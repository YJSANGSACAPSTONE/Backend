package com.planner.godsaeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.planner.godsaeng.dto.KakaoApproveResponse;
import com.planner.godsaeng.dto.KakaoReadyResponse;
import com.planner.godsaeng.dto.PaymentDTO;
import com.planner.godsaeng.entity.Payment;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.service.KakaoPayService;
import com.planner.godsaeng.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/kakaopay")
@RequiredArgsConstructor
@Transactional
public class KakaoPayController {

    private final KakaoPayService kakaoPayService;
    private final UserService userService;
    
	@GetMapping("/payment")
	public String payment() {
		return "kakaopay/payment";
	}
    
    /**
     * 결제요청
     */
    @RequestMapping("/ready")
    @ResponseBody
    public ResponseEntity readyToKakaoPay(@RequestParam("uid") String uid, @RequestParam("kpamount") int kpamount) {
//    	HttpSession session_uid = request.getSession();
//    	session.setAttribute("userid", "test1234");
//    	
    	ModelAndView mv = new ModelAndView();
    	KakaoReadyResponse kakaoReady = kakaoPayService.kakaoPayReady(uid, kpamount);
    	String nextRedirectPcUrl = kakaoReady.getNext_redirect_pc_url();
//    	mv.addObject("next_redirect_pc_url", nextRedirectPcUrl);
//    	mv.setViewName("kakaopay/payment");
    	Map<String, Object> responseBody = new HashMap<>();
    	responseBody.put("nextRedirectPcUrl", nextRedirectPcUrl);
    	return ResponseEntity.ok(responseBody);
    }
    
    /**
     * 결제 성공
     */
    @RequestMapping("/approve")
    public String afterPayRequest(@Value("${frontend.base.url}") final String frontendBaseUrl,@RequestParam("pg_token") String pgToken) {
    	
    	ModelAndView mv = new ModelAndView();
        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken);
        
//      결제정보 저장
        String uid = kakaoApprove.getPartner_user_id();
        Optional<User> userEntity = userService.SearchId(uid);
        
        Payment payment = Payment.builder()
        		.kpmethodtype(kakaoApprove.getPayment_method_type())
        		.kpdate(kakaoApprove.getApproved_at())
        		.kpamount(kakaoApprove.getAmount().getTotal())
        		.user(userEntity.get())
        		.build();
        
        kakaoPayService.SavePayment(payment);
        userService.AddDeposit(uid, kakaoApprove.getAmount().getTotal());
        
//        mv.addObject("kakaoApprove", kakaoApprove);
//        mv.setViewName("kakaopay/approve");
        return "redirect:" + frontendBaseUrl + "/JobComplete";
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
    
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUid(@RequestParam("uid") String uid) {
        List<PaymentDTO> paymentDTOList = kakaoPayService.readPayment(uid);
        return ResponseEntity.ok(paymentDTOList);
    }
    
}
