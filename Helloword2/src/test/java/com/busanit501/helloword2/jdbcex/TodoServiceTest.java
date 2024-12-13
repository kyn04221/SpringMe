package com.busanit501.helloword2.jdbcex;

import com.busanit501.helloword2.jdbc.dto.TodoDTO;
import com.busanit501.helloword2.jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class TodoServiceTest {
    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }
    @Test
    public void testInsert() throws Exception {
        // 더미 데이터, 화면에서 전달 받은 TodoDTO
        TodoDTO todoDTO = TodoDTO.builder()
                .title("샘플 작업 1126")
                .dueDate(LocalDate.now())
                .build();
        todoService.register(todoDTO);
    }

    @Test
    public void testSelectAll() throws Exception {
        // 더미 데이터, 화면에서 전달 받은 TodoDTO
        List<TodoDTO> todoList = todoService.listAll();
        for(TodoDTO todoDto:todoList){
            log.info(todoDto);
        }
    }

    @Test
    public void testSelectOne() throws Exception {
        TodoDTO todoDTO = todoService.get(1L);
        log.info("DTO:"+ todoDTO);
    }

    // 하나수정,
    @Test
    public void testUpdateOne() throws SQLException {
        //
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(2L)
                .title("수정된 내용입니다.")
                .dueDate(LocalDate.now())
                .finished(false)
                .build();

        todoService.update(todoDTO);
    }

    // 하나삭제,
    @Test
    public void testDelteOne() throws SQLException {
        todoService.delete(18L);
    }
}