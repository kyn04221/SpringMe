package com.busanit501.boot501.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EfoodDTO {
    private  Long bno;
    private  String menu;
    private  String price;
    private  String writer;
//    private LocalDateTime regDate;
//    private LocalDateTime modDate;
}
