package com.planner.godsaeng.repository;


import static com.planner.godsaeng.entity.QPlan.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.planner.godsaeng.entity.Plan;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PlanRepositoryImpl implements PlanRepositoryCustom {
	
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Plan> findByUidAndPStartDateOrderByPStartTimeAsc(String uid, String realtodaystime) {
        LocalDate realToday = LocalDate.parse(realtodaystime, DateTimeFormatter.ISO_DATE);

		return jpaQueryFactory
				.selectFrom(plan)
				.where(plan.user.uid.eq(uid)
						.and(plan.pstartdate.eq(realToday)))
						.orderBy(plan.pstartdate.asc())
						.fetch();
	}

	@Override
	public List<Plan> findByUidAndPStartDateOrderByPStartTimeAscc(String uid, String realtodaystime) {
		// TODO Auto-generated method stub
        LocalDate realToday = LocalDate.parse(realtodaystime, DateTimeFormatter.ISO_DATE);

		return jpaQueryFactory
                .selectFrom(plan)
                .where(plan.user.uid.eq(uid)
                        .and(plan.pstartdate.loe(realToday))
                        .and(plan.penddate.goe(realToday)))
                .orderBy(plan.pstarttime.asc())
                .fetch();
	}

}
