package com.growvenus.ims.dto;

import com.growvenus.ims.enums.Priority;
import com.growvenus.ims.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class IssueResponse {

    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String assignee;

}
