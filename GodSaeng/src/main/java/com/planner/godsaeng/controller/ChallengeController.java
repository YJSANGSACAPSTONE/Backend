package com.planner.godsaeng.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@Controller
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
	@Autowired
	ChallengeService service;
	
	@PostMapping("/addchallenge")
	public ResponseEntity<Boolean>AddChallenge(@RequestBody ChallengeDTO d){
		boolean isAddSuccessed = service.InsertChallenge(d);
		if(isAddSuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	
	}
	@GetMapping("/list")
	public ResponseEntity<Map<String,List<ChallengeDTO>>>ReadChallengeList(HttpSession session){
		String uid = /*HttpSession.getAttribute(u_id)*/"hwangju";
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
	public String UpdateChallengeView() {
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
}
