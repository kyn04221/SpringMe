package com.busanit501.service;


import com.busanit501.boot501.food.dto.EfoodDTO;
import com.busanit501.boot501.food.dto.PageRequestDTO;
import com.busanit501.boot501.food.dto.PageResponseDTO;
import com.busanit501.boot501.food.service.EfoodService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class EfoodServiceTests {

    @Autowired
    private EfoodService efoodService;

    @Test
    public void testRegisterBoard() {
        // 더미 데이터 필요, 임시 DTO 생성.
        EfoodDTO efoodDTO = EfoodDTO.builder()
                .menu("샤브샤브")
                .price("10,000원")
                .writer("이상용")
                .build();

        Long bno = efoodService.register(efoodDTO);
        log.info("입력한 게시글 번호: " + bno.toString());
    }

    @Test
    public void testSelectOneBoard() {
        Long bno = 103L;
        EfoodDTO efoodDTO= efoodService.readOne(bno);
        log.info("testSelectOneBoard , 하나 조회 boardDTO: " + efoodDTO.toString());
    }

    @Test
    public void testUpdateBoard() {
        // 수정할 더미 데이터 필요,
        EfoodDTO efoodDTO = EfoodDTO.builder()
                .bno(103L)
                .menu("얼큰샤브샤브")
                .price("30,000원")
                .build();

        efoodService.update(efoodDTO);

    }

    @Test
    public void testDeleteBoard() {
        efoodService.delete(103L);
    }

    @Test
    public void testSelectAllBoard() {
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<EfoodDTO> list = efoodService.list(pageRequestDTO);
        log.info("list: " + list.toString());
    }
}
