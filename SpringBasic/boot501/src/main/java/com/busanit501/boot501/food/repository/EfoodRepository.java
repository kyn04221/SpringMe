package com.busanit501.boot501.food.repository;

import com.busanit501.boot501.food.domain.Efood;
import com.busanit501.boot501.food.repository.search.FoodSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EfoodRepository extends JpaRepository<Efood, Long> , FoodSearch {

    Page<Efood> findByMenuContainingOrderByBnoDesc(String menu, Pageable pageable);

    @Query("select b from Efood b where b.menu like concat('%',:keyword,'%')")
    Page<Efood> findByKeyword(String keyword, Pageable pageable);

    @Query(value = "select now()", nativeQuery = true)
    String now();

}
