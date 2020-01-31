/*package com.capstone.jwt.service;

import com.capstone.jwt.model.Role;
import com.capstone.jwt.model.CapstoneUserDetails;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class CapstoneUserService {
	
	// this is just an example, you can load the user from the database from the repository
	
	//username:passwowrd -> user:user
	private final String userUsername = "user";// password: user
	private final CapstoneUserDetails user = new CapstoneUserDetails(userUsername, "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", true, Arrays.asList(Role.ROLE_USER));
	
	//username:passwowrd -> admin:admin
	private final String adminUsername = "admin";// password: admin
	private final CapstoneUserDetails admin = new CapstoneUserDetails(adminUsername, "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true, Arrays.asList(Role.ROLE_ADMIN));
	
	public Mono<CapstoneUserDetails> findByUsername(String username) {
		if (username.equals(userUsername)) {
			return Mono.just(user);
		} else if (username.equals(adminUsername)) {
			return Mono.just(admin);
		} else {
			return Mono.empty();
		}
	}
	
}*/