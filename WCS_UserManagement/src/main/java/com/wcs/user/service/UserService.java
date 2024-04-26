package com.wcs.user.service;

import com.wcs.user.model.Deep_user;

public interface UserService {

	public void createUser(Deep_user user);

	public Deep_user getUserByUsername(String username);

	public boolean authenticateUser(Deep_user user);

}
