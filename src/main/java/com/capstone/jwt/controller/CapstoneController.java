package com.capstone.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.jwt.model.Message;

import reactor.core.publisher.Mono;

@RestController
public class CapstoneController {
	
	@RequestMapping(value = "/resource/admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> admin() {
		return Mono.just(ResponseEntity.ok(new Message("Content for admin")));
	}
	
	@RequestMapping(value = "/resource/pmo", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PMO')")
	public Mono<ResponseEntity<?>> pmo() {
		return Mono.just(ResponseEntity.ok(new Message("Content for pmo")));
	}
	
	@RequestMapping(value = "/resource/poc", method = RequestMethod.GET)
	@PreAuthorize("hasRole('POC')")
	public Mono<ResponseEntity<?>> poc() {
		return Mono.just(ResponseEntity.ok(new Message("Content for poc")));
	}
	
	@RequestMapping(value = "/resource/user-or-admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Mono<ResponseEntity<?>> userOrAdmin() {
		return Mono.just(ResponseEntity.ok(new Message("Content for user or admin")));
	}
}
