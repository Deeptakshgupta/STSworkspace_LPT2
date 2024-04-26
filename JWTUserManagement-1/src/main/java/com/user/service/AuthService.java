package com.user.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.dto.AuthenticationResponse;
//import com.user.dto.AuthenticationResponse;
import com.user.dto.LoginRequest;
import com.user.dto.RefreshTokenRequest;
import com.user.dto.RegisterRequest;
import com.user.model.User_deep;
import com.user.repository.UserRepository;

//import com.user.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	 @Value("${jwt.expiration.time}")
   private Long jwtExpirationInMillis;
	    
	 private final PasswordEncoder passwordEncoder;
	    private final UserRepository userRepository;
	    
	    private final JWTService jwtService;
//	    private final MailService mailService;
	    private final AuthenticationManager authenticationManager;
//	    private final JwtProvider jwtProvider;
	    
	    
	    
	    
	    
	 public void signup(RegisterRequest registerRequest) {
	        User_deep user = new User_deep();
	        user.setUsername(registerRequest.getUsername());
	        user.setEmail(registerRequest.getEmail());
	        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
	        user.setCreated(Instant.now());
	        user.setEnabled(true);
//	        jwtService.generateJwtToken(registerRequest);
	        userRepository.save(user);
	        

	    }
	 
	 public AuthenticationResponse login(LoginRequest loginRequest) {
		 
		 System.out.println("inside login");
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
	                loginRequest.getPassword()));
	        System.out.println(authenticate.getPrincipal());
	       String token= jwtService.generateJwtToken(loginRequest);//jwt to generate Token
//	        SecurityContextHolder.getContext().setAuthentication(authenticate);
	        return new AuthenticationResponse().builder()
	        		.token(token)
//	        		.refreshToken(refreshTokenService.generateRefreshToken().getToken())
	                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
	                .username(loginRequest.getUsername())
	                .build();
	        
	    }
}