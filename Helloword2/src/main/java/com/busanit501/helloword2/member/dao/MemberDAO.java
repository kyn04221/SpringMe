package com.busanit501.helloword2.member.dao;

import com.busanit501.helloword2.member.dto.MemberVO;
import com.busanit501.helloword2.member.dto.TodoVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    //하나 저장하기---------------------------------------------
    public void insert(MemberVO vo) throws SQLException {
        String sql = "insert into member(mid, mpw, mname) values(?,?,?)";

        @Cleanup Connection con = MemberConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pr = con.prepareStatement(sql);

        pr.setString(1, vo.getMid());
        pr.setString(2, vo.getMpw());
        pr.setString(3,vo.getMname());
        pr.executeUpdate();
    }

    //전체 조회하기---------------------------------------------
    public List<MemberVO> selectAll() throws SQLException {
        String sql = "select * from member";
        @Cleanup Connection con = MemberConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = con.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        // 넘어온 데이터를 임시로 보관할 리스트 인스턴스 만들고,
        // 반복문 통해서, 넘어온 각행을 리스트에 요소로 하나씩 담기.
        List<MemberVO> list = new ArrayList<>();
        while (resultSet.next()) {
            MemberVO memberVO = MemberVO.builder()
                    .mno(resultSet.getLong("mno"))
                    .mid(resultSet.getString("mid"))
                    .mpw(resultSet.getString("mpw"))
                    .mname(resultSet.getString("mname"))
                    .build();
            list.add(memberVO);
        }
        return  list;
    }

    //하나 조회하기---------------------------------------------
    public MemberVO selectOne(Long mno) throws SQLException{
        String sql = "select * from member where mno = ?";
        @Cleanup Connection con = MemberConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pre = con.prepareStatement(sql);

        pre.setLong(1, mno);
        @Cleanup ResultSet rs = pre.executeQuery();

        rs.next();
        MemberVO memberVO = MemberVO.builder()
                .mno(rs.getLong("mno"))
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .mname(rs.getString("mname"))
                .build();
        return memberVO;
    }

    //삭제---------------------------------------------
    public void deletemember(Long mno) throws SQLException {
        String sql = "delete from member where mno =?";
        @Cleanup Connection con = MemberConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setLong(1, mno);
        preparedStatement.executeUpdate();

    }
    //수정---------------------------------------------
    public void updateOne(MemberVO VO) throws SQLException {
        String sql = " update member set mid=?, mpw=?, mname=?" +
                " where mno=?";
        @Cleanup Connection con = MemberConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = con.prepareStatement(sql);
        // 화면에서 넘겨받은 변경할 데이터를 DTO -> VO 변환 후에(서비스에서 할 예정.)
        // VO 에서 꺼내서, 디비로 데이터 전달하는 과정.
        preparedStatement.setString(1, VO.getMid());
        preparedStatement.setString(2, VO.getMpw());
        preparedStatement.setString(3,VO.getMname());
        preparedStatement.setLong(4,VO.getMno());
        preparedStatement.executeUpdate();

    }


    public String getTim(){
        String now = null;

        try (Connection con = MemberConnectionUtil.INSTANCE.getConnection();
             PreparedStatement pr = con.prepareStatement("select now()");
             ResultSet resultSet = pr.executeQuery();
        ) {
            resultSet.next();
            now = resultSet.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } //catch
        return now;
    } //getTime

    public String getTime2() throws SQLException, SQLException {
        String now = null;
        // 자동으로 디비의 connection 반납하는 방법2
        // @Cleanup
        @Cleanup Connection connection = MemberConnectionUtil.INSTANCE.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select now()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        now = resultSet.getString(1);
        return now;
    }
    }




