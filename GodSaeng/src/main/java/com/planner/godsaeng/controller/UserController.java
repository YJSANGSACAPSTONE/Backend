package com.planner.godsaeng.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.SaveResponseDTO;
import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
   
   @Autowired
   UserService service;
   
   @PostMapping("/adduser")
   public ResponseEntity<Boolean> addUser(@RequestBody SaveResponseDTO userinfo) {
	   
	  String u_id = userinfo.getU_id();
	  String u_nickname = userinfo.getU_nickname();
	  String u_content = userinfo.getU_content();
		  
	  UserDTO dto = new UserDTO();
	  dto.setU_id(u_id);
      dto.setU_nickname(u_nickname);
      dto.setU_zepid(null);
      dto.setU_deposit(0);
      dto.setU_grade(null);
      dto.setU_level(1);
      dto.setU_content(u_content);
      dto.setU_successedchallenge(null);	   
      
      boolean isAddSuccessed = service.InsertUser(dto);
      
      if(isAddSuccessed) {
    	  return ResponseEntity.ok(true);
      }else {
    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
      }
   }
   
   @GetMapping("/listuser")
   public String listUser(HttpSession session, Model m) {
	  String sessionuser_id = (String)(session.getAttribute("u_id"));
	  sessionuser_id = "hwangjoo";
      UserDTO userinfo = service.ReadUser(sessionuser_id);
      m.addAttribute("userinfo", userinfo);
      return "publishing/pages/userview";
   }
   
   @PostMapping("/updateuser")
   public ResponseEntity<Boolean> updateUser(UserDTO u) {
      boolean isUdateSuccessed = service.UpdateUser(u);
      if(isUdateSuccessed) {
    	  return ResponseEntity.ok(true);
      }else {
    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
      }
   }
   
   @GetMapping("/deleteplan")
   public ResponseEntity<Boolean> deletePlan(UserDTO u) {
      boolean isDeleted = service.DeleteUser(u.getU_id());
      if(isDeleted) {
    	  return ResponseEntity.ok(true);
      }else {
    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
      }
   }
}