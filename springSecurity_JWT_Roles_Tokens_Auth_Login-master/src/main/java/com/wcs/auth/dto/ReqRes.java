package com.wcs.auth.dto;


import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wcs.auth.entity.User;

import jakarta.persistence.Column;
import lombok.Data;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String email;
    private String role;
    private String tempPass;
    private String password;
    private String username;
    private Long userAccNo;
    private String phoneNumber;
    private char newUserFlag;
    private Instant creationDate;
    private String author;
    private String status;
    
    private User ourUsers;
}
