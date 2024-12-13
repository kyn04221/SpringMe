package com.busanit501.springex.service;

import com.busanit501.springex.domain.RecipeVO;
import com.busanit501.springex.dto.PageRequestDTO;
import com.busanit501.springex.dto.PageResponseDTO;
import com.busanit501.springex.dto.RecipeDTO;
import com.busanit501.springex.mapper.RecipeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeMapper recipeMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(RecipeDTO recipeDTO) {
        RecipeVO recipeVO = modelMapper.map(recipeDTO, RecipeVO.class);
        recipeMapper.insert(recipeVO);

    }

    @Override
    public List<RecipeDTO> getAll() {
        List<RecipeDTO> list = recipeMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, RecipeDTO.class))
                .collect(Collectors.toList());
        return list;
    }



    @Override
    public RecipeDTO getOne(Long recipe_id) {
        RecipeVO recipeVO = recipeMapper.selectOne(recipe_id);
        RecipeDTO recipeDTO = modelMapper.map(recipeVO, RecipeDTO.class);
        return recipeDTO;
    }

    @Override
    public void delete(Long recipe_id) {
        recipeMapper.delete(recipe_id);
    }

    @Override
    public void update(RecipeDTO recipeDTO) {
        RecipeVO recipeVO = modelMapper.map(recipeDTO, RecipeVO.class);
        recipeMapper.update(recipeVO);

    }

    @Override
    public PageResponseDTO<RecipeDTO> selectList(PageRequestDTO pageRequestDTO) {

        int total = recipeMapper.getCount(pageRequestDTO);

        List<RecipeDTO> dtoList = recipeMapper.selectList(pageRequestDTO).stream()
                .map(vo -> modelMapper.map(vo, RecipeDTO.class))
                .collect(Collectors.toList());

        PageResponseDTO<RecipeDTO> pageResponseDTO = PageResponseDTO.<RecipeDTO>withAll()
                .total(total)
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }
}
