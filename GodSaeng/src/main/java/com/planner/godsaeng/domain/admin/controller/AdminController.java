package com.planner.godsaeng.domain.admin.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.domain.admin.dto.AdminResponseDTO;
import com.planner.godsaeng.domain.admin.service.AdminService;
import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeVerifyDTO;
import com.planner.godsaeng.security.jwt.JwtAuthentication;
import com.planner.godsaeng.service.BoardService;
import com.planner.godsaeng.service.BoardServiceImpl;
import com.planner.godsaeng.service.ChallengeService;
import com.planner.godsaeng.service.ChallengeVerifyService;
import com.planner.godsaeng.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
@RestController
public class AdminController {
	
	
	private final ChallengeVerifyService verifyService;
	private final ChallengeService challengeService;
	private final UserService userService;
	private final AdminService adminService;
	private final BoardServiceImpl boardServiceImpl;

	
	//챌린지 인증내역에 들어갔을 때, 관리자가 볼 수 있는 페이지
	@GetMapping("/challengelist")
	public ResponseEntity<List<ChallengeDTO>>listChallenge(@AuthenticationPrincipal JwtAuthentication user){
		List<ChallengeDTO>result = adminService.findActiveChallenges();
		if(!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GetMapping("/verifylist/{cid}")
	public ResponseEntity<List<ChallengeVerifyDTO>>verifyNormalChallenge(@AuthenticationPrincipal JwtAuthentication user,@PathVariable Long cid){
		System.out.println(cid);
		List<ChallengeVerifyDTO>result = adminService.findVerifyLists(cid);
		if(!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("/verifythischallenge/{cvid}")
	public ResponseEntity<Boolean>verifyThisChallenge(@AuthenticationPrincipal JwtAuthentication user,@PathVariable Long cvid){
		boolean isVerifySuccessed = adminService.verifyParticipate(cvid);
		if(isVerifySuccessed = true) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	//@RequestMapping("/statistic")
	
	
	@GetMapping("/statistic/monthly")
	public ResponseEntity<Map<Integer, Long>> getMonthlyData(@AuthenticationPrincipal JwtAuthentication user) {
        Map<Integer, Long> monthlyData = verifyService.countVerifyByMonthRange();
        return ResponseEntity.ok(monthlyData);
    }
	
	@GetMapping("/statistic/daily")
	public ResponseEntity<Map<LocalDate,Long>>getDailyData(@AuthenticationPrincipal JwtAuthentication user){
		Map<LocalDate, Long> dailyData = verifyService.countVerifyByRecentDays();
		return ResponseEntity.ok(dailyData);
	}
	
//	@GetMapping("/list")
//	public ResponseEntity<Map<String,List<ChallengeDTO>>>ReadChallengeList(@AuthenticationPrincipal JwtAuthentication user, String uid){
//		List<ChallengeDTO>popularlist = service.ReadPopularChallenge();
//		List<ChallengeDTO>recentlist = service.ReadRecentChallenge();
//		List<ChallengeDTO>mylist = service.ReadMyChallenge(uid);
////		List<UserRankDTO>userranklist = userService.ReadUsersRank();
//		Map<String,List<ChallengeDTO>>lists = new HashMap<>();
//		lists.put("recentlist", recentlist);
//		lists.put("popularlist", popularlist);
//		lists.put("mylist", mylist);
//		
//		return new ResponseEntity<>(lists,HttpStatus.OK);
//		
//	}
	
	@GetMapping("/statistic")
	public ResponseEntity<Map<String, Object>> adminMainPage(@AuthenticationPrincipal JwtAuthentication user) {
	    List<Object[]> challengeVerifyList = adminService.adminChallengeInfo();
	    List<BoardDTO> boardList = boardServiceImpl.findByAll();

	    Map<String, Object> lists = new HashMap<>();
	    lists.put("verifyList", challengeVerifyList);

	    Map<Integer, Long> monthlylist = verifyService.countVerifyByMonthRange();
	    lists.put("monthlyList", monthlylist);
	    lists.put("boardList", boardList);

	    return ResponseEntity.ok(lists);
	}
	
	
	

}
