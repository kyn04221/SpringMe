package com.busanit501.boot501.dto;

import lombok.Data;

import java.time.LocalDateTime;

// 화면 목록에, 댓글의 갯수를 나타내기위한 박스
@Data
public class RecipeListReplyCountDTO {
    private  Long recipeid;
    private  String recipename;
    private  String ingredients;
    private  String method;
    private  String writer;
    private  String urlsource;
    private LocalDateTime createday;

    // 댓글의 갯수
    private Long replyCount;
}
