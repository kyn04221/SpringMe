package com.busanit501.springex.service;

import com.busanit501.springex.dto.PageRequestDTO;
import com.busanit501.springex.dto.PageResponseDTO;
import com.busanit501.springex.dto.RecipeDTO;

import java.util.List;

public interface RecipeService {
    void register(RecipeDTO recipeDTO);

    List<RecipeDTO> getAll();

    PageResponseDTO<RecipeDTO> selectList(PageRequestDTO pageRequestDTO);

    RecipeDTO getOne(Long recipe_id);

    void delete(Long recipe_id);

    void update(RecipeDTO recipeDTO);
}
