package com.busanit501.helloword2.jdbc.dao;

import com.busanit501.helloword2.jdbc.dto.MemberVO;
import lombok.Cleanup;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
    public MemberVO getMemberWithpw(String mid, String mpw) throws SQLException {
        String query = "select * from tbl_member where mid=? and mpw=?";
        // 결과 데이터를 담아둘 임시 박스 MemberVO
        MemberVO memberVO = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, mid);
        preparedStatement.setString(2, mpw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        memberVO = MemberVO.builder()
                .mid(resultSet.getString("mid"))
                .mpw(resultSet.getString("mpw"))
                .mname(resultSet.getString("mname"))
                .build();
        return memberVO;

    }
    //uuid 추가
    public void updateUuid(String mid, String uuid) throws SQLException {
        String query = "update tbl_member set uuid=? where mid=?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);
        preparedStatement.setString(2, mid);
        preparedStatement.executeUpdate();
    } //

    //uuid로 유저를 검색하는 기능.
    public MemberVO getMemberWithUuid(String uuid) throws SQLException {
        String query = "select * from tbl_member where uuid=?";
        // 결과 데이터를 담아둘 임시 박스 MemberVO
        MemberVO memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        memberVO = MemberVO.builder()
                .mid(resultSet.getString("mid"))
                .mpw(resultSet.getString("mpw"))
                .mname(resultSet.getString("mname"))
                .uuid(resultSet.getString("uuid"))
                .build();

        return memberVO;
    }

}
