package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.RReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;

    @Test
//    @Transactional
    public void testRegisterReply() {
        // 더미 데이터 필요, 임시 DTO 생성.
        RReplyDTO replyDTO = RReplyDTO.builder()
                .replyText("33댓글 작성 테스트 ")
                .replyer("333")
                .recipeid(1L)
                .createday(LocalDateTime.now())
                .build();

        log.info("replydto " + replyDTO);
        Long recipeid = replyService.register(replyDTO);
        log.info("입력한 댓글 번호: " + recipeid.toString());
    }

    @Test
    public void testReadReply() {
        RReplyDTO replyDTO = replyService.readOne(2L);
        log.info("조회한 댓글 내용: " + replyDTO);
    }

    @Test
    public void testUpdateReply() {
        // 더미 데이터 필요, 임시 DTO 생성.
        // 수정할 댓글 번호가 없어요. 더미로 추가함.
        // 각자 디비에 따라서 조금씩 달라요.
        //
        RReplyDTO replyDTO = RReplyDTO.builder()
                .replyText("333댓글 수정 테스트")
                .replyer("333")
                .rno(3L)
                .recipeid(1L)
                .createday(LocalDateTime.now())
                .build();

        // register 함수 호출 전에도,  bno 존재
        log.info("replyDTO , register 함수 호출 하기전 : "+replyDTO);
        replyService.update(replyDTO);

    }

    @Test

    public void testDeleteReply() {
        replyService.delete(9L);
    }

    @Test
    public void testAllReply() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<RReplyDTO> result = replyService.listWithReply(99L,pageRequestDTO);
        log.info("result : " + result);
    }
}
