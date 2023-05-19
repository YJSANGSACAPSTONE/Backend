package com.planner.godsaeng.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.dto.UsageHistoryDTO;
import com.planner.godsaeng.dto.UsageHistorySaveDTO;
import com.planner.godsaeng.entity.UsageHistory;
import com.planner.godsaeng.service.UsageHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/Usage")
@RequiredArgsConstructor
public class UsageHistoryController {

	private final UsageHistoryService usageHistoryService;
	
	@RequestMapping("/save")
	public ResponseEntity<Boolean> search(@RequestBody UsageHistorySaveDTO uh) {
		
		UsageHistory usageHistory = usageHistoryService.dtoToEntity(uh);
		boolean successed = usageHistoryService.SaveUsageHistory(usageHistory);
		
		if(successed) {
			return ResponseEntity.ok(successed);
		}else {
			return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	@RequestMapping("/search")
	public ResponseEntity<List<UsageHistoryDTO>> search(@RequestParam("uid") String uid){
		List<UsageHistoryDTO> usageHistoryDTOList = usageHistoryService.readUsageHistory(uid);
		return ResponseEntity.ok(usageHistoryDTOList);
	}
}
