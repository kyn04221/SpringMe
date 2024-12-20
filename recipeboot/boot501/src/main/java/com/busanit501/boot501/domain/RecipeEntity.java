package com.busanit501.boot501.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
abstract class RecipeEntity {

    @CreatedDate
    @Column(name="createday", updatable = false)
    private LocalDateTime createday;

    @LastModifiedDate
    @Column(name="updateday", updatable = true)
    private LocalDateTime updateday;
}
