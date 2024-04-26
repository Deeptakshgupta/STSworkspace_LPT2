package com.wcs.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.wcs.auth.model.User_jwt;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User_jwt,Long> {
    Optional<User_jwt> findByUserId(String userId);
    Boolean existsByUserId(String UserId);
}
