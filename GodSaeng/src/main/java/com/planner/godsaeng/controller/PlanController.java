package com.planner.godsaeng.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.repository.PlanRepository;
import com.planner.godsaeng.service.PlanService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plan")
@CrossOrigin(origins = "http://localhost:3000") // React app URL

public class PlanController {
	
	@Autowired
	PlanService service;
	
	private static final Logger logger = LogManager.getLogger(PlanController.class);
	
	@PostMapping("/addplan")
	public ResponseEntity<Boolean> addPlan(@RequestBody PlanDTO d) {
		boolean isAddSuccessed = service.InsertPlan(d);
		if(isAddSuccessed) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
		
	}
	
	//로그인 후 메인 화면에 보이는 오늘의 일정 출력 메서드
	@GetMapping("/dailyplan")
	public ResponseEntity<List<PlanDTO>> listPlan(HttpSession session) {
		String currentuser_id = (String)(session.getAttribute("u_id"));
		currentuser_id = "sanghee_ok@naver.com";
		List<PlanDTO> list = service.ReadDailyPlan(currentuser_id);
		return ResponseEntity.ok(list);
		
	}
	
	//업데이트 시 실시간으로 업데이트된 데이터를 출력할 수 있도록 하는 메서드
	@PostMapping("/updateplan")
	public ResponseEntity<PlanDTO> updatePlan(@RequestBody PlanDTO d) {
		logger.info("addPlan 요청 도착: {}", d.getU_id());

		boolean isSuccess = service.UpdatePlan(d);
		//업데이트 서비스메서드를 실행한 결과를 isSuccess 변수에 담아줌.
		if(isSuccess) {
			return ResponseEntity.ok(d);
			//성공일때, responseentity클래스를 활용하여 responsebody에 DTO d를 담아줌
		}else {
			//실패일때 에러 출력
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//일정 삭제 메서드
	@GetMapping("/deleteplan/{p_id}")
	public ResponseEntity<Boolean> deletePlan(@PathVariable("p_id") Long p_id) {
	    boolean isDeleted = service.DeletePlan(p_id);
	    if (isDeleted) {
	        return ResponseEntity.ok(true);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(false);
	    }
	}
//  다른 방법 고민중.	
//	@RequestMapping(value = "/deleteplan/{p_id}", method = RequestMethod.DELETE)
//	public Boolean deletePlan(@PathVariable Long p_id) {
//		return service.DeletePlan(p_id);
//		
//	}
//	

}
