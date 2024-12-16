//package com.busanit501.boot501.repository;
//
//import com.busanit501.boot501.domain.Recipe;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.IntStream;
//
//@SpringBootTest
//@Log4j2
//public class RecipeReopositoryTests {
//
//    @Autowired
//    // 아무 메소드가 없지만, 기본 탑재된 쿼리 메소드 이용해서, crud  해보기.
//    private RecipeRepository recipeRepository;
//
//    @Test
//    public void testInsert() {
//        // 더미 데이터, 앞에서, 병렬 처리 기능 사용하기.
//        // stream 클래스 이용하기.
//        // 1 ~ 99번까지 생성해요.
//        IntStream.range(1, 2).forEach(i -> {
//            Recipe recipe = Recipe.builder()
//                    .recipeName("김치찌개 " + i)
//                    .ingredients("고기 김치 양파 " + i)
//                    .method("모두 넣고 끓인다 " + i)
//                    .urlsource("http://" + i)
//                    .build();
//            Recipe result = boardRepository.save(board);
//            log.info("추가된 recipeid 번호 : " + result);
//        });
//    }
//
//    @Test
//    public void testSelectOne() {
//        Long recipeid = 99L;
//        //Optional , 있으면, 해당 인스턴스 가져오기, 없으면, null 입니다.
//        Optional<Board> result = boardRepository.findById(recipeid);
//        // result 있으면, Board 타입으로 받고, 없으면, 예외 발생시킴.
//        Board board = result.orElseThrow();
//        log.info("하나 조회 : " + board);
//
//    }
//
//    @Test
//    public void testSelectAll() {
//
//        List<Board> result = boardRepository.findAll();
//        // result 있으면, Board 타입으로 받고, 없으면, 예외 발생시킴.
//        for (Board board : result) {
//            log.info(board);
//        }
//
//
//    }
//
//    @Test
//    public void testUpdate() {
//        Long recipeid = 98L;
//        // 수정 할 데이터가 해당 테이블에 있는지 조회 부터 하기.
//        Optional<Board> result = boardRepository.findById(recipeid);
//        // result 있으면, Board 타입으로 받고, 없으면, 예외 발생시킴.
//        // board, 엔티티 클래스 인스턴스가, 하나의 데이터베이스의 내용임.
//        Board board = result.orElseThrow();
//        board.changeTitleConent("변경 제목 ", "변경 내용");
//        // 실제 디비 테이블 반영.
//        // 순서 -> 1차 영속성 컨텍스트(임시 테이블) 적용 -> 실제 테이블 반영.
//        // save -> 해당 실제 테이블 없다면, -> insert
//        // save -> 해당 실제 테이블 있다면, -> update
//
//        boardRepository.save(board);
//
//    }
//
//    @Test
//    public void testDelete() {
//        Long recipeid = 99L;
//        boardRepository.deleteById(recipeid);
//    }
//
//    @Test
//    public void testPaging() {
//
//        // 0 -> 1페이지, 1 -> 2페이지
//        Pageable pageable = PageRequest.of(1, 10,
//                Sort.by("recipe_id").descending());
//        Page<Board> result = boardRepository.findAll(pageable);
//        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
//        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
//        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
//        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
//        log.info("result.getSize() 크기  :" + result.getSize());
//    }
//
//    // 방법1 , 쿼리스트링
//    @Test
//    public void testQueryString() {
//        Pageable pageable = PageRequest.of(1, 10,
//                Sort.by("bno").descending());
//        Page<Board> result = boardRepository.findByTitleContainingOrderByBnoDesc(
//                "3", pageable
//        );
//        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
//        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
//        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
//        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
//        log.info("result.getSize() 크기  :" + result.getSize());
//    }
//
//    // 방법2 , @Query
//    @Test
//    public void testQueryAnotation() {
//        Pageable pageable = PageRequest.of(1, 10,
//                Sort.by("bno").descending());
//        Page<Board> result = boardRepository.findByKeyword("3", pageable);
//
//        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
//        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
//        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
//        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
//        log.info("result.getSize() 크기  :" + result.getSize());
//    }
//
//    // 방버3 Querydsl
//    // 단계적으로, sql 문장만 일단 확인중. , 아직 메서드 완성 안됨.
//    // 연습용
//    @Test
//    public void testQuerydsl() {
//        Pageable pageable = PageRequest.of(1, 10,
//                Sort.by("bno").descending());
////        Page<Board> result = boardRepository.search(pageable);
//          boardRepository.search(pageable);
//
////        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
////        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
////        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
////        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
////        log.info("result.getSize() 크기  :" +result.getSize());
//    }
//
//    @Test
//    public void testQuerydsl2() {
//        Pageable pageable = PageRequest.of(1, 10,
//                Sort.by("bno").descending());
//
//        // 전달할 준비물
//        // 1) 검색어, 2) 검색 유형
//        String keyword = "샘플";
//        String[] types = {"t","w","c"};
//
//        Page<Board> result = boardRepository.searchAll(types,keyword,pageable);
//
//        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
//        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
//        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
//        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
//        log.info("result.getSize() 크기  :" +result.getSize());
//        log.info("result.hasNext() 다음  :" +result.hasNext());
//        log.info("result.hasPrevious() 이전  :" +result.hasPrevious());
//    }
//
//
//}
