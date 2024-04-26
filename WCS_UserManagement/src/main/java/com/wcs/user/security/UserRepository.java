package com.wcs.user.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.user.model.Deep_user;
import java.util.List;


public interface UserRepository extends JpaRepository<Deep_user, Integer>{

	Deep_user findByUserName(String userName);
}
