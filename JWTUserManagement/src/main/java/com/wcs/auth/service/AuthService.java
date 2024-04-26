package com.wcs.auth.service;

import com.wcs.auth.dto.request.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}
