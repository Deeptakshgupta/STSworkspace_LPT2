package com.user.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_deep {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long userId;
	    private String username;
	    private String password;
	    private String email;
	    private Instant created;
	    private boolean enabled; 
	   
	
	
}
