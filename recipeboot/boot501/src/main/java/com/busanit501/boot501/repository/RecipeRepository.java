package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Recipe;
import com.busanit501.boot501.repository.search.RecipeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> , RecipeSearch {

    @Query("select b from Recipe b where b.recipename like concat('%',:keyword,'%')")
    Page<Recipe> findByKeyword(String keyword, Pageable pageable);

    @Query(value = "select now()" , nativeQuery = true)
    String now();



}
