package com.busanit501.boot501.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecipe is a Querydsl query type for Recipe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipe extends EntityPathBase<Recipe> {

    private static final long serialVersionUID = -367065941L;

    public static final QRecipe recipe = new QRecipe("recipe");

    public final QRecipeEntity _super = new QRecipeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createday = _super.createday;

    public final StringPath ingredients = createString("ingredients");

    public final StringPath method = createString("method");

    public final NumberPath<Long> recipeid = createNumber("recipeid", Long.class);

    public final StringPath recipename = createString("recipename");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateday = _super.updateday;

    public final StringPath urlsource = createString("urlsource");

    public QRecipe(String variable) {
        super(Recipe.class, forVariable(variable));
    }

    public QRecipe(Path<? extends Recipe> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecipe(PathMetadata metadata) {
        super(Recipe.class, metadata);
    }

}

