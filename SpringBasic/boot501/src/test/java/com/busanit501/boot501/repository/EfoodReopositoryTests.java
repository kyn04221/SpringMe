package com.busanit501.boot501.repository;

import com.busanit501.boot501.food.domain.Efood;
import com.busanit501.boot501.food.repository.EfoodRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class EfoodReopositoryTests {

    @Autowired
    private EfoodRepository efoodRepository;

    @Test
    public void testInsert() {
        IntStream.range(1, 100).forEach(i -> {
            Efood efood = Efood.builder()
                    .menu("샘플 메뉴 : " + i)
                    .price("샘플 가격 : " + i)
                    .writer("샘플 작성자 : jhj "+i)
                    .build();

            Efood result = efoodRepository.save(efood);
            log.info("추가된 bno 번호 : " + result);
        });
    }

    @Test
    public void testSelectOne() {
        Long bno = 99L;
        Optional<Efood> result = efoodRepository.findById(bno);
        Efood efood= result.orElseThrow();
        log.info("하나 조회 : " + efood);

    }

    @Test
    public void testSelectAll() {

        List<Efood> result = efoodRepository.findAll();
        // result 있으면, Board 타입으로 받고, 없으면, 예외 발생시킴.
        for (Efood efood : result) {
            log.info(efood);
        }


    }

    @Test
    public void testUpdate() {
        Long bno = 99L;
        Optional<Efood> result = efoodRepository.findById(bno);

        Efood efood= result.orElseThrow();
        efood.change(" 아메리카노 ","5,000원");


        efoodRepository.save(efood);

    }

    @Test
    public void testDelete() {
        Long bno = 99L;
        efoodRepository.deleteById(bno);
    }

    @Test
    public void testPaging() {

        Pageable pageable =  PageRequest.of(0, 10,
                Sort.by("bno").descending());
        Page<Efood> result = efoodRepository.findAll(pageable);
        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
        log.info("result.getSize() 크기  :" +result.getSize());
    }
    // 쿼리스트링
    @Test
    public void testQueryString() {
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("bno").descending());
        Page<Efood> result = efoodRepository.findByMenuContainingOrderByBnoDesc(
                "3", pageable
        );
        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
        log.info("result.getSize() 크기  :" + result.getSize());
    }

    // 방법2 , @Query
    @Test
    public void testQueryAnotation() {
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("bno").descending());
        Page<Efood> result = efoodRepository.findByKeyword("3", pageable);

        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
        log.info("result.getSize() 크기  :" + result.getSize());
    }


    @Test
    public void testQuerydsl() {
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("bno").descending());
        efoodRepository.search(pageable);

    }

    @Test
    public void testQuerydsl2() {
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("bno").descending());


        String keyword = "3";
        String[] types = {"m","p","w"};

        Page<Efood> result = efoodRepository.searchAll(types,keyword,pageable);

        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
        log.info("result.getSize() 크기  :" +result.getSize());
        log.info("result.hasNext() 다음  :" +result.hasNext());
        log.info("result.hasPrevious() 이전  :" +result.hasPrevious());
    }


}
