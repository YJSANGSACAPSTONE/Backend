package com.planner.godsaeng.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.service.PlanService;
import com.planner.godsaeng.service.SessionService;

@RestController
@RequestMapping("/userverify")
public class SessionController {
	
	@Autowired
	SessionService service;
	
	@GetMapping("/login")
	public boolean login(String uid, HttpSession session, HttpServletResponse response) {
	    Optional<User> userOptional = service.loginVerify(uid);
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        session.setAttribute("u_id", user.getUid());
	        session.setAttribute("u_level", user.getUlevel());
	        session.setAttribute("u_grade", user.getUgrade());
	        session.setAttribute("u_deposit", user.getUdeposit());
	        Cookie cookie = new Cookie("SESSIONID", session.getId());
	        cookie.setPath("/");
	        cookie.setHttpOnly(true);
	        response.addCookie(cookie);
	        return true;
	    } else {
	        return false;
	    }
	}
	
	@GetMapping("/logout")
	public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
	    session.invalidate();
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("SESSIONID")) {
	                cookie.setMaxAge(0);
	                cookie.setValue("");
	                cookie.setPath("/");
	                response.addCookie(cookie);
	            }
	        }
	    }
	}
	
	

}
