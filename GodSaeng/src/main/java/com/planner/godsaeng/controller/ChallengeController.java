package com.planner.godsaeng.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeParticipateDTO;
import com.planner.godsaeng.dto.ChallengeStatusDTO;
import com.planner.godsaeng.dto.ChallengeVerifyDTO;
import com.planner.godsaeng.dto.ZepIdVerifyDTO;
import com.planner.godsaeng.dto.ZepRequestDTO;
import com.planner.godsaeng.repository.ChallengeRepository;
import com.planner.godsaeng.service.ChallengeParticipateService;
import com.planner.godsaeng.service.ChallengeService;
import com.planner.godsaeng.service.ChallengeVerifyService;
import com.planner.godsaeng.service.UserService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
	@Autowired
	ChallengeService service;
	@Autowired
	ChallengeVerifyService verifyservice;
	@Autowired
	ChallengeParticipateService participateService;
	@Autowired
	UserService userService;
	
	@GetMapping("/addchallenge")
	public RedirectView AddChallengeView(@RequestBody ChallengeDTO d) {
		return null;
		
	}
	
	@PostMapping("/addchallenge")
	public ResponseEntity<Boolean> AddChallenge(@RequestParam("thumbnail") MultipartFile thumbnail,
	                                             @ModelAttribute ChallengeDTO d) throws IOException {
		
	    boolean isAddSuccessed = service.InsertChallenge(d,thumbnail);

	    if (isAddSuccessed) {
	        return ResponseEntity.ok(true);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    }
	}
	@GetMapping("/list")
	public ResponseEntity<Map<String,List<ChallengeDTO>>>ReadChallengeList(String uid){
		List<ChallengeDTO>popularlist = service.ReadPopularChallenge();
		List<ChallengeDTO>recentlist = service.ReadRecentChallenge();
		List<ChallengeDTO>mylist = service.ReadMyChallenge(uid);
		
		Map<String,List<ChallengeDTO>>lists = new HashMap<>();
		lists.put("recentlist", recentlist);
		lists.put("popularlist", popularlist);
		lists.put("mylist", mylist);
		
		return new ResponseEntity<>(lists,HttpStatus.OK);
		
	}
	@PostMapping("/update")
	public ResponseEntity<ChallengeDTO>UpdateChallenge(@RequestBody ChallengeDTO d){
		boolean isSuccess = service.UpdateChallenge(d);
		if(isSuccess) {
			return ResponseEntity.ok(d);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("/update")
	public RedirectView UpdateChallengeView() {
		return null;
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Boolean>DeleteChallenge(@RequestBody ChallengeDTO d){
		boolean isDeleteSuccessed = service.DeleteChallenge(d.getC_id());
		if(isDeleteSuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	@GetMapping("/{cid}/signUp")
	public ResponseEntity<List<ChallengeDTO>>signUpView(@PathVariable("cid")Long cid){
		
		return ResponseEntity.ok(service.ReadChallenge(cid));
	}
	
	
	//챌린지 참가 신청 시 실행 메서드
	@PostMapping("/participate")
	public ResponseEntity<Boolean>ParticipateChallenge(@RequestBody ChallengeDTO m, String uid){
		System.out.println("아이디는: " + uid);
		System.out.println("챌린지 이름은: " + m.getC_name());
		boolean isParticipateSuccessed = participateService.ParticipateChallenge(m, uid);
		if(isParticipateSuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	//인증 페이지로 이동 시에 페이지 매핑
	@GetMapping("/zepidverify")
	public String ZepidVerifyView(String uid){
		return userService.FindZepidByuID(uid);
			
		}
	@PostMapping("/zepidverify")
	public ResponseEntity<String>ZepidVerify(@RequestBody ZepIdVerifyDTO m){
		int isVerifySuccessed = userService.VerifyZepid(m, "sanghee_ok@naver.com");
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
	
	
	//인증 확인 submit버튼을 눌렀을시 데이터 삽입 메서드 
	@PostMapping("/verify")
	public ResponseEntity<Boolean>VerifyChallenge(@ModelAttribute ChallengeParticipateDTO m){
		boolean isVerifySuccessed = true;
		if(isVerifySuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	//젶인증
	@PostMapping("/zepverify")
	public ResponseEntity<Boolean>ZepVerify(@RequestBody ZepRequestDTO m){
		boolean isVerifySuccessed = verifyservice.VerifyMetaChallenge(m);
		if(isVerifySuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	//챌린지 참가 현황 눌렀을 시 데이터 출력 메서드
	@GetMapping("/mychallenge")
	public List<ChallengeStatusDTO>MyChallenge(String uid){
		return service.myChallengeProgress(uid);
	}
}
