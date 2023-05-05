package com.planner.godsaeng.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.KakaoLoginRepository;

@Service
public class KakaoLoginService {

   public String getAccessToken(String code) {
      String accessToken = "";
      String refreshToken = "";
      String reqURL = "https://kauth.kakao.com/oauth/token";
      
      try {
         URL url = new URL(reqURL);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("POST");
         conn.setDoOutput(true);
         
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
         StringBuilder sb = new StringBuilder();
         sb.append("grant_type=authorization_code");
         sb.append("&client_id=87c054c34eca4ca3541ab083e086cd12");
         sb.append("&redirect_uri=http://localhost:3000/kakaoLogin");
         sb.append("&code="+code);
         
         bw.write(sb.toString());
         bw.flush();
         
         int responseCode = conn.getResponseCode();
         System.out.println("response code = " + responseCode);
         
         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         
         String line = "";
         String result = "";
         while((line = br.readLine())!=null) {
            result += line;
         }
         System.out.println("response body = "+result);
         
         JsonParser parser = new JsonParser();
         JsonElement element = parser.parse(result);
         
         accessToken = element.getAsJsonObject().get("access_token").getAsString();
         refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
         
         br.close();
         bw.close();
      }catch (Exception e) {
         e.printStackTrace();
      }
      return accessToken;
   }

   
   public HashMap<String, Object> getUserInfo(String accessToken) {
      HashMap<String, Object> userInfo = new HashMap<String, Object>();
      String reqUrl = "https://kapi.kakao.com/v2/user/me";
      try {
         URL url = new URL(reqUrl);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("POST");
         conn.setRequestProperty("Authorization", "Bearer " + accessToken);
         int responseCode = conn.getResponseCode();
         System.out.println("responseCode =" + responseCode);
         
         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         
         String line = "";
         String result = "";
         
         while((line = br.readLine()) != null) {
            result += line;
         }
         System.out.println("response body ="+result);
         
         JsonParser parser = new JsonParser();
         JsonElement element =  parser.parse(result);
         
         JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
         JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
         
         String nickname = properties.getAsJsonObject().get("nickname").getAsString();
         String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
         String profileImageUrl = properties.getAsJsonObject().get("profile_image").getAsString();
         
         userInfo.put("nickname", nickname);
         userInfo.put("email", email);
         userInfo.put("profileImageUrl", profileImageUrl);
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      return userInfo;
   }


//   public void kakaoLogout(String accessToken) {
//      String reqURL = "http://kapi.kakao.com/v1/user/logout";
//      try {
//         URL url = new URL(reqURL);
//         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//         conn.setRequestMethod("POST");
//         conn.setRequestProperty("Authorization", "Bearer " + accessToken);
//         int responseCode = conn.getResponseCode();
//         System.out.println("responseCode = " + responseCode);
//         
//         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//         
//         String result = "";
//         String line = "";
//         
//         while((line = br.readLine()) != null) {
//            result+=line;
//         }
//         System.out.println(result);
//      } catch (FileNotFoundException e) {
//         System.out.println("Invalid access token. Please log in again.");
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }
   
   @Autowired
   KakaoLoginRepository kakaoLoginRepository;
   
   public boolean checkUser(String uid) {
      System.out.println("테스트:" + uid);
      Optional<User> result = kakaoLoginRepository.findById(uid);
      if(result.isPresent()) {
         return true;
      } else {
         return false;
      }
   }
   


}