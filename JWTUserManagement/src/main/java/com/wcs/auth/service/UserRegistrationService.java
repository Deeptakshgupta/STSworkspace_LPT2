package com.wcs.auth.service;

import com.wcs.auth.dto.request.SignupRequest;
import com.wcs.auth.model.User_jwt;

public interface UserRegistrationService {
	User_jwt register(SignupRequest signupRequest);
}
