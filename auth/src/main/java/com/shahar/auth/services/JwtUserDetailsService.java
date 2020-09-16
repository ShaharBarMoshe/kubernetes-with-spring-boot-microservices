package com.shahar.auth.services;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	PasswordEncoder p;
	
	public UserDetails loadUserByUsername(String username) {
		return new UserDetails() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -8362348847128972438L;

			@Override
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return true;
			}
			
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return "test_name";
			}
			
			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return p.encode("password");
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				GrantedAuthority a = new GrantedAuthority() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String getAuthority() {
						return "Admin";
					}
				};
				return Arrays.asList(a);
			}
		};
	}

}
