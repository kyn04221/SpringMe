package com.busanit501.helloword2.jdbc.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든멤버의 파라미터로 이용한 생성자
public class TodoDTO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

}
