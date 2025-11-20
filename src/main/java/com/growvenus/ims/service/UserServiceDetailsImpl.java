package com.growvenus.ims.service;

import com.growvenus.ims.dto.UserRequest;
import com.growvenus.ims.dto.UserResponse;
import com.growvenus.ims.entity.User;
import com.growvenus.ims.enums.Roles;
import com.growvenus.ims.mapper.UserMapper;
import com.growvenus.ims.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceDetailsImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for provided username: " + username));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles().name())
                .build();

    }

    public void registerUser(UserRequest userRequest){
        User user= UserMapper.toUser(userRequest);
        Optional<User> dbUser = userRepository.findByUserName(userRequest.getUserName());
        if(dbUser.isPresent()) {
            throw new UsernameNotFoundException("User already registered with given username: "+userRequest.getUserName());
        }
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(Roles.USER);
        userRepository.save(user);
    }

    public UserResponse changeUserRole(int id, Roles newRole) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));

        // 🔒 Example rule: do not allow changing ADMIN by anyone
        if (user.getRoles() == Roles.ADMIN) {
            throw new IllegalStateException("Cannot change role of SUPER_ADMIN user");
        }

        user.setRoles(newRole);
        User saved = userRepository.save(user);
        log.info("Role has been change successfully to "+saved.getRoles().name());
        return UserMapper.toUserResponseDTO(saved);
    }

}
