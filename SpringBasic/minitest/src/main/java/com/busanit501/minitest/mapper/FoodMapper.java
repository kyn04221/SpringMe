package com.busanit501.minitest.mapper;

import com.busanit501.minitest.domain.FoodVO;
import com.busanit501.minitest.dto.PageRequestDTO;

import java.util.List;

public interface FoodMapper {

    String getTime();

    void insert(FoodVO foodvo);

    List<FoodVO> selectAll();

    FoodVO selectOne(Long tno);

    void delete(Long tno);

    void update(FoodVO foodvo);

    //페이징한 전체 목록
    List<FoodVO> selectList(PageRequestDTO pageRequestDTO);

    // 페이징 위해서, 전체갯수,
    int getCount(PageRequestDTO pageRequestDTO);
}