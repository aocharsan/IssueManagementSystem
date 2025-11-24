package com.growvenus.ims.service;

import com.growvenus.ims.dto.IssueRequest;
import com.growvenus.ims.dto.IssueResponse;
import com.growvenus.ims.entity.Issue;
import com.growvenus.ims.enums.Priority;
import com.growvenus.ims.enums.Status;
import com.growvenus.ims.exception.IssueAlreadyExistsException;
import com.growvenus.ims.exception.IssueNotFoundException;
import com.growvenus.ims.mapper.IssueMapper;
import com.growvenus.ims.repository.IssueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IssueServiceImpl {

   private final IssueRepository issueRepository;


   @Autowired
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
   }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void createIssue(IssueRequest issueRequest){

        String authUser = getAuthentication().getName();
        Issue issue = IssueMapper.toIssue(issueRequest);
        Optional<Issue> byTitle = issueRepository.findByTitleIgnoreCase(issueRequest.getTitle());
        if(byTitle.isPresent()){
            throw new IssueAlreadyExistsException("Issue for provided title already created by :"+byTitle.get().getAssignee());
        }
        else {
            issue.setAssignee(authUser);
            issue.setCreatedAt(LocalDateTime.now());
            issueRepository.save(issue);
            log.info("new issue created successfully......");

        }

    }


    public IssueResponse fetchIssueByTitle(String title){

        Issue issue = issueRepository.findByTitleIgnoreCase(title)
                                     .orElseThrow(() -> new IssueNotFoundException("Issue not found for provided title :"+title));
        return IssueMapper.toIssueResponseDTO(issue);

    }

    public List<IssueResponse> getAllIssues(){
        List<Issue> issueList = issueRepository.findAll();
        // use pagination for large tables
        //  issueRepository.findAll(PageRequest.of(0,50));
        if(issueList.isEmpty()){
            throw new RuntimeException("Currently there is no issues inline.........");
        }

       return issueList.stream()
                .map(IssueMapper::toIssueResponseDTO)
                .toList();

    }

    public IssueResponse updateIssueOrStatus(int id, IssueRequest dto) {

        String userName  = getAuthentication().getName();
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));


        if (!issue.getAssignee().equals(userName)) {
            throw new AccessDeniedException("You can update only your own issues");
        }

        if (dto.getTitle() != null)
            issue.setTitle(dto.getTitle());

        if (dto.getDescription() != null)
            issue.setDescription(dto.getDescription());

        if (dto.getStatus() != null)
            issue.setStatus(dto.getStatus());
        if (dto.getPriority() != null)
            issue.setPriority(dto.getPriority());
        issue.setUpdatedAt(LocalDateTime.now());
        Issue updated = issueRepository.save(issue);

        return IssueMapper.toIssueResponseDTO(updated);
    }

    public void deleteIssue(int id){
        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no issue found...."));
        issueRepository.delete(issue);
        log.info("issue deleted successfully......");


    }

    public List<IssueResponse> filterIssues(Priority priority, Status status) {

        List<Issue> issues;
        if (priority != null && status != null) {
            issues = issueRepository.findByPriorityAndStatus(priority, status);

        } else if (priority != null) {
            issues = issueRepository.findByPriority(priority);

        } else if (status != null) {
            issues = issueRepository.findByStatus(status);

        } else {
            issues = issueRepository.findAll();  // No filters
        }

        return issues.stream()
                .map(IssueMapper::toIssueResponseDTO)
                .toList();
    }


}
