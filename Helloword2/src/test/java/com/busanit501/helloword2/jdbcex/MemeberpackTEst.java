package com.busanit501.helloword2.jdbcex;


import com.busanit501.helloword2.member.dto.MemberVO;
import com.busanit501.helloword2.member.dao.MemberDAO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class MemeberpackTEst {
    private MemberDAO memberDAO;

    @BeforeEach
    public void ready(){
        memberDAO = new MemberDAO();
    }

    @Test
    public void gettime(){
        System.out.println("sql전달 후 시간 조회"+memberDAO.getTim());
    }

    @Test
    public void getTime2() throws SQLException, SQLException {
        System.out.println("sql 전달 후, " +
                "시간 조회 확인용: 자동 반납 @Cleanup 확인 "+memberDAO.getTime2());
    }



    //하나 저장하기---------------------------------------------
    @Test
    public void insert() throws SQLException{

        MemberVO memberVo = MemberVO.builder()
                .mid("123")
                .mpw("123")
                .mname("123")
                .build();

        memberDAO.insert(memberVo);
    }

    //전체 조회하기---------------------------------------------
    @Test
    public void testList() throws SQLException{
        List<MemberVO> list = memberDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    //하나 조회하기---------------------------------------------
    @Test
    public void getOneTEst() throws SQLException{
        Long tmo = 3L;
        MemberVO vo = memberDAO.selectOne(tmo);
        System.out.println(vo);
    }

    //삭제----------------------------------------------------
    @Test
    public void deleteTest() throws SQLException {
        Long tmo = 3L;
        memberDAO.deletemember(tmo);
    }

    //수정----------------------------------------------------
    @Test
    public void updateTest() throws SQLException {
        // 실제 작업은 내용을 화면에서 받아오는 대신,
        // 하드 코딩으로 값을 더미로 테스트.
        MemberVO VO = MemberVO.builder()
                .mno(2L)
                .mid("456")
                .mpw("456")
                .mname("456")
                .build();
        memberDAO.updateOne(VO);
    }
}
