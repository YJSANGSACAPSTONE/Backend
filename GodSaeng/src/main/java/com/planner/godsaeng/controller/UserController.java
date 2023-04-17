package com.planner.godsaeng.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
   
   @Autowired
   UserService service;
   
   @PostMapping("/adduser")
   public String addUser(UserDTO u) {
      service.InsertUser(u);
      return null;
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
   public String updateUser(UserDTO u) {
      service.UpdateUser(u);
      return null;
   }
   
   @GetMapping("/deleteplan")
   public String deletePlan(UserDTO u) {
      service.DeleteUser(u.getU_id());
      
      return null;
   }
}