package com.planner.godsaeng.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeSearchService {
	
	private final ChallengeRepository challengeRepository;
	Challenge challenge = null;
	
	public List<ChallengeDTO>ChallengeSearchByName(String keyword) {
		
		List<Challenge>searchByChallengeNameEntity;
		List<ChallengeDTO>searchByChallengeName =new ArrayList<>();
		try {
			searchByChallengeNameEntity = challengeRepository.findByCnameContaining(keyword);
		for(Challenge e : searchByChallengeNameEntity) {
			searchByChallengeName.add(
					ChallengeDTO.builder()
					.c_id(e.getCid())
					.c_name(e.getCname())
					.c_content(e.getCcontent())
					.c_startdate(e.getCstartdate())
					.c_enddate(e.getCenddate())
					.c_numberofparticipants(e.getCnumberofparticipants())
					.c_category(e.getCcategory())
					.c_thumbnails(e.getCthumbnails())
					.c_introduction(e.getCintroduction())
					.c_fee(e.getCfee())
					.c_numberofphoto(e.getCnumberofphoto())
					.c_typeofverify(e.getCtypeofverify())
					.c_typeoffrequency(e.getCtypeoffrequency())
					.c_frequency(e.getCfrequency())
					.c_score(e.getCscore())
					.build()
			);	
		}
		return searchByChallengeName;
		
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<ChallengeDTO>ChallengeSearchByContent(String keyword) {
		List<Challenge>searchByChallengeContentEntity;
		List<ChallengeDTO>searchByChallengeContent =new ArrayList<>();
		try {
			searchByChallengeContentEntity = challengeRepository.findByCcontentContaining(keyword);
			for(Challenge e : searchByChallengeContentEntity) {
				searchByChallengeContent.add(
						ChallengeDTO.builder()
						.c_id(e.getCid())
						.c_name(e.getCname())
						.c_content(e.getCcontent())
						.c_startdate(e.getCstartdate())
						.c_enddate(e.getCenddate())
						.c_numberofparticipants(e.getCnumberofparticipants())
						.c_category(e.getCcategory())
						.c_thumbnails(e.getCthumbnails())
						.c_introduction(e.getCintroduction())
						.c_fee(e.getCfee())
						.c_numberofphoto(e.getCnumberofphoto())
						.c_typeofverify(e.getCtypeofverify())
						.c_typeoffrequency(e.getCtypeoffrequency())
						.c_frequency(e.getCfrequency())
						.c_score(e.getCscore())
						.build()
				);	
			}
			return searchByChallengeContent;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
		}
	}
	public List<ChallengeDTO> ChallengeSearchByCategory(String keyword) {
		List<Challenge>searchByChallengeCategoryEntity;
		List<ChallengeDTO>searchByChallengeCategory =new ArrayList<>();
		try {
			searchByChallengeCategoryEntity = challengeRepository.findByCcontentContaining(keyword);
			for(Challenge e : searchByChallengeCategoryEntity) {
				searchByChallengeCategory.add(
						ChallengeDTO.builder()
						.c_id(e.getCid())
						.c_name(e.getCname())
						.c_content(e.getCcontent())
						.c_startdate(e.getCstartdate())
						.c_enddate(e.getCenddate())
						.c_numberofparticipants(e.getCnumberofparticipants())
						.c_category(e.getCcategory())
						.c_thumbnails(e.getCthumbnails())
						.c_introduction(e.getCintroduction())
						.c_fee(e.getCfee())
						.c_numberofphoto(e.getCnumberofphoto())
						.c_typeofverify(e.getCtypeofverify())
						.c_typeoffrequency(e.getCtypeoffrequency())
						.c_frequency(e.getCfrequency())
						.c_score(e.getCscore())
						.build()
				);	
			}
			return searchByChallengeCategory;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
		}
	}
	public List<ChallengeDTO> ChallengeSearchByStartDate(String keyword) {
		List<Challenge>searchByChallengeStartdateEntity;
		List<ChallengeDTO>searchByChallengeStartdate =new ArrayList<>();
		try {
			searchByChallengeStartdateEntity = challengeRepository.findByCcontentContaining(keyword);
			for(Challenge e : searchByChallengeStartdateEntity) {
				searchByChallengeStartdate.add(
						ChallengeDTO.builder()
						.c_id(e.getCid())
						.c_name(e.getCname())
						.c_content(e.getCcontent())
						.c_startdate(e.getCstartdate())
						.c_enddate(e.getCenddate())
						.c_numberofparticipants(e.getCnumberofparticipants())
						.c_category(e.getCcategory())
						.c_thumbnails(e.getCthumbnails())
						.c_introduction(e.getCintroduction())
						.c_fee(e.getCfee())
						.c_numberofphoto(e.getCnumberofphoto())
						.c_typeofverify(e.getCtypeofverify())
						.c_typeoffrequency(e.getCtypeoffrequency())
						.c_frequency(e.getCfrequency())
						.c_score(e.getCscore())
						.build()
				);	
			}
			return searchByChallengeStartdate;
			}catch(Exception e) {
				e.printStackTrace();
				return null;
		}
	}
}
