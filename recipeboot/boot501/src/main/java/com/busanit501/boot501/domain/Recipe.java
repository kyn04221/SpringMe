package com.busanit501.boot501.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Recipe extends RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeid;

    @Column(length = 100, nullable = false)
    private String recipename;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String method;

    @Column(length = 50, nullable = false)// 길이 500자,  NotNull=nn
    private String writer;

    @Column(nullable = false)
    private String urlsource;

    public void changeRecipeConent(String recipename, String ingredients, String method,
                                   String writer,String urlsource) {
        this.recipename = recipename;
        this.ingredients = ingredients;
        this.method = method;
        this.writer = writer;
        this.urlsource = urlsource;

    }
}