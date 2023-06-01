package com.planner.godsaeng.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.planner.godsaeng.entity.Challenge;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ChallengeRepositoryImpl implements ChallengeRepository {
	
	@Autowired
	JPAQueryFactory jpaQueryFactory;
	
	
	
	@Override
	public List<Challenge> findAll() {
		
		return null;
	}

	@Override
	public List<Challenge> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Challenge> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends Challenge> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Challenge> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Challenge> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub

	}

	@Override
	public Challenge getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Challenge getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Challenge getReferenceById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Challenge> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Challenge> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Challenge> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Challenge> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Challenge> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Challenge entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Challenge> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public <S extends Challenge> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends Challenge> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Challenge> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Challenge> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Challenge, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findByCid(Long cid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findAllByOrderByCnumberofparticipantsDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findAllByOrderByCstartdateDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findTop10ChallengesByFeeDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findChallengeByUid(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findFinishedChallengeByUid(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findByUid(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findByCnameContaining(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findByCcontentContaining(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findByCstartdateContaining(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Challenge> findByCcategoryContaining(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> myChallengeProgress(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

}
