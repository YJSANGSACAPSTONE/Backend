package com.planner.godsaeng.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.service.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/s3upload")
@RequiredArgsConstructor
public class S3Controller {
	
	private final S3Service s3Service;

    @GetMapping("/s3")
    public Map<String, Serializable> s3saveImage(@RequestParam("fileName") String fileName){
        return s3Service.getPreSignedUrl(fileName);
    }
}
