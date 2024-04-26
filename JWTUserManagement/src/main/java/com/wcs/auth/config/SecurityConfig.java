package com.wcs.auth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wcs.auth.security.AuthTokenFilter;
import com.wcs.auth.service.Impl.UserDetailsServiceImpl;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {//extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
     AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .exceptionHandling().authenticationEntryPoint(this.unauthorizedEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .mvcMatchers("/user/**").permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    	
    	
//    	return http
//    	        .cors().and()
//    	        .csrf().disable()
//    	        .authorizeRequests(authorize -> authorize
//    	            .anyRequest().permitAll()
//    	        )
//    	        .build();
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/*.js", "/*.css", "/*.ico",
                        "/assets/**", "/static/**")
                .mvcMatchers("/swagger-ui/**",
                        "/v2/api-docs/**", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/**",
                        "/swagger-ui.html", "/webjars/**", "/swagger-ui/index.html",
                        "/swagger.json", "/configuration/ui",
                        "/v3/api-docs/**", "/v1/user/**");
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
    @Bean
     PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
       return builder.getAuthenticationManager();
   }

    @Bean
     AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
    }
}