package com.busanit501.helloword2.food.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@Data
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든멤버의 파라미터로 이용한 생성자
public class FoodDTO {
    private Long tno;
    private String menu;
    private String price;
}
