package com.busanit501.helloword2.jdbcex;

import com.busanit501.helloword2.food.dao.FoodDAO;
import com.busanit501.helloword2.food.dto.FoodVO;

import com.busanit501.helloword2.jdbc.dao.TodoDAO;
import com.busanit501.helloword2.jdbc.dto.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FoodTest {
    private FoodDAO dao ;

    //@BeforeEach로 모든TEST 시작전 객체 생성--------------------
    @BeforeEach
    public void ready(){
        dao = new FoodDAO();
    }

    //저장하기-------------------------------------------------
    @Test
    public void insert() throws SQLException {
        FoodVO foodVo = FoodVO.builder()
                .menu("푸라푸치노")
                .price("5000원")
                .build();

        dao.insert(foodVo);

    }

    //전체 조회하기---------------------------------------------
    @Test
    public void testList() throws SQLException{
        List<FoodVO> list = dao.selectAll(); // dao 객체 질문하기
        list.forEach(vo -> System.out.println(vo));
    }

    //하나 조회하기---------------------------------------------
    @Test
    public void getOneTEst() throws SQLException{
        Long tno = 3L;
        FoodVO vo = dao.selectOne(tno);
        System.out.println(vo);
    }

    //삭제----------------------------------------------------
    @Test
    public void deleteTest() throws SQLException {
        Long tno = 1L;
        dao.deleteFood(tno);
    }

    //수정----------------------------------------------------
    @Test
    public void updateTest() throws SQLException {
        // 실제 작업은 내용을 화면에서 받아오는 대신,
        // 하드 코딩으로 값을 더미로 테스트.
        FoodVO vo = FoodVO.builder()
                .tno(2L)
                .menu("카페모카")
                .price("5500원")
                .build();
        dao.updateOne(vo);
    }


}

