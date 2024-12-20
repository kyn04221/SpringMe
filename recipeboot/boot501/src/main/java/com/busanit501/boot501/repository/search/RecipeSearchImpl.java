package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.QRReply;
import com.busanit501.boot501.domain.QRecipe;
import com.busanit501.boot501.domain.Recipe;

import com.busanit501.boot501.dto.RecipeListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
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

    @Override
    public Page<RecipeListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QRecipe recipe = QRecipe.recipe;
        QRReply reply = QRReply.rReply;
        JPQLQuery<Recipe> query = from(recipe);
        // 조인 설정 , 게시글에서 댓글에 포함된 게시글 번호와 , 게시글 번호 일치
        query.leftJoin(reply).on(reply.recipe.recipeid.eq(recipe.recipeid));
        query.groupBy(recipe);


        if (types != null && types.length > 0 && keyword != null) {
            // 여러 조건을 하나의 객체에 담기.
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "n":
                        booleanBuilder.or(recipe.recipename.contains(keyword));
                        break;
                    case "g":
                        booleanBuilder.or(recipe.ingredients.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(recipe.writer.contains(keyword));
                        break;
                } // switch
            }// end for
            // where 조건을 적용해보기.
            query.where(booleanBuilder);
        } //end if
        // bno >0
        query.where(recipe.recipeid.gt(0L));

        JPQLQuery<RecipeListReplyCountDTO> dtoQuery =
                query.select(Projections.bean(RecipeListReplyCountDTO.class, //> bean형변환할 대상책
                        recipe.recipeid,
                        recipe.recipename,
                        recipe.ingredients,
                        recipe.method,
                        recipe.writer,
//                        recipe.createday,
                        recipe.createday,
                        reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<RecipeListReplyCountDTO> list = dtoQuery.fetch();

        long total = dtoQuery.fetchCount();

        Page<RecipeListReplyCountDTO> result = new PageImpl<RecipeListReplyCountDTO>(list, pageable, total);

        return result;

    }
}
