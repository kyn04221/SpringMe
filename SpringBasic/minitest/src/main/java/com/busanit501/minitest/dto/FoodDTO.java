package com.busanit501.minitest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDTO {
    private Long tno;
    @NotEmpty
    private String menu;
    @NotEmpty
    private String price;
    @Future
    private LocalDate dueDate;
    private boolean finished;
}