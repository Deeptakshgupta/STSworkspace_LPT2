package com.wcs.auth.utils;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.wcs.auth.dto.response.UserDetailsDto;
import com.wcs.auth.model.User_jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserUtils {
    public static UserDetailsDto build(User_jwt user) {
    	 List<GrantedAuthority> authorities = new ArrayList<>();

         // Create a GrantedAuthority object for the role and add it to the list
         authorities.add(new SimpleGrantedAuthority(user.getRole()));


        return new UserDetailsDto(
                user.getUserId(),
                user.getPassword(),
                authorities);
    }
}