package com.capstone.jwt.controller;

import com.capstone.jwt.config.CapstoneJwtTokenGeneration;
import com.capstone.jwt.config.CapstoneJwtPasswordEncoder;
import com.capstone.jwt.dao.CapstoneUserDetailsRepository;
import com.capstone.jwt.model.CapstoneJwtRequest;
import com.capstone.jwt.model.CapstoneJwtRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public Mono<ResponseEntity<?>> login(@RequestBody CapstoneJwtRequest capstoneJwtRequest) {
		return capstoneUserDetailsRepository.findById(capstoneJwtRequest.getUsername()).map((userDetails) -> {
			if (passwordEncoder.encode(capstoneJwtRequest.getPassword()).equals(userDetails.getPassword())) {
				return ResponseEntity.ok(new CapstoneJwtRespone(jwtUtil.generateToken(userDetails)));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
}
