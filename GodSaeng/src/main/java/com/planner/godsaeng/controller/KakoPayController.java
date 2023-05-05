package com.planner.godsaeng.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.planner.godsaeng.dto.KakaoApproveResponse;
import com.planner.godsaeng.dto.KakaoReadyResponse;
import com.planner.godsaeng.service.KakaoPayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/kakaopay")
@RequiredArgsConstructor
public class KakoPayController {

    private final KakaoPayService kakaoPayService;
    
	@GetMapping("/payment")
	public String paymeant() {
		return "kakaopay/payment";
	}
    
    /**
     * 결제요청
     */
    @RequestMapping("/ready")
    @ResponseBody
    public ModelAndView readyToKakaoPay() {
    	
    	ModelAndView mv = new ModelAndView();
    	KakaoReadyResponse kakaoReady = kakaoPayService.kakaoPayReady();
    	String nextRedirectPcUrl = kakaoReady.getNext_redirect_pc_url();
    	mv.addObject("next_redirect_pc_url", nextRedirectPcUrl);
    	mv.setViewName("kakaopay/payment");
    	return mv;
    }
    
    /**
     * 결제 성공
     */
    @RequestMapping("/approve")
    public ModelAndView afterPayRequest(@RequestParam("pg_token") String pgToken) {
    	
    	ModelAndView mv = new ModelAndView();
        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);

        mv.addObject("kakaoApprove", kakaoApprove);
        mv.setViewName("kakaopay/approve");
        return mv;
//        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
	public ModelAndView cancel(ModelAndView mv) {
		mv.setViewName("kakaopay/cancel");
		return mv;
	}

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public ModelAndView fail(ModelAndView mv) {
    	mv.setViewName("kakaopay/fail");
    	return mv;
    }
}
