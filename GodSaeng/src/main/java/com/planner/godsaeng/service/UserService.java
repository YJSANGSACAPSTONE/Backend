package com.planner.godsaeng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.dto.UserRankDTO;
import com.planner.godsaeng.dto.ZepIdVerifyDTO;
import com.planner.godsaeng.dto.ZepIdVerifyViewDTO;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;

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
      User user = dtoToEntity(u);
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
	   return userRepository.findUzepidByUid(uid);
   }
   
   public ZepIdVerifyViewDTO CheckZepidAndVerified(String uid) {
	   System.out.println("uid잘들어옴?" + uid);
	   Optional<User> userEntity = userRepository.findUserByUid(uid);
	   ZepIdVerifyViewDTO list = null;
	   list = ZepIdVerifyViewDTO.builder()
			   .uzepid(userEntity.get().getUzepid())
			   .uverifiedornot(userEntity.get().getUverifiedornot())
			   .build();
	   return list;
	   
   }
   
   public int VerifyZepid(ZepIdVerifyDTO m,String uid) {
	   String currentVerifykey = userRepository.findUzepidByUid(uid);
	   System.out.println(m.getVerifykey() + "들어왔나?");
	   System.out.println(currentVerifykey);
	   if(m.getVerifykey().equals(currentVerifykey)) {
		      try {
		    	 userRepository.updateZepid(uid, m.getZepid());
		    	 System.out.println("인증 성공");
		         return 1;
		        
		      }catch(Exception e) {
		         e.printStackTrace();
		         System.out.println("이미 인증된 계정입니다.");
		         return 2;
		      }
		   }else {
			   System.out.println("인증 코드가 잘못되었습니다.");
			   return 3;
		   }

   }
   public List<UserRankDTO>ReadUsersRank(){
	   List<User>userListEntity = userRepository.findTop10UsersByScoreDesc();
	   List<UserRankDTO>userList = new ArrayList<>();
	   UserRankDTO list;
	   for(User u:userListEntity) {
		   list = UserRankDTO.builder()
				   .ulevel(u.getUlevel())
				   .unickname(u.getUnickname())
				   .build();
		   userList.add(list);
				   
	   }
	   return userList;
   }

   public UserDTO entityToDto(User u) {
	   return UserDTO.builder()
			   .u_id(u.getUid())
			   .u_nickname(u.getUnickname())
			   .u_zepid(u.getUzepid())
			   .u_deposit(u.getUdeposit())
			   .u_grade(u.getUgrade())
			   .u_level(u.getUlevel())
			   .u_content(u.getUcontent())
			   .u_successedchallenge(u.getUsuccessedchallenge())
			   .build();
   }
   
   public User dtoToEntity(UserDTO u) {
	   return User.builder()
			   .uid(u.getU_id())
			   .unickname(u.getU_nickname())
			   .uzepid(u.getU_zepid())
			   .udeposit(u.getU_deposit())
			   .ugrade(u.getU_grade())
			   .ulevel(u.getU_level())
			   .ucontent(u.getU_content())
			   .usuccessedchallenge(u.getU_successedchallenge())
			   .build();
   }

}

