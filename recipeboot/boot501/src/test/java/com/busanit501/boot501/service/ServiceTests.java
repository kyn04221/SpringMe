package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.RecipeDTO;
import com.busanit501.boot501.dto.RecipeListReplyCountDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        RecipeDTO recipeDTO = RecipeDTO.builder()
                .recipeid(3L)
                .recipename("간장계란밥")
                .ingredients("간장,참기름,계란,밥")
                .method("계란 구운후 모든재료를 비빈다")
                .writer("전효정")
                .urlsource("123")
                .build();
        recipeService.update(recipeDTO);

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

    @Test
    @Transactional
    public void testSelectAllBoardWithReplyCount() {
        // 검색할 더미 데이터
        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<RecipeListReplyCountDTO> list = recipeService.listWithReplyCount(pageRequestDTO);
        log.info("list: " + list.toString());
    }
}
