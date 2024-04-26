package com.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.user.security.JwtAuthenticationEntryPoint;
import com.user.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



	    @Bean
	     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    	return  http.csrf(csrf -> csrf.disable())
             .authorizeRequests()            
             .anyRequest()
             .permitAll()
             .and().build();
	    }
	    
	    }

	   