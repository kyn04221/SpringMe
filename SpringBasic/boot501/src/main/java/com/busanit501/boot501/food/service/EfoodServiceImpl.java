package com.busanit501.boot501.food.service;

import com.busanit501.boot501.food.dto.EfoodDTO;
import com.busanit501.boot501.food.dto.PageRequestDTO;
import com.busanit501.boot501.food.dto.PageResponseDTO;
import com.busanit501.boot501.food.domain.Efood;
import com.busanit501.boot501.food.repository.EfoodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class EfoodServiceImpl implements EfoodService {

    //맵퍼에게 의존 해야함.
    // 디비 작업 도구,
    private final EfoodRepository efoodRepository;
    // DTO <-> Entity class
    private final ModelMapper modelMapper;

    @Override
    public Long register(EfoodDTO efoodDTO) {
        Efood efood = modelMapper.map(efoodDTO, Efood.class);
        Long bno = efoodRepository.save(efood).getBno();
        return bno;
    }

    @Override
    public EfoodDTO readOne(Long bno) {
        Optional<Efood> result = efoodRepository.findById(bno);
        Efood efood = result.orElseThrow();
        EfoodDTO dto = modelMapper.map(efood, EfoodDTO.class);
        return dto;
    }

    @Override
    public void update(EfoodDTO efoodDTO) {
        Optional<Efood> result = efoodRepository.findById(efoodDTO.getBno());
        Efood efood = result.orElseThrow();
        efood.changeMenuConent(efoodDTO.getMenu(),efoodDTO.getPrice());
        efoodRepository.save(efood);
    }
    @Override
    public void delete(Long bno) {
        efoodRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<EfoodDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Efood> result = efoodRepository.searchAll(types,keyword,pageable);

        List<EfoodDTO> dtoList = result.getContent().stream()
                .map(efood ->modelMapper.map(efood, EfoodDTO.class))
                .collect(Collectors.toList());


        return PageResponseDTO.<EfoodDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

    } // list
}
