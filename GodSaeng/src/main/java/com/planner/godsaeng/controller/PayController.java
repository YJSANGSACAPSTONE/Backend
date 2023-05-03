//package com.planner.godsaeng.controller;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.planner.godsaeng.dto.Approval;
//import com.planner.godsaeng.service.KakaoPayService;
//
//@Controller //restController와의 차이
//@RequestMapping("/kakaopay")
//public class PayController {
//	
//	@GetMapping("/payment")
//	public String paymeant() {
//		return "kakaopay/payment";
//	}
//	
////	@GetMapping("/approve")
////	public String approve() {
////		return "approve";
////	}
////	
////	@GetMapping("/fail")
////	public String fail() {
////		return "fail";
////	}
////	
////	@GetMapping("/cancel")
////	public String cancel() {
////		return "cancel";
////	}
//	@Autowired
//	KakaoPayService service;
//	
////	@RequestMapping("/approve")
////	public ModelAndView approve(@RequestParam("pg_token") String pg_token, ModelAndView mv) {
////	    // 결제 승인 요청 전송
////	    Approval approval = service.approve(pg_token);
////
////	    // ModelAndView 객체에 데이터를 담아서 view로 전달
//////	    mv.addObject("paymentTime", approval.getApproved_at());
////	    mv.addObject("partnerOrderId", approval.getPartner_order_id());
//////	    mv.addObject("itemName", approval.getItem_name());
//////	    mv.addObject("quantity", approval.getQuantity());
////	    mv.addObject("totalAmount", approval.getAmount().getTotal());
//////	    mv.addObject("paymentMethodType", approval.getPayment_method_type());
////	    mv.setViewName("kakaopay/approve");
////
////	    return mv;
////	}
//	
//	@RequestMapping("/cancel")
//	public ModelAndView cancel(ModelAndView mv) {
//		mv.setViewName("kakaopay/cancel");
//		return mv;
//	}
//	
//	@RequestMapping("/pay")
//	@ResponseBody
//	public ModelAndView kakaopay() {
//		ModelAndView mav = new ModelAndView();
//		try {
//			URL address = new URL("https://kapi.kakao.com/v1/payment/ready");
//			HttpURLConnection con = (HttpURLConnection) address.openConnection();
//			con.setRequestMethod("POST");
//			con.setRequestProperty("Authorization", "KakaoAK a061fa236640dfa13b0d0993ddcffb93");
//			con.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//			con.setDoOutput(true);
//			
//			String cid = "TC0ONETIME";
//	        String partner_order_id = "partner_order_id";
//	        String partner_user_id = "partner_user_id";
//	        String item_name = "초코파이";
//	        int quantity = 1;
//	        int total_amount = 2200;
//	        int tax_free_amount = 0;
//	        String approval_url = "http://localhost:8070/kakaopay/approve";
//	        String fail_url = "http://localhost:8070/kakaopay/fail";
//	        String cancel_url = "http://localhost:8070/kakaopay/cancel";
//	        String param = "cid=" + cid + "&partner_order_id=" + partner_order_id
//	                + "&partner_user_id=" + partner_user_id + "&item_name=" + item_name
//	                + "&quantity=" + quantity + "&total_amount=" + total_amount
//	                + "&tax_free_amount=" + tax_free_amount + "&approval_url=" + approval_url
//	                + "&fail_url=" + fail_url + "&cancel_url=" + cancel_url;
//	        
////			String param = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&tax_free_amount=0&approval_url=http://localhost:8070/kakaopay/approve&fail_url=http://localhost:8070/kakaopay/fail&cancel_url=http://localhost:8070/kakaopay/cancel";
//			OutputStream ops = con.getOutputStream();
//			DataOutputStream dops = new DataOutputStream(ops);
//			dops.writeBytes(param);
//			dops.close();
//			
//			int result = con.getResponseCode();
//			
//			InputStream ips;
//			if(result == 200) {
//				ips = con.getInputStream();
//			}else {
//				ips = con.getErrorStream();
//			}
//			InputStreamReader isr = new InputStreamReader(ips);
//			BufferedReader br = new BufferedReader(isr);
//			String rs =br.readLine();
//			JSONObject json = new JSONObject(rs); // JSON 문자열을 JSONObject로 파싱
//			String nextRedirectPcUrl = json.getString("next_redirect_pc_url"); // next_redirect_pc_url 값 추출
//			mav.addObject("next_redirect_pc_url", nextRedirectPcUrl);
////			mav.addObject("next_redirect_pc_url", rs);
//			mav.setViewName("kakaopay/payment");
//			return mav;
////			return br.readLine();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mav.setViewName("kakaopay/fail");
//		return mav;
////		return "{\"result\":\"NO\"}";
//	}
//}
