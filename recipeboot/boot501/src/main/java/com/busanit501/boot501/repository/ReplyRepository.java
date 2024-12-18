package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.RReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<RReply, Long> {
    // 기본적인 crud , 쿼리 스트링으로 가능함.

    // 검색 기능 추가해보기.
    @Query("select r from RReply r where r.recipe.recipeid = :reciperid")
    Page<RReply> listOfRecipe(Long reciperid, Pageable pageable);
}
