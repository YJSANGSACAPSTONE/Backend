package com.planner.godsaeng.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.QBoard;
import com.planner.godsaeng.entity.QComment;
import com.planner.godsaeng.entity.QPost;
import com.planner.godsaeng.entity.QPostImage;
import com.planner.godsaeng.entity.QPostLike;
import com.planner.godsaeng.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchPostRepositoryImpl extends QuerydslRepositorySupport implements SearchPostRepository {
   
   public SearchPostRepositoryImpl() {
      super(Post.class);
   }
   
   @Override // Tuple 이용
   public Post search1() {
      log.info("search1................");
      QPost post = QPost.post;
      QUser user= QUser.user;
      QBoard board = QBoard.board;
      
      JPQLQuery<Post> jpqlQuery = from(post);
      jpqlQuery.leftJoin(user).on(post.user.eq(user));
      jpqlQuery.leftJoin(board).on(post.board.eq(board));
      
      JPQLQuery<Tuple> tuple = jpqlQuery.select(post, user, board.bid);
      tuple.groupBy(post);
      
      log.info("-------------------------------");
      log.info(tuple);
      log.info("-------------------------------");
      
      List<Tuple> result = tuple.fetch();
      
      log.info(result);
      
      return null;
   }
   
   @Override
   public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
      log.info("searchPage...........................");
      QPost post = QPost.post;
      QUser user= QUser.user;
      QBoard board = QBoard.board;
      QComment comment = QComment.comment;
      QPostLike postLike = QPostLike.postLike;
      
      JPQLQuery<Post> jpqlQuery = from(post);
      jpqlQuery.leftJoin(post.user, user);
      jpqlQuery.leftJoin(post.board, board);
      jpqlQuery.leftJoin(comment).on(comment.post.eq(post));
      jpqlQuery.leftJoin(postLike).on(postLike.post.eq(post));
      
      JPQLQuery<Tuple> tuple = jpqlQuery.select(post, user.unickname, board.bid, comment.count(), postLike.count()).groupBy(post);

      BooleanBuilder booleanBuilder = new BooleanBuilder();
      BooleanExpression expression = post.poid.gt(0L);
      
      booleanBuilder.and(expression);
      
      if(type != null) {
         String[] typeArr = type.split("");
         
         BooleanBuilder conditionBuilder = new BooleanBuilder();
         
         for (String t : typeArr) {
            switch (t) {
            case "t": conditionBuilder.or(post.potitle.contains(keyword)); break;
            case "w": conditionBuilder.or(user.unickname.contains(keyword)); break;
            case "c": conditionBuilder.or(post.pocontent.contains(keyword)); break;
            }
         }
         booleanBuilder.and(conditionBuilder);
      }
      
      tuple.where(booleanBuilder);
      
      Sort sort = pageable.getSort();
      
      sort.stream().forEach(order -> {
         Order direction = order.isAscending() ? Order.ASC : Order.DESC;
         String prop = order.getProperty();
         PathBuilder orderByExpression = new PathBuilder(Post.class, "post");
         tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
      });
      
      tuple.groupBy(post);
      
      tuple.offset(pageable.getOffset());
      tuple.limit(pageable.getPageSize());
      
      List<Tuple> result = tuple.fetch();
      log.info(result);
      
      long count = tuple.fetchCount();
      log.info("COUNT: " + count);
      
      return new PageImpl<Object[]>(
            result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
   }
   
   @Override
   public Page<Object[]> searchPageByBoard(String type, String keyword, Pageable pageable, int bid) {
      log.info("searchPage...........................");
      QPost post = QPost.post;
      QUser user= QUser.user;
      QBoard board = QBoard.board;
      QComment comment = QComment.comment;
      QPostLike postLike = QPostLike.postLike;
      
      JPQLQuery<Post> jpqlQuery = from(post);
      jpqlQuery.leftJoin(post.user, user);
      jpqlQuery.leftJoin(post.board, board);
      jpqlQuery.leftJoin(comment).on(comment.post.eq(post));
      jpqlQuery.leftJoin(postLike).on(postLike.post.eq(post));
      
      JPQLQuery<Tuple> tuple = jpqlQuery.select(post, user.unickname, board.bid, comment.count(), postLike.count()).groupBy(post);

      BooleanBuilder booleanBuilder = new BooleanBuilder();
      BooleanExpression expression = post.poid.gt(0L);
      
      booleanBuilder.and(expression);
      
      if(type != null) {
         String[] typeArr = type.split("");
         
         BooleanBuilder conditionBuilder = new BooleanBuilder();
         
         for (String t : typeArr) {
            switch (t) {
            case "t": conditionBuilder.or(post.potitle.contains(keyword)); break;
            case "w": conditionBuilder.or(user.unickname.contains(keyword)); break;
            case "c": conditionBuilder.or(post.pocontent.contains(keyword)); break;
            }
         }
         booleanBuilder.and(conditionBuilder);
      }
      
      booleanBuilder.and(post.board.bid.eq(bid)); // 추가: bid 조건
      
      tuple.where(booleanBuilder);
      
      Sort sort = pageable.getSort();
      
      sort.stream().forEach(order -> {
         Order direction = order.isAscending() ? Order.ASC : Order.DESC;
         String prop = order.getProperty();
         PathBuilder orderByExpression = new PathBuilder(Post.class, "post");
         tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
      });
      
      tuple.groupBy(post);
      
      tuple.offset(pageable.getOffset());
      tuple.limit(pageable.getPageSize());
      
      List<Tuple> result = tuple.fetch();
      log.info(result);
      
      long count = tuple.fetchCount();
      log.info("COUNT: " + count);
      
      return new PageImpl<Object[]>(
            result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
   }
}