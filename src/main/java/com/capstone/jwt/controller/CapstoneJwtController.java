package com.capstone.jwt.controller;

import com.capstone.jwt.config.CapstoneJwtTokenGeneration;
import com.capstone.jwt.config.CapstoneJwtPasswordEncoder;
import com.capstone.jwt.dao.CapstoneUserDetailsRepository;
import com.capstone.jwt.model.CapstoneJwtRequest;
import com.capstone.jwt.model.CapstoneJwtRespone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class CapstoneJwtController {

	@Autowired
	private CapstoneJwtTokenGeneration jwtUtil;
	
	@Autowired
	private CapstoneJwtPasswordEncoder passwordEncoder;
	
	@Autowired
	private CapstoneUserDetailsRepository capstoneUserDetailsRepository;
	

	@PostMapping(value = "/login")
	public Mono<CapstoneJwtRespone> login(@RequestBody CapstoneJwtRequest capstoneJwtRequest) {
		return capstoneUserDetailsRepository.findById(capstoneJwtRequest.getUsername()).map((userDetails) -> {
			if (passwordEncoder.encode(capstoneJwtRequest.getPassword()).equals(userDetails.getPassword())) {
				return new CapstoneJwtRespone(jwtUtil.generateToken(userDetails),userDetails.getRole());
			} else {
				return new CapstoneJwtRespone(HttpStatus.UNAUTHORIZED,"Invalid Credentials");
			}
		}).defaultIfEmpty(new CapstoneJwtRespone(HttpStatus.UNAUTHORIZED,"Invalid Credentials"));
	}
}
