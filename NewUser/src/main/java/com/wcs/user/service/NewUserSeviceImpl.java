package com.wcs.user.service;

import java.security.SecureRandom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wcs.user.dto.ApiResponse;
import com.wcs.user.dto.PassUpdateRequest;
import com.wcs.user.entity.User;
import com.wcs.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewUserSeviceImpl {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    public  String generateTempPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }
    
    
    public ApiResponse changePassword(PassUpdateRequest request)
    {
    	ApiResponse response = new ApiResponse();
    	String userName = request.getUsername();
    	String oldPassword= request.getTemppass();
    	String newPassword= request.getNewpass();
    	User user =userRepository.findByUsername(userName).orElseThrow();
    	if (user.getTempPass()!= null &&
    			!user.getTempPass().equals(oldPassword)) {

    		response.setMessage("Temporay password doesn't match");
    		response.setStatusCode(500);
    		return response;
    	}
    	else
    	{
    	response.setStatusCode(200);
    	response.setMessage("Password Updated Successfully");
    	response.setEnabled(true);
    	//user updations
    	user.setStatus("enabled");
    	user.setNewUserFlag('Y');
    	user.setTempPass(null);
    	user.setPassword(passwordEncoder.encode(newPassword));
    	userRepository.save(user);
    	
    	return response;
    	}
    	
    }
    
}
