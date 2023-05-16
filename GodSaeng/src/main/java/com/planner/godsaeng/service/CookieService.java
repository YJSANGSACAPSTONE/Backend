package com.planner.godsaeng.service;

import javax.servlet.http.Cookie;

public class CookieService {
	
	public String cookieParser(Cookie[] cookies) {
		   String uid = null;
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if (cookie.getName().equals("uid")) {
		                uid = cookie.getValue();
		                break;
		            }
		        }
		    }
		    
		    return uid;
	}
}
