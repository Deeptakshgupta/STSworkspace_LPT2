package com.wcs.auth.service.Impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.wcs.auth.dto.request.LoginRequest;
import com.wcs.auth.dto.response.UserDetailsDto;
import com.wcs.auth.service.AuthService;
import com.wcs.auth.service.JWTService;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private JWTService jwtService;
    @Override
    public String login(LoginRequest loginRequest) {
        log.info("Login request for userId : {} ", loginRequest.getUserId());
        
        
        //authentication
        //auto => authenticate method => authentication manager => userName and Password
//        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));
     
        
        
        UserDetailsDto userDetails = (UserDetailsDto) userDetailsService.loadUserByUsername(loginRequest.getUserId());
        return jwtService.generateJwtToken(userDetails);
    }
}
