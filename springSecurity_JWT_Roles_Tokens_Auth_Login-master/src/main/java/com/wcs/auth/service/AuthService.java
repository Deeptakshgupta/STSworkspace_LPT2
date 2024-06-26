package com.wcs.auth.service;

import com.wcs.auth.dto.LoginResponsetDto;
import com.wcs.auth.dto.ReqRes;
import com.wcs.auth.entity.User;

import com.wcs.auth.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private UserRepository ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
        	User ourUsers = new User();
        	ourUsers.setUsername(registrationRequest.getUsername());
        	ourUsers.setUserAccNo(registrationRequest.getUserAccNo());
        	ourUsers.setPhoneNumber(registrationRequest.getPhoneNumber());
        	ourUsers.setNewUserFlag('N');
        	ourUsers.setTempPass(registrationRequest.getTempPass());
        	ourUsers.setCreationDate(Instant.now());
        	ourUsers.setAuthor(registrationRequest.getAuthor());
        	ourUsers.setStatus(registrationRequest.getStatus());
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());
            User ourUserResult = ourUserRepo.save(ourUsers);
            if (ourUserResult != null && ourUserResult.getId()>0) {
                resp.setOurUsers(ourUserResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public LoginResponsetDto signIn(ReqRes signinRequest){
    	LoginResponsetDto response = new LoginResponsetDto();

        try {
        	// authentication manager auto authenticate
        	System.out.println();
            var auth1 = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
            System.out.println(auth1.isAuthenticated());
            var user = ourUserRepo.findByUsername(signinRequest.getUsername()).orElseThrow();
            System.out.println("USER IS: "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        String username = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        User users = ourUserRepo.findByUsername(username).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
