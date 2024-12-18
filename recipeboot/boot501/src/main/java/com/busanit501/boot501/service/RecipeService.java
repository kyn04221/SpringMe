package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.RecipeDTO;
import com.busanit501.boot501.dto.RecipeListReplyCountDTO;

public interface RecipeService {
    Long register(RecipeDTO recipeDTO);
    RecipeDTO readOne(Long recipeid);
    void update(RecipeDTO recipeDTO);
    void delete(Long recipeid);
    PageResponseDTO<RecipeDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<RecipeListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

}
