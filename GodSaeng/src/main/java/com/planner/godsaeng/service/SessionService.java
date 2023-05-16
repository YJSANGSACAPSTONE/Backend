package com.planner.godsaeng.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.UserRepository;

@Service
public class SessionService {
	
	@Autowired
	private UserRepository userRepository;
	
	 public Optional<User> loginVerify(String uid) {
	        return userRepository.findByUid(uid);
	    }
	
	
	
	
	

}
