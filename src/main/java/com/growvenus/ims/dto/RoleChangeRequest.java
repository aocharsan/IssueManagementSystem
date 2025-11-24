package com.growvenus.ims.dto;

import com.growvenus.ims.enums.Roles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleChangeRequest {

    private Roles roles;

}

