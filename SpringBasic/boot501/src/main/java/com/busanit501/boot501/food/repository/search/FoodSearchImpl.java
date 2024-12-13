package com.busanit501.boot501.food.repository.search;


import com.busanit501.boot501.food.domain.Efood;
import com.busanit501.boot501.food.domain.QEfood;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class FoodSearchImpl extends QuerydslRepositorySupport
        implements FoodSearch {

    public FoodSearchImpl() {
        super(Efood.class);
    }

    @Override
    public Page<Efood> search(Pageable pageable) {
        QEfood efood = QEfood.efood;
        JPQLQuery<Efood> query = from(efood);

        query.where(efood.menu.contains("3"));

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(efood.menu.contains("3"));
        booleanBuilder.or(efood.price.contains("7"));

        query.where(booleanBuilder);
        query.where(efood.bno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Efood> list = query.fetch();
        long total = query.fetchCount();

        return null;
    }

    @Override
    public Page<Efood> searchAll(String[] types, String keyword, Pageable pageable) {
        QEfood efood = QEfood.efood;
        JPQLQuery<Efood> query = from(efood);

        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "m":
                        booleanBuilder.or(efood.menu.contains(keyword));
                        break;
                    case "p":
                        booleanBuilder.or(efood.price.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(efood.writer.contains(keyword));
                        break;
                } // switch
            }// end for
            query.where(booleanBuilder);
        } //end if
        query.where(efood.bno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);

        List<Efood> list = query.fetch();
        long total = query.fetchCount();

        Page<Efood> result = new PageImpl<Efood>(list, pageable, total);

        return result;
    }
}