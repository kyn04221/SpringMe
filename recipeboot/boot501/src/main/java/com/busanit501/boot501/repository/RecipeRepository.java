package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Recipe;
import com.busanit501.boot501.repository.search.RecipeSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> , RecipeSearch {

}
