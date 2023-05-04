package com.planner.godsaeng.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.service.KakaoAPI;
import com.planner.godsaeng.service.UserService;

@RestController
public class KakaoController {
	@Autowired
	UserService service;

	KakaoAPI kakaoApi = new KakaoAPI();
	
	@RequestMapping(value="/login")
	public ResponseEntity login(@RequestParam("code") String code, HttpSession session) {
		System.out.println(code);
		ModelAndView mav = new ModelAndView();
		// 1번 인증코드 요청 전달
		String accessToken = kakaoApi.getAccessToken(code);
		System.out.println("access토큰값 : " + accessToken);
		// 2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
		
		System.out.println("login info : " + userInfo.toString());
		
		if(userInfo.get("email") != null && userInfo.get("profileImageUrl") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("profileImageUrl", userInfo.get("profileImageUrl"));
			System.out.println(userInfo.get("email")); //내가 추가
			session.setAttribute("accessToken", accessToken);
		}
//		mav.addObject("userId", userInfo.get("email"));
//		mav.addObject("profileImageUrl", userInfo.get("profileImageUrl"));
//		mav.setViewName("signup");
//		return mav;
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("userId", userInfo.get("email"));
		responseBody.put("profileImageUrl", userInfo.get("profileImageUrl"));
		responseBody.put("accessToken", accessToken);
		return ResponseEntity.ok(responseBody);
	}
	
//	@RequestMapping(value="/logout")
//	public ModelAndView logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView mav = new ModelAndView();
//		
//		kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
//		session.removeAttribute("accessToken");
//		session.removeAttribute("userId");
		
		
//		 // 세션 무효화
//	    session.invalidate();
	    
//	    // 캐시 제어 헤더 추가
//	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
//	    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
//	    response.setHeader("Expires", "0"); // Proxies
//		
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("JSESSIONID")) {
//                	cookie.setValue("");
//                    cookie.setPath("/");
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                }
//            }
//        }
		
		
//		mav.setViewName("index");
//		return mav;
//	}
	
	@RequestMapping(value="/save")
	public ModelAndView save(HttpSession session, HttpServletRequest request) {
	  String u_id = (String) session.getAttribute("userId");
	  String u_nickname = request.getParameter("u_nickname");
	  String u_zepid = request.getParameter("u_zepid");

	  String u_img = request.getParameter("u_img");
	  String u_grade = request.getParameter("u_grade");
	  String u_content= request.getParameter("u_content");
	  String u_successedchallenge= request.getParameter("u_successedchallenge");
	  
	  UserDTO dto = new UserDTO();
	  dto.setU_id(u_id);
      dto.setU_nickname(u_nickname);
      dto.setU_zepid(u_zepid);
      dto.setU_img(u_img);
      dto.setU_deposit(1);
      dto.setU_grade(u_grade);
      dto.setU_level(1);
      dto.setU_content(u_content);
      dto.setU_successedchallenge(u_successedchallenge);
      
      service.InsertUser(dto);
      
	  ModelAndView mav = new ModelAndView();
	  mav.addObject("userId", u_id);
	  mav.setViewName("index");
	  return mav;
	}
	
}
