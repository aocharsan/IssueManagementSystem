package com.growvenus.ims.mapper;

import com.growvenus.ims.dto.IssueRequest;
import com.growvenus.ims.dto.IssueResponse;
import com.growvenus.ims.entity.Issue;

public class IssueMapper {


    public static IssueRequest toIssueRequestDTO(Issue issue){

        return IssueRequest.builder()
                .title(issue.getTitle())
                .description(issue.getDescription())
                .build();

    }

    public static Issue toIssue(IssueRequest issueRequest){
        return Issue.builder()
                .title(issueRequest.getTitle())
                .description(issueRequest.getDescription())
                .assignee("Sanket Deshmukh")
                .build();
    }

    public static IssueResponse toIssueResponseDTO(Issue issue){
        return  IssueResponse.builder()
                .title(issue.getTitle())
                .description(issue.getDescription())
                .assignee(issue.getAssignee())
                .status(issue.getStatus())
                .priority(issue.getPriority())
                .createdAt(issue.getCreatedAt())
                .updatedAt(issue.getUpdatedAt())
                .build();
    }










}
