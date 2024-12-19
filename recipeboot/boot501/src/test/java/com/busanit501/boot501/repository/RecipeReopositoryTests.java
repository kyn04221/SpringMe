package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Recipe;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RecipeReopositoryTests {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void testInsert() {
        IntStream.range(1, 100).forEach(i -> {
            Recipe recipe = Recipe.builder()
                    .recipename("메뉴명 " + i)
                    .ingredients("재료 " + i)
                    .method("레시피 " + i)
                    .writer("123"+i)
                    .urlsource("http://" + i)
                    .build();
            Recipe result = recipeRepository.save(recipe);
            log.info("추가된 recipeid 번호 : " + result);
        });
    }
//
//    @Test
//    public void testSelectOne() {
//        Long recipeid = 99L;
//        Optional<Recipe> result = recipeRepository.findById(recipeid);
//        Recipe recipe = result.orElseThrow();
//        log.info("하나 조회 : " + recipe);
//
//    }
//
//    @Test
//    public void testSelectAll() {
//        List<Recipe> result = recipeRepository.findAll();
//        for (Recipe recipe : result) {
//            log.info(recipe);
//        }
//
//
//    }
//
//    @Test
//    public void testUpdate() {
//        Long recipeid = 98L;
//        Optional<Recipe> result = recipeRepository.findById(recipeid);
//
//        Recipe recipe = result.orElseThrow();
//        recipe.changeRecipeConent("메뉴명 수정", "재료 수정",
//                "조리법 수정","작성자수정", "url수정");
//        recipeRepository.save(recipe);
//    }
//
//    @Test
//    public void testDelete() {
//        Long recipeid = 99L;
//        recipeRepository.deleteById(recipeid);
//    }
//
//    @Test
//    public void testPaging() {
//        Pageable pageable = PageRequest.of(1, 10,
//                Sort.by("recipe_id").descending());
//        Page<Recipe> result = recipeRepository.findAll(pageable);
//        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
//        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
//        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
//        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
//        log.info("result.getSize() 크기  :" + result.getSize());
//    }

}
