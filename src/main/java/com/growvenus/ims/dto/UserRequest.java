package com.growvenus.ims.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    @NotBlank(message = "you need to provide username")
    public String userName;

    @NotBlank(message = "you need to provide password")
    public String password;

}
