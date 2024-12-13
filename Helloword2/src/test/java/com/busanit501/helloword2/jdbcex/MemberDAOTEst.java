package com.busanit501.helloword2.jdbcex;

import com.busanit501.helloword2.jdbc.dao.MemberDAO;
import com.busanit501.helloword2.jdbc.dto.MemberDTO;
import com.busanit501.helloword2.jdbc.dto.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;


@Log4j2
public class MemberDAOTEst {

    private MemberDAO memberDAO;

    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }
    @Test
    public void getMemberWithpwTest() throws SQLException {
        String mid = "lsy";
        String mpw = "1234";

        MemberVO memberVO = memberDAO.getMemberWithpw(mid,mpw);
        log.info("memberVO 확인" + memberVO);
    }

    @Test
    public void updatemember() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        log.info("uuid 랜덤 문자열 생성"+ uuid);
        memberDAO.updateUuid("123", uuid);
    }
    @Test
    public void updateUuidTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
//        memberService.updateUuid("lsy",uuid);
//        memberService.updateUuid("lsy2",uuid);
        memberDAO.updateUuid("123",uuid);

        //uuid 로 유저 검색

    }
    @Test
    public void getMemberWithUuidTest() throws SQLException {

        // 각자 테이블의 유저의uuid를 직접 복사해서 붙여넣기.
        // 각각 전부 다 달라요.
        MemberVO memberVO = memberDAO.getMemberWithUuid("a28a80a5-e716-4917-8cb5-8caa15cedf86");
        log.info("memberVO : " + memberVO);

    }

}
