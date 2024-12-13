package com.busanit501.helloword2.food.dao;

import com.busanit501.helloword2.food.dto.FoodMemberVO;
import com.busanit501.helloword2.food.service.FoodConnectionUtil;

import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//입력받은 값 vo에 저장
public class FoodMemberDAO {
    public FoodMemberVO getMemberWithpw(String mid, String mpw) throws SQLException {
            String query = "select * from foodmember where mid=? and mpw=?";
            // 결과 데이터를 담아둘 임시 박스 MemberVO
            FoodMemberVO foodmemberVO = null;
            @Cleanup Connection con = FoodConnectionUtil.INSTANCE.getConnection();
            @Cleanup PreparedStatement pre = con.prepareStatement(query);
        pre.setString(1, mid);
        pre.setString(2, mpw);
        @Cleanup ResultSet resultSet = pre.executeQuery();
        resultSet.next();

        foodmemberVO = FoodMemberVO.builder()
                .mid(resultSet.getString("mid"))
                .mpw(resultSet.getString("mpw"))
                .mname(resultSet.getString("mname"))
                .build();

        return foodmemberVO;

    }

    //uuid 추가
    public void updateUuid(String mid, String uuid) throws SQLException {
        String query = "update foodmember set uuid=? where mid=?";

        @Cleanup Connection connection = FoodConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);
        preparedStatement.setString(2, mid);
        preparedStatement.executeUpdate();
    } //

    //uuid로 유저를 검색하는 기능.
    public FoodMemberVO getMemberWithUuid(String uuid) throws SQLException {
        String query = "select * from foodmember where uuid=?";
        // 결과 데이터를 담아둘 임시 박스 MemberVO
        FoodMemberVO fmVO = null;

        @Cleanup Connection connection = FoodConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        fmVO = FoodMemberVO.builder()
                .mid(resultSet.getString("mid"))
                .mpw(resultSet.getString("mpw"))
                .mname(resultSet.getString("mname"))
                .uuid(resultSet.getString("uuid"))
                .build();

        return fmVO;
    }
}
