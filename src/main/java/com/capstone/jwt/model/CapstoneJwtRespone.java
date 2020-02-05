package com.capstone.jwt.model;

public class CapstoneJwtRespone {
	private String token;
	private String role;


	public CapstoneJwtRespone(String token, String role) {
		super();
		this.token = token;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
