package com.planner.godsaeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.entity.Zepeto;
import com.planner.godsaeng.repository.ZepetoRepository;

@Service
public class ZepetoService {
	
	Zepeto zepeto = null;
	@Autowired
	ZepetoRepository zepetoRepository;

//	public void testClass() {
//		System.out.println("객체생성 확인 ---" + commentRepository.getClass().getName());
//	}
	   
    public void saveZepeto(Zepeto zepeto) {
        zepetoRepository.save(zepeto);
    }
    
}

