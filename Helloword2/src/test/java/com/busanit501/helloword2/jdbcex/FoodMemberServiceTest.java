package com.busanit501.helloword2.jdbcex;

import com.busanit501.helloword2.food.dto.FoodMemberDTO;
import com.busanit501.helloword2.food.service.FoodMemberService;

import com.busanit501.helloword2.jdbc.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;


@Log4j2
public class FoodMemberServiceTest {


    private FoodMemberService fmService;

    @BeforeEach
    public void ready() {
        fmService = FoodMemberService.INSTANCE;
    }
    // 등록
    @Test
    public void loginTest() throws SQLException {
        FoodMemberDTO fmDTO = fmService.login("123", "123");
        log.info("MemberService loginTest : " + fmDTO.toString());
    }
    @Test
    public void updateUuidTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        fmService.updateUuid("123",uuid);
    }

    // uuid 이용해서 검색해보기.
    @Test
    public void getMemberWithUuidSearch() throws SQLException {
        // 각자 테이블의 유저의uuid를 직접 복사해서 붙여넣기.
        // 각각 전부 다 달라요.
        FoodMemberDTO fmDTO = fmService.getMemberWithUuidService("a28a80a5-e716-4917-8cb5-8caa15cedf86");
        log.info("memberDTO : " + fmDTO);
    }

}
