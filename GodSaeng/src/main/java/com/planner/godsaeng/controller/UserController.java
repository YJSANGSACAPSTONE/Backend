package com.planner.godsaeng.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planner.godsaeng.dto.SaveResponseDTO;
import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.dto.ZepIdVerifyDTO;
import com.planner.godsaeng.dto.ZepIdVerifyViewDTO;
import com.planner.godsaeng.security.jwt.JwtAuthentication;
import com.planner.godsaeng.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
   
   @Autowired
   UserService service;
   
   @PostMapping("/adduser")
   public ResponseEntity<Boolean> addUser(@RequestBody SaveResponseDTO userinfo , @AuthenticationPrincipal JwtAuthentication user) {
	   
	  String u_id = userinfo.getU_id();
	  String u_nickname = userinfo.getU_nickname();
	  String u_content = userinfo.getU_content();
	  String u_zepid = userinfo.getU_zepid();
		  
	  UserDTO dto = new UserDTO();
	  dto.setU_id(u_id);
      dto.setU_nickname(u_nickname);
      dto.setU_zepid(u_zepid);
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
   
   @Autowired
   private ObjectMapper objectMapper;
   
   @GetMapping("/listuser")
   public ResponseEntity listUser(@RequestParam("uid") String uid) {
//	  String sessionuser_id = (String)(session.getAttribute("u_id"));
//	  sessionuser_id = "hwangjoo";
      UserDTO userinfo = service.ReadUser(uid);
      
      try {
          // DTO 객체를 JSON 형식으로 변환
          String json = objectMapper.writeValueAsString(userinfo);
          // JSON 데이터를 ResponseBody에 담아서 반환
          return ResponseEntity.ok(json);
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
   }
   
   @PostMapping("/updateuser")
   public ResponseEntity<Boolean> updateUser(@RequestBody SaveResponseDTO userinfo) {
	   
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
      boolean isUdateSuccessed = service.UpdateUser(dto);
      if(isUdateSuccessed) {
    	  return ResponseEntity.ok(true);
      }else {
    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
      }
   }
   
   @GetMapping("/deleteuser")
   public ResponseEntity<Boolean> deleteUser(@RequestParam("uid") String uid) {
	   
      boolean isDeleted = service.DeleteUser(uid);
      if(isDeleted) {
    	  return ResponseEntity.ok(true);
      }else {
    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
      }
   }
   
	//인증 페이지로 이동 시에 페이지 매핑
	@GetMapping("/zepidverify")
	public ResponseEntity<ZepIdVerifyViewDTO> ZepidVerifyView(@RequestParam("uid") String uid) {
	    ZepIdVerifyViewDTO result = service.CheckZepidAndVerified(uid);
	    if (result != null) {
	        return ResponseEntity.ok(result);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@PostMapping("/zepidverify")
	public ResponseEntity<String>ZepidVerify(@RequestBody ZepIdVerifyDTO m){
		int isVerifySuccessed = service.VerifyZepid(m, "sanghee_ok@naver.com");
		if(isVerifySuccessed == 1) {
			return ResponseEntity.ok("인증 성공");
		}else if(isVerifySuccessed == 2){
			return ResponseEntity.ok("이미 인증된 계정입니다.");
		}else if(isVerifySuccessed == 3) {
			return ResponseEntity.ok("인증 코드가 틀렸습니다.");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("내부 서버 에러");
		}
		
		
	}
 
//   예치금 업데이트
//	@PostMapping("/finddeposit")
//	public ResponseEntity login(@RequestParam("uid") String uid) {
//		String accessToken = kakaoLoginService.getAccessToken(code);
//		HashMap<String, Object> userInfo = kakaoLoginService.getUserInfo(accessToken);
//		
//		
//
//		Map<String, Object> responseBody = new HashMap<>();
//		responseBody.put("userId", userInfo.get("email"));
//		responseBody.put("accessToken", accessToken);
//		return ResponseEntity.ok(responseBody);
//	}
}