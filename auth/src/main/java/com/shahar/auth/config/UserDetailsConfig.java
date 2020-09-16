package com.shahar.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shahar.auth.services.JwtUserDetailsService;

@Configuration
public class UserDetailsConfig {

	@Bean 
	JwtUserDetailsService jwtUserDetailsService() {
		return new JwtUserDetailsService();
	}
}
