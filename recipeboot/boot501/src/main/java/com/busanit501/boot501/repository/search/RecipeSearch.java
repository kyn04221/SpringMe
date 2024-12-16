package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeSearch {

    Page<Recipe> search(Pageable pageable);

    Page<Recipe> searchAll(String[] types, String keyword, Pageable pageable);
}
