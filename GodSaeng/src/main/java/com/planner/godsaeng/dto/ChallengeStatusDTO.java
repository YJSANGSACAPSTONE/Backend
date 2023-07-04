package com.planner.godsaeng.dto;




import java.math.BigInteger;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ChallengeStatusDTO {
	private BigInteger cid;
    private String cname;
    private String cthumbnails;
    private Date cstartdate;
    private Date cenddate;
    private Integer ctypeofverify;
    private Integer datediff;
    private BigInteger totalcount;
    private BigInteger cvsuccesscount;
    
    

}
