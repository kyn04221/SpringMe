package com.busanit501.boot501.service;

import com.busanit501.boot501.domain.Recipe;
import com.busanit501.boot501.dto.RecipeDTO;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(RecipeDTO recipeDTO) {
        Recipe recipe = modelMapper.map(recipeDTO, Recipe.class);
        Long recipeid = recipeRepository.save(recipe).getRecipeid();
        return recipeid;
    }

    @Override
    public RecipeDTO readOne(Long recipeid) {
        Optional<Recipe> result = recipeRepository.findById(recipeid);
        Recipe recipe= result.orElseThrow();
        RecipeDTO dto = modelMapper.map(recipe, RecipeDTO.class);
        return dto;
    }

    @Override
    public void update(RecipeDTO recipeDTO) {
        Optional<Recipe> result = recipeRepository.findById(recipeDTO.getRecipeid());
        Recipe recipe = result.orElseThrow();
        recipe.changeRecipeConent(recipeDTO.getRecipename(),recipeDTO.getIngredients(),recipeDTO.getMethod(),recipeDTO.getUrlsource());
        recipeRepository.save(recipe);
    }

    @Override
    public void delete(Long recipeid) {
        recipeRepository.deleteById(recipeid);
    }

    @Override
    public PageResponseDTO<RecipeDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("recipeid");

        Page<Recipe> result = recipeRepository.searchAll(types,keyword,pageable);

        List<RecipeDTO> dtoList = result.getContent().stream()
                .map(recipe ->modelMapper.map(recipe, RecipeDTO.class))
                .collect(Collectors.toList());


        return PageResponseDTO.<RecipeDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

    }
}
