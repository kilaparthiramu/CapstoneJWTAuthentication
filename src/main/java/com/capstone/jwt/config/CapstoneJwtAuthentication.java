package com.capstone.jwt.config;

import ch.qos.logback.core.net.server.Client;

import com.capstone.jwt.model.Role;
import com.capstone.jwt.util.CapstoneJwtTokenUtil;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CapstoneJwtAuthentication implements ReactiveAuthenticationManager {

	@Autowired
	private CapstoneJwtTokenUtil capstoneJwtTokenUtil;
	
	@Override
	@SuppressWarnings("unchecked")
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();
		
		String username;
		try {
			username = capstoneJwtTokenUtil.getUsernameFromToken(authToken);
		} catch (Exception e) {
			username = null;
		}
		if (username != null && capstoneJwtTokenUtil.validateToken(authToken)) {
			Claims claims = capstoneJwtTokenUtil.getAllClaimsFromToken(authToken);
			String role = (String) claims.get("role");
			List<GrantedAuthority> grantedAuths =
	                AuthorityUtils.commaSeparatedStringToAuthorityList(role);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				username,null,grantedAuths);
			return Mono.just(auth);
		} else {
			return Mono.empty();
		}
	}
}
