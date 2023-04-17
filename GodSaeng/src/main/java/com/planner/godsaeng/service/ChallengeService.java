package com.planner.godsaeng.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {
	@Autowired
	private final ChallengeRepository challengeRepository;
	
	Challenge challenge = null;
	//챌린지 CREATE
	
	public boolean InsertChallenge(ChallengeDTO d) {
		challenge = Challenge.builder()
			.c_id(d.getC_id())
			.c_name(d.getC_name())
			.c_content(d.getC_content())
			.c_startdate(d.getC_startdate())
			.c_enddate(d.getC_enddate())
			.c_numberofparticipants(d.getC_numberofparticipants())
			.c_category(d.getC_category())
			.c_thumbnails(d.getC_thumbnails())
			.c_introduction(d.getC_introduction())
			.c_fee(d.getC_fee())
			.c_numberofphoto(d.getC_numberofphoto())
			.c_typeofverify(d.getC_typeofverify())
			.c_typeoffrequency(d.getC_typeoffrequency())
			.c_frequency(d.getC_frequency())
			.c_score(d.getC_score())
			.build();
		
		try {
			challengeRepository.save(challenge);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	//insert문 끝
	public List<ChallengeDTO>ReadPopularChallenge(){
		List<Challenge>popularListEntity=challengeRepository.findAllByOrderByStartdateDesc();
		List<ChallengeDTO>popularList=new ArrayList<>();
		
		for(Challenge e : popularListEntity) {
			popularList.add(
					ChallengeDTO.builder()
					.c_id(e.getC_id())
					.c_name(e.getC_name())
					.c_content(e.getC_content())
					.c_startdate(e.getC_startdate())
					.c_enddate(e.getC_enddate())
					.c_numberofparticipants(e.getC_numberofparticipants())
					.c_category(e.getC_category())
					.c_thumbnails(e.getC_thumbnails())
					.c_introduction(e.getC_introduction())
					.c_fee(e.getC_fee())
					.c_numberofphoto(e.getC_numberofphoto())
					.c_typeofverify(e.getC_typeofverify())
					.c_typeoffrequency(e.getC_typeoffrequency())
					.c_frequency(e.getC_frequency())
					.c_score(e.getC_score())
					.build()
			);
		}
		return popularList;
	}
	public List<ChallengeDTO>ReadRecentChallenge(){
		List<Challenge>recentListEntity = challengeRepository.findAllByOrderByNumberofparticipantsDesc();
		List<ChallengeDTO>recentList = new ArrayList<>();
		for(Challenge e : recentListEntity) {
			recentList.add(
					ChallengeDTO.builder()
					.c_id(e.getC_id())
					.c_name(e.getC_name())
					.c_content(e.getC_content())
					.c_startdate(e.getC_startdate())
					.c_enddate(e.getC_enddate())
					.c_numberofparticipants(e.getC_numberofparticipants())
					.c_category(e.getC_category())
					.c_thumbnails(e.getC_thumbnails())
					.c_introduction(e.getC_introduction())
					.c_fee(e.getC_fee())
					.c_numberofphoto(e.getC_numberofphoto())
					.c_typeofverify(e.getC_typeofverify())
					.c_typeoffrequency(e.getC_typeoffrequency())
					.c_frequency(e.getC_frequency())
					.c_score(e.getC_score())
					.build()
			);
		}
		return recentList;
	}
//	public List<ChallengeDTO>ReadMyChallenge(String u_id){
//		List<Challenge>myList = challengeRepository.findAll();
//	}

}
