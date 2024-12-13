package com.busanit501.minitest.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodVO {
    private Long tno;
    private String menu;
    private String price;
    private LocalDate dueDate;
    private boolean finished;

}