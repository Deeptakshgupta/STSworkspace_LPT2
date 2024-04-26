package com.wcs.user.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.user.dto.ApiResponse;
import com.wcs.user.dto.PassUpdateRequest;
import com.wcs.user.service.NewUserSeviceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/newuser")
@RequiredArgsConstructor
public class NewUserController {

	private final NewUserSeviceImpl service;
//	@PostMapping("/tempPass")
//	public ResponseEntity<?> saveUserWithTempPassword(){
//		
//	}
	
	@PostMapping("/newPass")
	public ResponseEntity<ApiResponse> updateUserPassword(@RequestBody PassUpdateRequest request){
		
			
			return ResponseEntity.ok(service.changePassword(request));
		
	}
}
