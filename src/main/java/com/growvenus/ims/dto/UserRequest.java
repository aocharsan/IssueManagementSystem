package com.growvenus.ims.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    public String userName;
    public String password;

}
