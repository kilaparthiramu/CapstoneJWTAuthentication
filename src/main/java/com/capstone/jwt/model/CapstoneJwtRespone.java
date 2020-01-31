package com.capstone.jwt.model;

public class CapstoneJwtRespone {
	
	private String token;

	public CapstoneJwtRespone(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
