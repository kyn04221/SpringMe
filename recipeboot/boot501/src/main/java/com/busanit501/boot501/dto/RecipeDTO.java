package com.busanit501.boot501.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private  Long recipeid;
    private  String recipename;
    private  String ingredients;
    private  String method;
    private  String urlsource;
    private LocalDateTime createday;
    private LocalDateTime updateday;
}
