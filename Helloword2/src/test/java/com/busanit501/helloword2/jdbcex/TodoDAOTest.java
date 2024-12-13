package com.busanit501.helloword2.jdbcex;

import com.busanit501.helloword2.jdbc.dao.TodoDAO;
import com.busanit501.helloword2.jdbc.dto.TodoVO;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class TodoDAOTest {
    private TodoDAO todoDAO;
    @BeforeEach
    public void ready(){
        todoDAO = new TodoDAO();
    }

    @Test
    public void gettime(){
        System.out.println("sql전달 후 시간 조회"+todoDAO.getTim());
    }

    @Test
    public void getTime2() throws SQLException, SQLException {
        System.out.println("sql 전달 후, " +
                "시간 조회 확인용: 자동 반납 @Cleanup 확인 "+todoDAO.getTime2());
    }



    //하나 저장하기---------------------------------------------
    @Test
    public void insert() throws SQLException{

//        TodoVO todoVo = new TodoVO();
//
//        todoVo.setTitle("점심");
//        todoVo.setDueDate(LocalDate.of("2024,12,31"));


        TodoVO todoVo = TodoVO.builder()
                .title("디비연결 테스트")
                .dueDate(LocalDate.of(2024,12,31))
                .build();

        todoDAO.insert(todoVo);
    }

    //전체 조회하기---------------------------------------------
    @Test
    public void testList() throws SQLException{
            List<TodoVO> list = todoDAO.selectAll();
            list.forEach(vo -> System.out.println(vo));
        }

    //하나 조회하기---------------------------------------------
    @Test
    public void getOneTEst() throws SQLException{
        Long tno = 3L;
        TodoVO vo = todoDAO.selectOne(tno);
        System.out.println(vo);
        }

    //삭제----------------------------------------------------
    @Test
    public void deleteTest() throws SQLException {
        Long tno = 4L;
        todoDAO.deleteTodo(tno);
    }

    //수정----------------------------------------------------
    @Test
    public void updateTest() throws SQLException {
        // 실제 작업은 내용을 화면에서 받아오는 대신,
        // 하드 코딩으로 값을 더미로 테스트.
        TodoVO todoVO = TodoVO.builder()
                .tno(3L)
                .title("수정 테스트 중")
                .finished(true)
                .dueDate(LocalDate.of(2024, 11, 25))
                .build();
        todoDAO.updateOne(todoVO);
    }

}