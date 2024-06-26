package com.wcs.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.auth.dto.request.AddressRequest;
import com.wcs.auth.dto.request.LoginRequest;
import com.wcs.auth.dto.request.SignupRequest;
import com.wcs.auth.repository.UserRepository;
import com.wcs.auth.service.AddressRegistrationService;
import com.wcs.auth.service.AuthService;
import com.wcs.auth.service.UserRegistrationService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    AuthService authService;
    @Autowired
    AddressRegistrationService addressRegistrationService;

    @Autowired
    UserRegistrationService userRegistrationService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/auth")
    public ResponseEntity<Map<String,String>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(Map.of("access_token", authService.login(loginRequest)));
    }

    @PostMapping("/address")
    public ResponseEntity<String> address(@RequestBody @Valid AddressRequest addressRequest){
        this.addressRegistrationService.saveAddress(addressRequest);
        return new ResponseEntity<>("Address saved in the table.", HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
//        if (Boolean.TRUE.equals(userRepository.existsByUserId(signupRequest.getUserId()))) {
//            return ResponseEntity
//                    .badRequest()
//                    .body("Error: UserId is already in use!");
//        }
        this.userRegistrationService.register(signupRequest);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}

