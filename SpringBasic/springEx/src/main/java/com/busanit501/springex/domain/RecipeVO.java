package com.busanit501.springex.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecipeVO {
    private Long recipe_id;           //번호
    private String recipe_name;       //요리이름
    private String ingredients;       //재료
    private String method;            //요리 방법
    private String urlsource;         //출처 URL
    private LocalDateTime createday;  // 생성일
    private LocalDateTime updateday;  // 수정일

}


