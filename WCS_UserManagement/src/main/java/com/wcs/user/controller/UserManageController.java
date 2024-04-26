package com.wcs.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.user.model.Deep_user;
import com.wcs.user.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserManageController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> login(@RequestBody Deep_user user){
		
		userService.createUser(user);
		return ResponseEntity.ok("User Created Successfully");
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<String> loginUser(@RequestBody Deep_user user) {
	        if (user != null && userService.authenticateUser(user)) {
	            return ResponseEntity.ok("Login successful");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	    }
}
