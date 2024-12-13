package com.busanit501.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDTO {
    private Long recipe_id;
    @NotEmpty
    private String recipe_name;

    @NotEmpty
    private String ingredients;

    @NotEmpty
    private String method;
    private String urlsource;

    private LocalDateTime createday;
    private LocalDateTime updateday;

    private LocalDate from;
    private LocalDate to;

}