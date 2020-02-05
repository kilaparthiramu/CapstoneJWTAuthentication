package com.capstone.jwt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(value="userdetails")
public class CapstoneUserDetails {
	
	@Id
	private String username;
	private String password;
	private Boolean enabled;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public CapstoneUserDetails(String username, String password, Boolean enabled,
			String role) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

}