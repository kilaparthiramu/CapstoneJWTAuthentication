package com.capstone.jwt.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.capstone.jwt.model.CapstoneUserDetails;

public interface CapstoneUserDetailsRepository extends ReactiveCrudRepository<CapstoneUserDetails, String>{
	
}
