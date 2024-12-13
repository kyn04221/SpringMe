package com.busanit501.helloword2.jdbc.service;

import com.busanit501.helloword2.jdbc.dao.TodoDAO;
import com.busanit501.helloword2.jdbc.dto.TodoDTO;
import com.busanit501.helloword2.jdbc.dto.TodoVO;
import com.busanit501.helloword2.jdbc.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors  ;

@Log4j2
public enum TodoService {
    //crud
    INSTANCE;

    private TodoDAO todoDAO;
    private ModelMapper modelMapper;

    TodoService(){
        todoDAO = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //dto > vo
    public void register (TodoDTO todoDTO) throws Exception {

        TodoVO todoVO = modelMapper.map(todoDTO,TodoVO.class);
        //기존 출력방식
        // System.out.println("todoVo : "+ todoVO);

        log.info("todoVo : "+ todoVO);
        // DAO 외주 맡기기,
        todoDAO.insert(todoVO);
    }
    // vo > dto
    public List<TodoDTO> listAll() throws SQLException {
        List<TodoVO> voList = todoDAO.selectAll();
        log.info("voList : "+ voList);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
    public TodoDTO get(Long tno) throws SQLException {
        log.info("tno : " + tno);
        ///  디비에서 하나 조회 결과 받았음.
        TodoVO todoVO = todoDAO.selectOne(tno);
        // VO -> DTO 변환 작업.
        TodoDTO todoDTO = modelMapper.map(todoVO,TodoDTO.class);
        return todoDTO;

    }

    //4 수정 기능
    public void update(TodoDTO todoDTO) throws SQLException {
        // 화면에서 넘겨 받은 데이터는 TodoDTO 타입 박스에 담겨서 오고,
        // DAO 계층에서 박스의 타입 TodoVO 사용하니, 변환 작업 필요함.
        // 항상 데이터가 전달 유무 확인.
        log.info("todoDTO : " + todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoDAO.updateOne(todoVO);

    }

    //5 삭제 기능.
    public void delete(Long tno) throws SQLException {
        todoDAO.deleteTodo(tno);
    }

}
