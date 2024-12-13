package com.busanit501.boot501.food.repository.search;

import com.busanit501.boot501.food.domain.Efood;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FoodSearch {

    Page<Efood> search(Pageable pageable);

    Page<Efood> searchAll(String[] types, String keyword, Pageable pageable);
}
