package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.QRecipe;
import com.busanit501.boot501.domain.Recipe;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class RecipeSearchImpl extends QuerydslRepositorySupport
        implements RecipeSearch {

    public RecipeSearchImpl() {
        super(Recipe.class);
    }

    @Override
    public Page<Recipe> search(Pageable pageable) {

        QRecipe recipe = QRecipe.recipe;
        JPQLQuery<Recipe> query = from(recipe);
        query.where(recipe.recipename.contains("3"));

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(recipe.recipename.contains("3"));
        booleanBuilder.or(recipe.ingredients.contains("7"));

        query.where(booleanBuilder);
        query.where(recipe.recipeid.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Recipe> list = query.fetch();

        long total = query.fetchCount();

        return null;
    }

    @Override
    public Page<Recipe> searchAll(String[] types, String keyword, Pageable pageable) {
        QRecipe recipe = QRecipe.recipe;
        JPQLQuery<Recipe> query = from(recipe);
        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(recipe.recipename.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(recipe.ingredients.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(recipe.recipeid.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Recipe> list = query.fetch();

        long total = query.fetchCount();

        Page<Recipe> result = new PageImpl<Recipe>(list, pageable, total);

        return result;
    }
}
