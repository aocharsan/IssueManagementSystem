package com.growvenus.ims.dto;

import com.growvenus.ims.enums.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String userName;
    private String password;
    private Roles roles;

}
