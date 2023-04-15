package com.planner.godsaeng.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.repository.PlanRepository;
import com.planner.godsaeng.service.PlanService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
	
	@Autowired
	PlanService service;
	
	@PostMapping("/addplan")
	public String addPlan(@ModelAttribute PlanDTO d) {
		service.InsertPlan(d);
		return null;
	}
	
	@GetMapping("/index")
	public String Mainpage() {
		return "publishing/index";
	}
	@PostMapping("/refreshPlan")
	public String RefreshPlan() {
		return "";
	}
	
	@GetMapping("/dailyplan")
	public String listPlan(HttpSession session, Model model) {
		String currentuser_id = (String)(session.getAttribute("u_id"));
		currentuser_id = "sanghee";
		List<PlanDTO> list = service.ReadDailyPlan(currentuser_id);
		model.addAttribute("list",list);
		return "publishing/pages/planner";
	}
	
	@PostMapping("/updateplan")
	public String updatePlan(@ModelAttribute PlanDTO d) {
		service.UpdatePlan(d);
		return null;
		
	}
	@GetMapping("/deleteplan")
	public String deletePlan(@ModelAttribute PlanDTO d) {
		service.DeletePlan(d.getP_id());
		
		return null;
	}
	

}
