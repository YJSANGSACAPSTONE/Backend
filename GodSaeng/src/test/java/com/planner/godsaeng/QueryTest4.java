package com.planner.godsaeng;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.repository.UserRepository;




@SpringBootTest
public class QueryTest4 {
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	void contextLoads(){
		LocalDateTime dummyDateTime = LocalDateTime.now();
		System.out.println(dummyDateTime);

		System.out.println(FindZepidByuID("sanghee_ok@naver.com"));
		
	}
	
	 public String FindZepidByuID(String uid) {
		   return userRepository.findUzepidByUid(uid);
	   }

}
