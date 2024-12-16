//package com.busanit501.boot501.service;
//
//import com.busanit501.boot501.dto.RecipeDTO;
//import com.busanit501.boot501.dto.PageRequestDTO;
//import com.busanit501.boot501.dto.PageResponseDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//
//@Slf4j
//@SpringBootTest
//public class ServiceTests {
//    @Autowired
//    private RecipeService recipeService;
//
//    @Test
//    public void testRegisterBoard() {
//        // 더미 데이터 필요, 임시 DTO 생성.
//        RecipeDTO recipeDTO = RecipeDTO.builder()
//                .title("오늘 점심 뭐 먹지?")
//                .content("라면 먹어야지")
//                .writer("이상용")
//                .regDate(LocalDateTime.now())
//                .build();
//
//        Long bno = recipeService.register(recipeDTO);
//        log.info("입력한 게시글 번호: " + bno.toString());
//    }
//
//    @Test
//    public void testSelectOneRecipe() {
//        // 더미 데이터 필요, 임시 DTO 생성.
//        Long bno = 103L;
//        RecipeDTO recipeDTO= boardService.readOne(bno);
//        log.info("testSelectOneBoard , 하나 조회 boardDTO: " + boardDTO.toString());
//    }
//
//    @Test
//    public void testUpdateBoard() {
//        // 수정할 더미 데이터 필요,
//        RecipeDTO boardDTO = RecipeDTO.builder()
//                .recipe_id(103L)
//                .title("수정한 오늘 점심 뭐 먹지?")
//                .content("칼국수 먹을까?")
//                .build();
//        boardService.update(boardDTO);
//
//    }
//
//    @Test
//    public void testDeleteBoard() {
//        boardService.delete(103L);
//    }
//
//    @Test
//    public void testSelectAllBoard() {
//        // 검색할 더미 데이터
//        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
//        PageRequestDTO pageRequestDTO =
//                PageRequestDTO.builder()
//                        .page(1)
//                        .type("tcw")
//                        .keyword("샘플")
//                        .size(10)
//                        .build();
//
//        PageResponseDTO<RecipeDTO> list = boardService.list(pageRequestDTO);
//        log.info("list: " + list.toString());
//    }
//}
