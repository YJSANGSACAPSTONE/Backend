package com.planner.godsaeng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.UsageHistoryDTO;
import com.planner.godsaeng.dto.UsageHistorySaveDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.entity.UsageHistory;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.UsageHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsageHistoryService {
	
	private final UserService userService;
	private final ChallengeService challengeService;
	
	private final UsageHistoryRepository usageHistoryRepository;
	
	public boolean SaveUsageHistory(UsageHistory usageHistory) {
		try {
			usageHistoryRepository.save(usageHistory);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<UsageHistoryDTO> readUsageHistory(String uid){
		List<UsageHistory> usageHistoryList = usageHistoryRepository.findUsageHistoryByUid(uid);
		List<UsageHistoryDTO> usageHistoryDTOList = new ArrayList<>();
		
		for (UsageHistory uh : usageHistoryList) {
			UsageHistoryDTO usageHistoryDTO = entityToDto(uh);
			usageHistoryDTOList.add(usageHistoryDTO);
		}
		return usageHistoryDTOList;
	}
	
	public UsageHistoryDTO entityToDto(UsageHistory uh) {
		return UsageHistoryDTO.builder()
				.uh_id(uh.getUhid())
				.uh_amount(uh.getUhamount())
				.uh_date(uh.getUhdate())
				.uh_challengeName(uh.getChallenge().getCname())
				.build();
	}
	
	public UsageHistory dtoToEntity(UsageHistorySaveDTO uh) {
		Optional<User> userEntity = userService.SearchId(uh.getUh_user());
		Optional<Challenge> challengeEntity = challengeService.SearchCid(uh.getUh_challenge());
		return UsageHistory.builder()
				.uhamount(uh.getUh_amount())
				.uhdate(uh.getUh_date())
				.user(userEntity.get())
				.challenge(challengeEntity.get())
				.build();
	}
}
