package com.planner.godsaeng.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.planner.godsaeng.dto.SaveResponseDTO;
import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.service.KakaoLoginService;
import com.planner.godsaeng.service.UserService;

@RestController
public class KakaoLoginController {
	@Autowired
	UserService service;

	KakaoLoginService kakaoLoginService = new KakaoLoginService();
	
	@RequestMapping(value="/login")
	public ResponseEntity login(@RequestParam("code") String code) {
//		ModelAndView mav = new ModelAndView();
		// 1번 인증코드 요청 전달
		String accessToken = kakaoLoginService.getAccessToken(code);
		// 2번 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoLoginService.getUserInfo(accessToken);
		
		System.out.println("login info : " + userInfo.toString());
		
//		if(userInfo.get("email") != null && userInfo.get("profileImageUrl") != null) {
//			session.setAttribute("userId", userInfo.get("email"));
//			session.setAttribute("profileImageUrl", userInfo.get("profileImageUrl"));
//			System.out.println(userInfo.get("email")); //내가 추가
//			session.setAttribute("accessToken", accessToken);
//		}
		
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
//	}h
	
	@RequestMapping(value="/check")
	public ResponseEntity<Boolean> check(@RequestParam("uid") String uid) {
		boolean successed = kakaoLoginService.checkUser(uid);
		
		if(successed) {
			return ResponseEntity.ok(successed);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
//	@RequestMapping(value="/save")
//	public ResponseEntity<Boolean> save(@RequestBody SaveResponseDTO userinfo) {
//		
//	  String u_id = userinfo.getU_id();
//	  String u_nickname = userinfo.getU_nickname();
//	  String u_content = userinfo.getU_content();
//	  
//	  UserDTO dto = new UserDTO();
//	  dto.setU_id(u_id);
//      dto.setU_nickname(u_nickname);
//      dto.setU_zepid(null);
//      dto.setU_deposit(0);
//      dto.setU_grade(null);
//      dto.setU_level(1);
//      dto.setU_content(u_content);
//      dto.setU_successedchallenge(null);
//      
//      boolean isAddSuccessed = service.InsertUser(dto);
//      
////	  ModelAndView mav = new ModelAndView();
////	  mav.addObject("userId", u_id);
////	  mav.setViewName("index");
//      if(isAddSuccessed) {
//    	  return ResponseEntity.ok(true);
//      }else {
//    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
//      }
//	}
	
}
