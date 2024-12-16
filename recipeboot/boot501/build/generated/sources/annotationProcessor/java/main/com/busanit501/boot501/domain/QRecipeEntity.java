package com.busanit501.boot501.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecipeEntity is a Querydsl query type for RecipeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QRecipeEntity extends EntityPathBase<RecipeEntity> {

    private static final long serialVersionUID = 26765422L;

    public static final QRecipeEntity recipeEntity = new QRecipeEntity("recipeEntity");

    public final DateTimePath<java.time.LocalDateTime> createday = createDateTime("createday", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updateday = createDateTime("updateday", java.time.LocalDateTime.class);

    public QRecipeEntity(String variable) {
        super(RecipeEntity.class, forVariable(variable));
    }

    public QRecipeEntity(Path<? extends RecipeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecipeEntity(PathMetadata metadata) {
        super(RecipeEntity.class, metadata);
    }

}

