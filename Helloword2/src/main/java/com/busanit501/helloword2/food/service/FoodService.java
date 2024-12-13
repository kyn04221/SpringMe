package com.busanit501.helloword2.food.service;

import com.busanit501.helloword2.food.dao.FoodDAO;
import com.busanit501.helloword2.food.dto.FoodDTO;
import com.busanit501.helloword2.food.dto.FoodVO;
import com.busanit501.helloword2.food.util.FoodMapperUtil;
import com.busanit501.helloword2.jdbc.dao.TodoDAO;
import com.busanit501.helloword2.jdbc.dto.TodoDTO;
import com.busanit501.helloword2.jdbc.dto.TodoVO;
import com.busanit501.helloword2.jdbc.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public enum FoodService {
    INSTANCE;

    private FoodDAO dao;
    private ModelMapper modelMapper;

    FoodService(){
        dao = new FoodDAO();
        modelMapper = FoodMapperUtil.INSTANCE.get();
    }

    // dto > vo
    public void register (FoodDTO dto) throws Exception {

        FoodVO foodVO = modelMapper.map(dto, FoodVO.class);
        log.info("foodvo : "+ foodVO);
        dao.insert(foodVO);
    }
    // vo > dto
    public List<FoodDTO> listAll() throws SQLException {
        List<FoodVO> voList = dao.selectAll();

        log.info("voList : "+ voList);

        List<FoodDTO> dtoList = voList.stream()
                .map(vo->modelMapper.map(vo,FoodDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    // vo > dto
    public FoodDTO get(Long tno) throws SQLException {
        log.info("tno : "+ tno);
        dao.selectOne(tno);
        FoodVO foodovo = dao.selectOne(tno);
        FoodDTO foodDTO = modelMapper.map(foodovo,FoodDTO.class);
        return foodDTO;
    }


    public FoodDTO getOne(String menu) {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setMenu("푸라푸치노 - 5000원");
        return foodDTO;

    }

    //4 수정 기능
    public void update(FoodDTO dto) throws SQLException {
        // 화면에서 넘겨 받은 데이터는 TodoDTO 타입 박스에 담겨서 오고,
        // DAO 계층에서 박스의 타입 TodoVO 사용하니, 변환 작업 필요함.
        // 항상 데이터가 전달 유무 확인.
        log.info("FoodDTO : " + dto);
        FoodVO VO = modelMapper.map(dto, FoodVO.class);
        dao.updateOne(VO);

    }

    //5 삭제 기능.
    public void delete(Long tno) throws SQLException {
        dao.deleteFood(tno);
    }

}
//하나 조회 / 전화번호부 이름 -번호 조회됨

