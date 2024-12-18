package com.busanit501.boot501.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
// 복합키 인덱스, 순서 중요함,
// 순서, 댓글 번호로 1차 기준 정렬, 게시글 번호 2차 기준 정렬,
// 검색시, 전체 검색을 안하고, 색인 검색(index scan)
// 검색시, 성능 향상을 위해서함,
// 주의사항, 너무 복잡한 복합키 설정이나, 또는 많은 복합키를 사용하게 되면,
// 디비 자체에서 부하가 걸려서 효율적이지 못함, 가급적 너무 많은 복합키 사용은 주의.
@Table(name = "rreply", indexes = {
        @Index(name = "idx_reply_recipe_recipeid", columnList = "recipe_recipeid")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString// db에 접근시, 2번접근 안함.
// 단위 테스트 기본 설정상, 하나의 테이블에 한번 접근함.
//(exclude = "board") , 사용 안하고,
// 다른 테이블도 같이 조회를 해야한다면, @Transaction 설정
// 단위테스트 메서드에 추가하기.
public class RReply extends RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;// 댓글의 구분 번호,

    //중요, 여기서 연관관계 설정, 외래키 설정,
    // 마치, 데이터베이스에서 설정하는 것과 비슷
    @ManyToOne(fetch = FetchType.LAZY) // 사용하는 시점에 조회함.
    // FetchType.EAGER , 즉시 로딩, 사용 안해도 조회 하겠다.
    private Recipe recipe; // 부모의 게시글 번호,

    private String replyText;

    private String replyer;

    public void changeRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void changeReplyText(String replyText) {
        this.replyText = replyText;
    }


}
