package com.planner.godsaeng.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.planner.godsaeng.dto.SaveResponseDTO;
import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.dto.UserListDTO;
import com.planner.godsaeng.dto.ZepIdVerifyDTO;
import com.planner.godsaeng.dto.ZepIdVerifyViewDTO;
import com.planner.godsaeng.security.jwt.JwtAuthentication;
import com.planner.godsaeng.security.jwt.JwtAuthenticationToken;
import com.planner.godsaeng.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
   UserService service;

   @PostMapping("/adduser")
   public ResponseEntity<Boolean> addUser(@RequestBody SaveResponseDTO userinfo, @AuthenticationPrincipal JwtAuthentication user) {
     
	 String u_id = user.userId;
     String u_nickname = userinfo.getU_nickname();
     String u_content = userinfo.getU_content();
     String u_zepid = userinfo.getU_zepid();
     String profile_image = userinfo.getProfile_image();
     
     UserDTO dto = new UserDTO(u_id, null, u_nickname, u_zepid, 0, 1, u_content, null, profile_image);

      boolean isAddSuccessed = service.InsertUser(dto);
      
      if(isAddSuccessed) {
         return ResponseEntity.ok(true);
      }else {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
      }
   }

   @Autowired
   private ObjectMapper objectMapper;

   @GetMapping("/readuser")
   public ResponseEntity<String> readUser(@AuthenticationPrincipal JwtAuthentication user) {

	   UserDTO userinfo = service.ReadUser(user.userId);

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
   public ResponseEntity<Boolean> updateUser(@RequestBody SaveResponseDTO userinfo, @AuthenticationPrincipal JwtAuthentication user) {
	  log.info("유저 롤:" + user.role);
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  if (authentication instanceof JwtAuthenticationToken) {
	      JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
	      Collection<? extends GrantedAuthority> authorities = jwtToken.getAuthorities();
	      System.out.println("유저 권한 정보"+authorities);
	  }
	  String u_id = user.userId;
	  String role = user.role;
	  String u_nickname = userinfo.getU_nickname();
	  String u_content = userinfo.getU_content();
	  String u_zepid = userinfo.getU_zepid();
	  String profile_image = userinfo.getProfile_image();

	  UserDTO dto = new UserDTO(u_id, role, u_nickname, u_zepid, 0, 1, u_content, null, profile_image);
      
      boolean isUdateSuccessed = service.UpdateUser(dto);
      if(isUdateSuccessed) {
    	  return ResponseEntity.ok(true);
      }else {
    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
      }
   }

   @GetMapping("/deleteuser")
   public ResponseEntity<Boolean> deleteUser(@AuthenticationPrincipal JwtAuthentication user) {

      boolean isDeleted = service.DeleteUser(user.userId);
      if(isDeleted) {
    	  return ResponseEntity.ok(true);
      }else {
    	  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
      }
   }

	//인증 페이지로 이동 시에 페이지 매핑
	@GetMapping("/zepidverify")
	public ResponseEntity<ZepIdVerifyViewDTO> ZepidVerifyView(@AuthenticationPrincipal JwtAuthentication user) {
	    ZepIdVerifyViewDTO result = service.CheckZepidAndVerified(user.userId);
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

	//관리자 모드
	@PostMapping("/userlist")
	public ResponseEntity<List<UserListDTO>> getUserList(){
		List<UserListDTO> userDTOList = service.getUserList();
		return ResponseEntity.ok(userDTOList);
	}
}
