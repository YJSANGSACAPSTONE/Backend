package com.planner.godsaeng.service;

import java.math.BigInteger;
import java.sql.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.planner.godsaeng.dto.ChallengeStatusDTO;

@Component
public class ChallengeDataConverter implements Converter<Object[], ChallengeStatusDTO> {

	@Override
	 public ChallengeStatusDTO convert(Object[] source) {
        ChallengeStatusDTO convertedChallengeStatusDTO = new ChallengeStatusDTO();
        convertedChallengeStatusDTO.setCid((BigInteger) source[0]);
        convertedChallengeStatusDTO.setCname((String) source[1]);
        convertedChallengeStatusDTO.setCthumbnails((String) source[2]);
        convertedChallengeStatusDTO.setCstartdate((Date) source[3]);
        convertedChallengeStatusDTO.setCenddate((Date) source[4]);
        convertedChallengeStatusDTO.setCtypeofverify((Integer) source[4]);
        convertedChallengeStatusDTO.setDatediff((BigInteger) source[6]);
        convertedChallengeStatusDTO.setTotalcount((BigInteger) source[7]);
        convertedChallengeStatusDTO.setCvsuccesscount(((BigInteger) source[9]));
        return convertedChallengeStatusDTO;
    }
}
