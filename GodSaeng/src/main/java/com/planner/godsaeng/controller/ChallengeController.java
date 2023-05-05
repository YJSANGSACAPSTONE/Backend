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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeParticipateDTO;
import com.planner.godsaeng.dto.ChallengeStatusDTO;
import com.planner.godsaeng.dto.ChallengeVerifyDTO;
import com.planner.godsaeng.dto.ZepRequestDTO;
import com.planner.godsaeng.repository.ChallengeRepository;
import com.planner.godsaeng.service.ChallengeService;
import com.planner.godsaeng.service.ChallengeVerifyService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@Controller
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
	@Autowired
	ChallengeService service;
	@Autowired
	ChallengeVerifyService verifyservice;
	
	@GetMapping("/addchallenge")
	public RedirectView AddChallengeView(@ModelAttribute ChallengeDTO d) {
		return null;
		
	}
	
	@PostMapping("/addchallenge")
	public ResponseEntity<Boolean> AddChallenge(@RequestParam("thumbnail") MultipartFile thumbnail,
	                                             @RequestBody ChallengeDTO d) throws IOException {
	    // 파일 저장 경로
	    String path = "/img/challengeimg";
	    // 파일 저장
	    if (!thumbnail.isEmpty()) {
	        String fileName = thumbnail.getOriginalFilename();
	        File dest = new File(path + File.separator + fileName);
	        thumbnail.transferTo(dest);
	        d.setThumbnailData(thumbnail);
	    }

	    boolean isAddSuccessed = service.InsertChallenge(d,thumbnail);

	    if (isAddSuccessed) {
	        return ResponseEntity.ok(true);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
	    }
	}
	@GetMapping("/list")
	public ResponseEntity<Map<String,List<ChallengeDTO>>>ReadChallengeList(HttpSession session){
		String uid = /*HttpSession.getAttribute(u_id)*/"sanghee_ok@naver.com";
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
	@GetMapping("/participate")
	public ResponseEntity<Boolean>ParticipateChallenge(@ModelAttribute ChallengeDTO m, HttpSession session){
//		String u_id = session.getAttribute("uid");
		boolean isParticipateSuccessed = true;
		if(isParticipateSuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	//인증 페이지로 이동 시에 페이지 매핑
	@GetMapping("/verify")
	public RedirectView VerifyChallengeView(@ModelAttribute ChallengeParticipateDTO m){
		return null;
			
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
	public ResponseEntity <List<ChallengeStatusDTO>>MyChallenge(HttpSession session){
		boolean isMychallengeSuccessed = true;
		if(isMychallengeSuccessed) {
			return ResponseEntity.ok(service.myChallengeProgress((String)session.getAttribute("u_id")));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
