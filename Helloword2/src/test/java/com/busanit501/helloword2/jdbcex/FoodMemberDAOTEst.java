package com.busanit501.helloword2.jdbcex;

import com.busanit501.helloword2.food.dao.FoodMemberDAO;
import com.busanit501.helloword2.food.dto.FoodMemberVO;
import com.busanit501.helloword2.jdbc.dao.MemberDAO;
import com.busanit501.helloword2.jdbc.dto.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Log4j2
public class FoodMemberDAOTEst {

    private FoodMemberDAO fmDAO;

    @BeforeEach
    public void ready() {
        fmDAO = new FoodMemberDAO();
    }

    @Test
    public void getMemberWithpwTest() throws SQLException {
        String mid = "123";
        String mpw = "123";

        FoodMemberVO fmvo = fmDAO.getMemberWithpw(mid,mpw);
        log.info("memberVO 확인" + fmvo);
    }
}
