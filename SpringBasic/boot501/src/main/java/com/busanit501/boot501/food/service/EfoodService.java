package com.busanit501.boot501.food.service;

import com.busanit501.boot501.food.dto.EfoodDTO;
import com.busanit501.boot501.food.dto.PageRequestDTO;
import com.busanit501.boot501.food.dto.PageResponseDTO;

public interface EfoodService {
    Long register(EfoodDTO efoodDTO);
    EfoodDTO readOne(Long bno);
    void update(EfoodDTO efoodDTO);
    void delete(Long bno);
    PageResponseDTO<EfoodDTO> list(PageRequestDTO pageRequestDTO);
}
