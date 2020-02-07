package com.capstone.jwt.config;

import com.capstone.jwt.util.CapstoneJwtTokenUtil;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CapstoneJwtAuthentication implements ReactiveAuthenticationManager {

	@Autowired
	private CapstoneJwtTokenUtil capstoneJwtTokenUtil;
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String authToken = authentication.getCredentials().toString();
		if (capstoneJwtTokenUtil.getUsernameFromToken(authToken) != null && capstoneJwtTokenUtil.validateToken(authToken)) {
			Claims claims = capstoneJwtTokenUtil.getAllClaimsFromToken(authToken);
			String role = (String) claims.get("role");
			List<GrantedAuthority> grantedAuths =
	                AuthorityUtils.commaSeparatedStringToAuthorityList(role);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					capstoneJwtTokenUtil.getUsernameFromToken(authToken),null,grantedAuths);
			return Mono.just(auth);
		} else {
			return Mono.empty();
		}
	}
}
