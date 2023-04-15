package com.planner.godsaeng.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
   
   UserService service = new UserService();
   
   @PostMapping("/adduser")
   public String addUser(UserDTO u) {
      service.InsertUser(u);
      return null;
   }
   
   @GetMapping("/listuser")
   public String listUser(Model m) {
      List<User> list = service.ReadUser();
      m.addAttribute("list", list);
      return null;
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