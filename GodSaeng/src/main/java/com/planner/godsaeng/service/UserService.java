package com.planner.godsaeng.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.UserRepository;

@Service
public class UserService {
   
   User user = null;
   @Autowired
   UserRepository userRepository;
   
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
   public List<User> ReadUser(){
      List<User> list = new ArrayList<User>();
      list = userRepository.findAll();
      return list;
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