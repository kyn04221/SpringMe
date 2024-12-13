package com.busanit501.helloword2.jdbcex;

import com.busanit501.helloword2.food.dto.FoodDTO;
import com.busanit501.helloword2.food.service.FoodService;

import com.busanit501.helloword2.jdbc.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class FoodServicTest {
        private FoodService foodService;
        @BeforeEach
        public void ready() {
            foodService = FoodService.INSTANCE;
        }
        @Test
        public void testInsert() throws Exception {
            // 더미 데이터, 화면에서 전달 받은 TodoDTO
            FoodDTO foodDTO = FoodDTO.builder()
                    .menu("아이스 아메리카노")
                    .price("2500원")
                    .build();
            foodService.register(foodDTO);
        }

    @Test
    public void testSelectAll() throws Exception {
        // 더미 데이터, 화면에서 전달 받은 TodoDTO
        List<FoodDTO> foodList = foodService.listAll();
        for(FoodDTO foodDto:foodList){
            log.info(foodDto);
        }
    }
    
    @Test
    public void testSelectOne() throws Exception {
        FoodDTO foodDTO = foodService.get(2L);
        log.info("DTO:"+ foodDTO);
    }


    // 하나수정,
    @Test
    public void testUpdateOne() throws SQLException {
        //
        FoodDTO foodDTO = FoodDTO.builder()
                .tno(2L)
                .menu("수정된 내용입니다.")
                .price("20,000원")
                .build();

        foodService.update(foodDTO);
    }

    // 하나삭제,
    @Test
    public void testDelteOne() throws SQLException {
        foodService.delete(9L);
    }
    }

