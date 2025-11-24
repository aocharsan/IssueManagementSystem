package com.growvenus.ims.dto;


import com.growvenus.ims.enums.Priority;
import com.growvenus.ims.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IssueRequest {

    @NotBlank(message = "Need to provide title to get related issue.....")
    private String title;

    @NotBlank(message = "Need to provide description related given issue.....")
    private String description;
    private Status status;
    private Priority priority;

}
