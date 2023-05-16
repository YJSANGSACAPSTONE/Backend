package com.planner.godsaeng.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.dto.ZepIdVerifyDTO;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.UserRepository;

@Service
public class UserService {
   
   @Autowired
   UserRepository userRepository;
   
   User user = null;
   
   //유저 회원가입(INSERT)
   public boolean InsertUser(UserDTO u) {
	  Random r = new Random();
	  int randomnumber = r.nextInt(100000);
      user = User.builder()
            .uid(u.getU_id())
            .unickname(u.getU_nickname())
            .uzepid(u.getU_id() + randomnumber)
            .udeposit(u.getU_deposit())
            .ugrade(u.getU_grade())
            .ulevel(u.getU_level())
            .ucontent(u.getU_content())
            .usuccessedchallenge(u.getU_successedchallenge())
            .build();
      
      try {
         userRepository.save(user);
         return true;
      }catch(Exception e) {
         e.printStackTrace();
         return false;
      }
   }
   
   //유저 정보 목록
   public UserDTO ReadUser(String uid){
     
      UserDTO userinfo = null;
      Optional<User> result = userRepository.findById(uid);
       
      if(result.isPresent()) {
          userinfo = UserDTO.builder()
         .u_id(result.get().getUid())
         .u_nickname(result.get().getUnickname())
         .u_zepid(result.get().getUzepid())


         .u_deposit(result.get().getUdeposit())
         .u_grade(result.get().getUgrade())
         .u_level(result.get().getUlevel())
         .u_content(result.get().getUcontent())
         .u_successedchallenge(result.get().getUsuccessedchallenge())
         .build();
            return userinfo;
      } else {
         return null;
      }
        
   }

   
   //유저 정보 수정
   public boolean UpdateUser(UserDTO u) {
      user = User.builder()
            .uid(u.getU_id())
            .unickname(u.getU_nickname())
            .uzepid(u.getU_zepid())
            .udeposit(u.getU_deposit())
            .ugrade(u.getU_grade())
            .ulevel(u.getU_level())
            .ucontent(u.getU_content())
            .usuccessedchallenge(u.getU_successedchallenge())
            .build();
      try {
         userRepository.save(user);
         return true;
      }catch(Exception e) {
         e.printStackTrace();
         return false;
      }
   }
   
   //유저 정보 삭제
   public boolean DeleteUser(String u_id) {
      try {
         userRepository.deleteById(u_id);
         return true;
      }catch(Exception e) {
         e.printStackTrace();
         return false;
      }
   }
   
   public void AddDeposit(String uid, int newDeposit) {
	   userRepository.addDeposit(uid, newDeposit);
   }
   
   public Optional<User> SearchId(String uid) {
	   Optional<User> userEntity = userRepository.findById(uid);
	   return userEntity;
   }
   
   public String FindZepidByuID(String uid) {
	   return userRepository.findUzepidByUid("sanghee_ok@naver.com");
   }
   
   public Boolean VerifyZepid(ZepIdVerifyDTO m,String uid) {
	   
	   String currentVerifykey = userRepository.findUzepidByUid(uid);
	   System.out.println(m.getVerifykey() + "들어왔나?");
	   System.out.println(currentVerifykey);
	   if(m.getVerifykey().equals(currentVerifykey)) {
		      try {
		    	 userRepository.updateZepid(uid, m.getZepid());
		         return true;
		      }catch(Exception e) {
		         e.printStackTrace();
		         System.out.println("오류");
		         return false;
		      }
		   }else {
			   System.out.println("휴");
			   return false;
		   }
	   
   }
   
   
}