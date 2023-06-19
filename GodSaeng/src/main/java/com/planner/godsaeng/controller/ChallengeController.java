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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.planner.godsaeng.dto.UserRankDTO;
import com.planner.godsaeng.dto.ZepIdVerifyDTO;
import com.planner.godsaeng.dto.ZepIdVerifyViewDTO;
import com.planner.godsaeng.dto.ZepRequestDTO;
import com.planner.godsaeng.repository.ChallengeRepository;
import com.planner.godsaeng.security.jwt.JwtAuthentication;
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
	
	//챌린지추가
	@PostMapping("/addchallenge")
	public ResponseEntity<Boolean> AddChallenge(@AuthenticationPrincipal JwtAuthentication user, @RequestParam("thumbnail") MultipartFile thumbnail,
	                                             @ModelAttribute ChallengeDTO d) throws IOException {
		
	    boolean isAddSuccessed = service.InsertChallenge(d,thumbnail);

	    if (isAddSuccessed) {
	        return ResponseEntity.ok(true);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    }
	}
	//챌린지 참가 취소(나가기)
	@GetMapping("/quitchallenge")
	public ResponseEntity<Boolean>quitChallenge(@AuthenticationPrincipal JwtAuthentication user, Long cid){
		boolean isQuitSuccessed = participateService.LeftChallenge(cid, user.getUserId());
		if(isQuitSuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	@GetMapping("/list")
	public ResponseEntity<Map<String,List<ChallengeDTO>>>ReadChallengeList(@AuthenticationPrincipal JwtAuthentication user, String uid){
		List<ChallengeDTO>popularlist = service.ReadPopularChallenge();
		List<ChallengeDTO>recentlist = service.ReadRecentChallenge();
		List<ChallengeDTO>mylist = service.ReadMyChallenge(uid);
//		List<UserRankDTO>userranklist = userService.ReadUsersRank();
		Map<String,List<ChallengeDTO>>lists = new HashMap<>();
		lists.put("recentlist", recentlist);
		lists.put("popularlist", popularlist);
		lists.put("mylist", mylist);
		
		return new ResponseEntity<>(lists,HttpStatus.OK);
		
	}
	@GetMapping("/alllist")
	public ResponseEntity<List<ChallengeDTO>>ReadAllChallenge(@AuthenticationPrincipal JwtAuthentication user){
		List<ChallengeDTO>allList = service.ReadAllChallenge();
		if(!allList.isEmpty()) {
			return ResponseEntity.ok(allList);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	@PostMapping("/update")
	public ResponseEntity<ChallengeDTO>UpdateChallenge(@AuthenticationPrincipal JwtAuthentication user,@RequestBody ChallengeDTO d){
		boolean isSuccess = service.UpdateChallenge(d);
		if(isSuccess) {
			return ResponseEntity.ok(d);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Boolean>DeleteChallenge(@AuthenticationPrincipal JwtAuthentication user,@RequestBody ChallengeDTO d){
		boolean isDeleteSuccessed = service.DeleteChallenge(d.getC_id());
		if(isDeleteSuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	@GetMapping("/{cid}/signUp")
	public ResponseEntity<List<ChallengeDTO>>signUpView(@PathVariable("cid")Long cid){
		//
		return ResponseEntity.ok(service.ReadChallenge(cid));
	}
	
	
	//챌린지 참가 신청 시 실행 메서드
	@PostMapping("/participate")
	public ResponseEntity<Boolean>ParticipateChallenge(@AuthenticationPrincipal JwtAuthentication user, @RequestBody ChallengeDTO m, String uid){
		System.out.println("아이디는: " + uid);
		System.out.println("챌린지 이름은: " + m.getC_name());
		boolean isParticipateSuccessed = participateService.ParticipateChallenge(m, uid);
		if(isParticipateSuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	//인증 확인 submit버튼을 눌렀을시 데이터 삽입 메서드 
	@PostMapping("/adminverify")
	public ResponseEntity<Boolean>VerifyChallenge(@AuthenticationPrincipal JwtAuthentication user, @ModelAttribute ChallengeParticipateDTO m){
		boolean isVerifySuccessed = true;
		if(isVerifySuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	@PostMapping("/verify")
	public ResponseEntity<Boolean>VerifyNormalChallenge(@ModelAttribute ChallengeVerifyDTO v, @AuthenticationPrincipal JwtAuthentication user, @RequestParam("verifyPhoto") MultipartFile verifyPhoto)throws IOException{
		System.out.println(v.getCid());
		boolean isVerifySuccessed = verifyservice.InsertNormalChallengeVerifyData(v, verifyPhoto);
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
	public List<ChallengeStatusDTO>MyChallenge(@AuthenticationPrincipal JwtAuthentication user, String uid){
		System.out.println(uid + "------------");
		return service.myChallengeProgress(uid);
	}
}
