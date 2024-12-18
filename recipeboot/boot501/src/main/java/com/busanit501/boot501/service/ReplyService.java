package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.RReplyDTO;

public interface ReplyService {
    Long register(RReplyDTO replyDTO);

    RReplyDTO readOne(Long rno);

    void update(RReplyDTO replyDTO);

    void delete(Long rno);

    // 부모 게시글 번호에 대한 댓글 목록 조회.
    PageResponseDTO<RReplyDTO> listWithReply(Long bno, PageRequestDTO pageRequestDTO);
}

