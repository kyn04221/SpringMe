package com.busanit501.helloword2.food.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodMemberDTO {
    private String mid;
    private String mpw;
    private String mname;
    private String uuid;
}
