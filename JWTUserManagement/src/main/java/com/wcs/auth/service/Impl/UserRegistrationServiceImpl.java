package com.wcs.auth.service.Impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wcs.auth.dto.request.SignupRequest;
//import com.wcs.auth.model.ERole;
//import com.wcs.auth.model.Role;
import com.wcs.auth.model.User_jwt;
//import com.wcs.auth.repository.RoleRepository;
import com.wcs.auth.repository.UserRepository;
import com.wcs.auth.service.UserRegistrationService;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
//    private RoleRepository roleRepository;

    @Override
    public User_jwt register(SignupRequest signupRequest) {
        log.info("Signup request for user {} ",signupRequest.getUserId());
        

        User_jwt user = User_jwt.builder()
                .userId(signupRequest.getUserId())
                .contact(signupRequest.getContact())
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .password(encoder.encode(signupRequest.getPassword()))
                .role("admin")
                .build();
        user =  userRepository.save(user);
        log.info("User registered successfully with userId : {}", user.getUserId());
        return user;
    }
}
