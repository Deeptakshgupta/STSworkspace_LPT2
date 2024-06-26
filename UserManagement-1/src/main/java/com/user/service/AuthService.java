//package com.user.service;
//
//import java.time.Instant;
//import java.util.UUID;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.user.dto.AuthenticationResponse;
////import com.user.dto.AuthenticationResponse;
//import com.user.dto.LoginRequest;
//import com.user.dto.RefreshTokenRequest;
//import com.user.dto.RegisterRequest;
//import com.user.model.User_deep;
//import com.user.model.VerificationToken;
//import com.user.repository.UserRepository;
//import com.user.repository.VerificationTokenRepository;
//
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//	    
//	 private final PasswordEncoder passwordEncoder;
//	    private final UserRepository userRepository;
//	    private final VerificationTokenRepository verificationTokenRepository;
////	    private final MailService mailService;
//	    private final AuthenticationManager authenticationManager;
//	    private final JwtProvider jwtProvider;
//	    private final RefreshTokenService refreshTokenService;
//	    
//	    
//	    
//	    
//	 public void signup(RegisterRequest registerRequest) {
//	        User_deep user = new User_deep();
//	        user.setUsername(registerRequest.getUsername());
//	        user.setEmail(registerRequest.getEmail());
//	        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//	        user.setCreated(Instant.now());
//	        user.setEnabled(true);
//	        generateVerificationToken(user);
//	        userRepository.save(user);
//	        
//
//	    }
//	 
//	 public AuthenticationResponse login(LoginRequest loginRequest) {
//		 
//		 System.out.println("inside login");
//	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
//	                loginRequest.getPassword()));
//	        System.out.println(authenticate.getPrincipal());
//	        String token =  jwtProvider.generateToken(authenticate);//jwt to generate Token
////	        SecurityContextHolder.getContext().setAuthentication(authenticate);
//	        return new AuthenticationResponse().builder()
//	        		.authenticationToken(token)
//	        		.refreshToken(refreshTokenService.generateRefreshToken().getToken())
//	                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
//	                .username(loginRequest.getUsername())
//	                .build();
//	        
//	    }
//	 
//	 private void generateVerificationToken(User_deep user) {
//	        String token = UUID.randomUUID().toString();
//	        VerificationToken verificationToken = new VerificationToken();
//	        verificationToken.setToken(token);
//	        verificationToken.setUser(user);
//
//	        verificationTokenRepository.save(verificationToken);
////	        return token;
//	    }
//	 
//	 public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//	        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
//	        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
//	        return AuthenticationResponse.builder()
//	                .authenticationToken(token)
//	                .refreshToken(refreshTokenRequest.getRefreshToken())
//	                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
//	                .username(refreshTokenRequest.getUsername()) // update from  UserName to other
//	                .build();
//	    }
//}
