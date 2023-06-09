package com.planner.godsaeng.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.planner.godsaeng.dto.KakaoApproveResponse;
import com.planner.godsaeng.dto.KakaoReadyResponse;
import com.planner.godsaeng.dto.PaymentDTO;
import com.planner.godsaeng.entity.Payment;
import com.planner.godsaeng.repository.KakaoPayRepository;
import com.planner.godsaeng.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {
	private final KakaoPayRepository kakaoPayRepository;
	private final UserRepository userRepository;
	
    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
    static final String admin_Key = "a061fa236640dfa13b0d0993ddcffb93"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
    private KakaoReadyResponse kakaoReady;
    
    public KakaoReadyResponse kakaoPayReady(String uid, int kpamount) {
    	String amount = Integer.toString(kpamount);
    	
         // 카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "partner_order_id");
        parameters.add("partner_user_id", uid);
        parameters.add("item_name", "예치금 충전");
        parameters.add("quantity", "1");
        parameters.add("total_amount", amount);
        parameters.add("vat_amount", "0");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:8070/kakaopay/approve"); // 성공 시 redirect url
        parameters.add("fail_url", "http://localhost:8070/kakaopay/fail"); // 실패 시 redirect url
        parameters.add("cancel_url", "http://localhost:8070/kakaopay/cancel"); // 취소 시 redirect url
        
        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);
        
        kakaoReady.setPartner_user_id(uid);
        
        return kakaoReady;
    }
    
    /**
     * 결제 완료 승인
     */
    public KakaoApproveResponse ApproveResponse(String pgToken) {
    
        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
        parameters.add("partner_order_id", "partner_order_id");
        parameters.add("partner_user_id", kakaoReady.getPartner_user_id());
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        
        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);
                
        return approveResponse;
    }
    
    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }

    public void SavePayment(Payment payment) {
    	kakaoPayRepository.save(payment);
    }
    
    public List<PaymentDTO> readPayment(String uid) {
        List<Payment> paymentList = kakaoPayRepository.findPaymentByUid(uid);
        List<PaymentDTO> paymentDTOList = new ArrayList<>();
        
        for (Payment p : paymentList) {
        	PaymentDTO paymentDTO = entityToDto(p);
            paymentDTOList.add(paymentDTO);
        }
        return paymentDTOList;
    }
    
    public PaymentDTO entityToDto(Payment p) {
    	return PaymentDTO.builder()
    			.kp_id(p.getKpid())
    			.kp_methodtype(p.getKpmethodtype())
    			.kp_date(p.getKpdate())
    			.kp_amount(p.getKpamount())
    			.build();
    }
    
    public Payment dtoToEntity(PaymentDTO p) {
    	return Payment.builder()
    			.kpid(p.getKp_id())
    			.kpmethodtype(p.getKp_methodtype())
    			.kpdate(p.getKp_date())
    			.kpamount(p.getKp_amount())
    			.build();
    }

}
