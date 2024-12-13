package com.busanit501.springex.mapper;

import com.busanit501.springex.domain.RecipeVO;
import com.busanit501.springex.dto.PageRequestDTO;

import java.util.List;

public interface RecipeMapper {

    void insert(RecipeVO recipeVO);

    List<RecipeVO> selectAll();

    RecipeVO selectOne(Long recipe_id);

    void delete(Long recipe_id);

    void update(RecipeVO recipeVO);

    List<RecipeVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}





