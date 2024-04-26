package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.User_deep;

public interface UserRepository extends JpaRepository<User_deep, Long> {
    Optional<User_deep> findByUsername(String username);
}
