package com.planner.godsaeng.controller;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.service.SessionService;

@RestController
@RequestMapping("/userverify")
public class SessionController {
	
	@Autowired
	SessionService service;
	
	@GetMapping("/login")
	public boolean login(@RequestParam String uid, HttpServletResponse response) {
	    Optional<User> result = service.loginVerify(uid);
	    if (result.isPresent()) {
	        User user = result.get();
	        
	        Cookie cookie = new Cookie("uid", user.getUid());
	        
	        //쿠키 설정
	        cookie.setPath("/");
	        cookie.setHttpOnly(true);
	        //응답 헤더에 쿠키 추가
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
