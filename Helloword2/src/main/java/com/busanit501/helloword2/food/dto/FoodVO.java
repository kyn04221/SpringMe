package com.busanit501.helloword2.food.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@Data
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든멤버의 파라미터로 이용한 생성자
public class FoodVO {
    private Long tno;
    private String menu;
    private String price;
//    private LocalDate dueDate;
//    private boolean finished;
}
