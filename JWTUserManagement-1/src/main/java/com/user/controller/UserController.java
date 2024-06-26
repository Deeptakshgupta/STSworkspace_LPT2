package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.AuthenticationResponse;
import com.user.dto.LoginRequest;

import com.user.dto.RegisterRequest;
import com.user.service.AuthService;



import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private  AuthService authService;


	@GetMapping("/check")
	public String check() {
		return "Checked";
	}

	@PostMapping("/check2")
	public String check2() {
		return "Checked2";
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}



//	@PostMapping("/logout")
//	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
//		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
//		return ResponseEntity.ok("Refresh Token Deleted Successfully!!");
//	}
}
