package com.growvenus.ims.controller;

import com.growvenus.ims.dto.IssueRequest;
import com.growvenus.ims.dto.IssueResponse;
import com.growvenus.ims.entity.Issue;
import com.growvenus.ims.enums.Priority;
import com.growvenus.ims.enums.Status;
import com.growvenus.ims.service.IssueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("growvenus/api")
public class IssueController {

    private final IssueServiceImpl issueService;

    @Autowired
    public IssueController(IssueServiceImpl issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/create-issue")
    public ResponseEntity<Issue> createIssue(@RequestBody IssueRequest request){
        issueService.createIssue(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/get-issue")
    public ResponseEntity<IssueResponse> fetchIssue(@RequestParam String title){

        IssueResponse issueResponse = issueService.fetchIssueByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(issueResponse);

    }

    @GetMapping("/get-all-issue")
    public ResponseEntity<List<IssueResponse>> fetchAllIssue(){

        List<IssueResponse> allIssues = issueService.getAllIssues();
        return ResponseEntity.status(HttpStatus.OK).body(allIssues);

    }

    @PatchMapping("update-issue/{id}")
    public ResponseEntity<IssueResponse> updateIssueOrStatus(
            @PathVariable int id,
            @RequestBody IssueRequest request) {
        return ResponseEntity.ok(issueService.updateIssueOrStatus(id, request));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("delete-issue/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable int id) {
        issueService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("get-filtered-issue")
    public ResponseEntity<List<IssueResponse>> filterIssueByPriorityOrStatus(
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Status status) {

        return ResponseEntity.ok(issueService.filterIssues(priority, status));
    }




}
