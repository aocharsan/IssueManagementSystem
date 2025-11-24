package com.growvenus.ims.mapper;

import com.growvenus.ims.dto.UserRequest;
import com.growvenus.ims.dto.UserResponse;
import com.growvenus.ims.entity.User;

public class UserMapper {


    public static User toUser(UserRequest userRequest){
        return User.builder()
                .userName(userRequest.getUserName())
                .password(userRequest.getPassword())
                .build();
    }

    public static UserResponse toUserResponseDTO(User user){
        return  UserResponse.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();

    }
}
