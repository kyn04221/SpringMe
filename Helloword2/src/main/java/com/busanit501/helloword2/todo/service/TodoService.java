package com.busanit501.helloword2.todo.service;

import com.busanit501.helloword2.food.dto.FoodDTO;
import com.busanit501.helloword2.todo.dto.TodoDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum TodoService {

    INSTANCE;

    public void register(TodoDTO TodoDTO) {
        System.out.println("글쓰기 작업 기능");
    }

    //    public List<TodoDTO> getList(){
//
//        List<TodoDTO> todoDTOs = IntStream.range(0,10).mapToObj
//                (i ->{
//                    TodoDTO dto = new TodoDTO();
//                    dto.setTno((long) i);
//                    dto.setTitle("Todo.."+i);
//                    dto.setDueDate(LocalDate.now());
//                    return dto;
//                }
//                ).collect(Collectors.toList());
//
//
//                return todoDTOs;
//    }
    public List<TodoDTO> getList() {
//List<TodoDTO> todoList2 = new ArrayList<>();
//
//        for (int i=0; i<10; i++) {
//        TodoDTO todoDTO = new TodoDTO();
//        todoDTO.setTitle("테스트 " + i);
//        todoDTO.setTno((long) i);
//        todoDTO.setDueDate(LocalDate.now());
//        todoList2.add(todoDTO);
//    }
        List<TodoDTO> todoList = IntStream.range(0, 10).mapToObj(
                i -> {
                    // 10 반복 해서, 더미 인스턴스 10개 생성,
                    TodoDTO todoDTO = new TodoDTO();
                    todoDTO.setTitle("테스트 " + i);
                    todoDTO.setTno((long) i);
                    todoDTO.setDueDate(LocalDate.now());
                    return todoDTO;
                }).collect(Collectors.toList());
        return todoList;
    }

    public TodoDTO getOne(Long tno) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTno(5L);
        todoDTO.setTitle("테스트");
        todoDTO.setDueDate(LocalDate.now());
        return todoDTO;

    }

}
//하나 조회 / 전화번호부 이름 -번호 조회됨

