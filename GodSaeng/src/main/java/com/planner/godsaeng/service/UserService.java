package com.planner.godsaeng.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.UserRepository;

@Service
public class UserService {
   
   @Autowired
   UserRepository userRepository;
   
   User user = null;
   
   //유저 회원가입(INSERT)
   public boolean InsertUser(UserDTO u) {
      user = User.builder()
            .u_id(u.getU_id())
            .u_nickname(u.getU_nickname())
            .u_zepetoid(u.getU_zepetoid())
            .u_img(u.getU_img())
            .u_deposit(u.getU_deposit())
            .u_grade(u.getU_grade())
            .u_email(u.getU_email())
            .u_successedchallenge(u.getU_successedchallenge())
            .build();
      
      try {
         userRepository.save(user);
         return true;
      }catch(Exception e) {
         e.printStackTrace();
         return false;
      }
   }
   
   //유저 정보 목록
   public UserDTO ReadUser(String u_id){
	  
      UserDTO userinfo = null;
      Optional<User> result = userRepository.findById(u_id);
    	
      if(result.isPresent()) {
    	  	userinfo = UserDTO.builder()
    	  	.u_id(result.get().getU_id())
			.u_nickname(result.get().getU_nickname())
			.u_zepetoid(result.get().getU_zepetoid())
			.u_img(result.get().getU_img())
			.u_deposit(result.get().getU_deposit())
			.u_grade(result.get().getU_grade())
			.u_email(result.get().getU_email())
			.u_successedchallenge(result.get().getU_successedchallenge())
			.build();
    	  	return userinfo;
      } else {
    	  return null;
      }
	     
   }

   
   //유저 정보 수정
   public boolean UpdateUser(UserDTO u) {
      user = User.builder()
            .u_id(u.getU_id())
            .u_nickname(u.getU_nickname())
            .u_zepetoid(u.getU_zepetoid())
            .u_img(u.getU_img())
            .u_deposit(u.getU_deposit())
            .u_grade(u.getU_grade())
            .u_email(u.getU_email())
            .u_successedchallenge(u.getU_successedchallenge())
            .build();
      try {
         userRepository.save(user);
         return true;
      }catch(Exception e) {
         e.printStackTrace();
         return false;
      }
   }
   
   //유저 정보 삭제
   public boolean DeleteUser(String u_id) {
      try {
         userRepository.deleteById(u_id);
         return true;
      }catch(Exception e) {
         e.printStackTrace();
         return false;
      }
   }
}