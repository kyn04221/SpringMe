package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.RecipeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ServiceTests {
    @Autowired
    private RecipeService recipeService;

    @Test
    public void testRegisterBoard() {
        RecipeDTO recipeDTO = RecipeDTO.builder()
                .recipename("김치라면")
                .ingredients("김치, 라면")
                .method("김치를 넣고 라면을 끓인다")
                .urlsource("123")
                .build();

        Long recipeid = recipeService.register(recipeDTO);
        log.info("입력한 게시글 번호: " + recipeid.toString());
    }

    @Test
    public void testSelectOneRecipe() {
        Long recipeid = 1L;
        RecipeDTO recipeDTO= recipeService.readOne(recipeid);
        log.info("testSelectOneBoard , 하나 조회 boardDTO: " + recipeDTO.toString());
    }

    @Test
    public void testUpdateBoard() {
        RecipeDTO boardDTO = RecipeDTO.builder()
                .recipeid(3L)
                .recipename("간장계란밥")
                .ingredients("간장,참기름,계란,밥")
                .method("계란 구운후 모든재료를 비빈다")
                .urlsource("123")
                .build();
        recipeService.update(boardDTO);

    }

    @Test
    public void testDeleteBoard() {
        recipeService.delete(2L);
    }

    @Test
    public void testSelectAllBoard() {
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("가나다")
                        .size(10)
                        .build();

        PageResponseDTO<RecipeDTO> list = recipeService.list(pageRequestDTO);
        log.info("list: " + list.toString());
    }
}
