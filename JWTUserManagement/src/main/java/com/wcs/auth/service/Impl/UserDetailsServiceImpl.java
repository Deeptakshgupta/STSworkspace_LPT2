package com.wcs.auth.service.Impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wcs.auth.model.User_jwt;
import com.wcs.auth.repository.UserRepository;
import com.wcs.auth.utils.UserUtils;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	User_jwt user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + userId));
        log.info("load user : {} with userId : {}", user, userId);
        return UserUtils.build(user);
    }
}
