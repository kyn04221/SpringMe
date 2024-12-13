package com.busanit501.boot501.food.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Efood extends EfoodEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;


    @Column(length = 500, nullable = false)
    private String menu;

    @Column(length = 2000, nullable = false)
    private String price;

    @Column(length = 50, nullable = false)
    private String writer;

    public void changeMenuConent(String menu, String price) {
        this.menu = menu;
        this.price = price;
    }
}
