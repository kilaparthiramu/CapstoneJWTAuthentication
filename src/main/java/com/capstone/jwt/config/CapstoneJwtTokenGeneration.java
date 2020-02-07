package com.capstone.jwt.config;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.capstone.jwt.model.CapstoneUserDetails;

@Component
public class CapstoneJwtTokenGeneration implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Value("${capstone.jwt.secret}")
	private String secret;
	
	public String generateToken(CapstoneUserDetails user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRole());
		return doGenerateToken(claims, user.getUsername());
	}

	@SuppressWarnings("deprecation")
	private String doGenerateToken(Map<String, Object> claims, String username) {
		
		final Date createdDate = new Date();
		final Date expirationDate = new Date(createdDate.getTime() + 100 * 1000);
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes()))
				.compact();
	}
}
