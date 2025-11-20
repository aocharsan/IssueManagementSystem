package com.growvenus.ims.controller;

import com.growvenus.ims.dto.RoleChangeRequest;
import com.growvenus.ims.dto.UserRequest;
import com.growvenus.ims.dto.UserResponse;
import com.growvenus.ims.service.UserServiceDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service/api")
@Slf4j
public class UserController {

    private final UserServiceDetailsImpl userService;

    @Autowired
    public UserController(UserServiceDetailsImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/auth-user")
    public void createUser(@RequestBody UserRequest request) {
        userService.loadUserByUsername(request.getUserName());
       log.info("User log-in successfully using DB.............");
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRequest request) {
        userService.registerUser(request);
        log.info("User register successfully using DB.............");
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/role-change/{id}")
    public ResponseEntity<UserResponse> changeUserRole(
            @PathVariable int id,
            @RequestBody RoleChangeRequest request) {

        UserResponse updated = userService.changeUserRole(id, request.getRoles());
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/home")
    @ResponseBody
    public void welcomeHome(){
        System.out.println("Welcome to Issue Management System ...........");
    }










}
