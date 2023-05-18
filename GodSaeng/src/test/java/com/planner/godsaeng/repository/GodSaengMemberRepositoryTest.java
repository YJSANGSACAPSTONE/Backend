package com.planner.godsaeng.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.entity.User;

@SpringBootTest
public class GodSaengMemberRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void insertUsers() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			User user = User.builder()
					.uid("userID" + i)
					.unickname("nickname" + i)
					.uzepid("zepetoID" + i)
					.ugrade("grade0")
					.uemail("user" + i + "@gmail.com")
					.ucontent("content" + i)
					.usuccessedchallenge("successCha" + i)
					.build();
			
			userRepository.save(user);
		});
	}
}
