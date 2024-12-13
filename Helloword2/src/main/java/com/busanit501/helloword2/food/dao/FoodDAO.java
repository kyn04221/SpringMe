package com.busanit501.helloword2.food.dao;

import com.busanit501.helloword2.food.dto.FoodVO;
import com.busanit501.helloword2.food.service.FoodConnectionUtil;
import com.busanit501.helloword2.jdbc.dao.ConnectionUtil;
import com.busanit501.helloword2.jdbc.dto.TodoVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {

    //하나 저장하기---------------------------------------------
    public void insert(FoodVO vo) throws SQLException {
        String sql = "insert into food(menu, price) values(?,?)";

        @Cleanup Connection con = FoodConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pr = con.prepareStatement(sql);

        pr.setString(1, vo.getMenu());
        pr.setString(2, vo.getPrice());

        pr.executeUpdate();
    }

    //vo로 DB 조회하기---------------------------------------------
    public List<FoodVO> selectAll() throws SQLException {
        String sql = "select * from food"; // 파생언어 질문하기
        @Cleanup Connection con = FoodConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pr = con.prepareStatement(sql);
        @Cleanup ResultSet rs = pr.executeQuery();

        List<FoodVO> list = new ArrayList<>();
        while (rs.next()) {
            FoodVO vo = FoodVO.builder()
                    .tno(rs.getLong("tno"))
                    .menu(rs.getString("menu"))
                    .price(rs.getString("price"))
                    .build();
            list.add(vo);
        }
        return  list;
    }

    //하나 조회하기---------------------------------------------
    public FoodVO selectOne(Long tno) throws SQLException{
        String sql = "select * from food where tno = ?";
        @Cleanup Connection con = FoodConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pre = con.prepareStatement(sql);

        pre.setLong(1, tno);
        @Cleanup ResultSet rs = pre.executeQuery();

        rs.next();
        FoodVO vo = FoodVO.builder()
                .tno(rs.getLong("tno"))
                .menu(rs.getString("menu"))
                .price(rs.getString("price"))
                .build();
        return vo;
    }

    //삭제---------------------------------------------
    public void deleteFood(Long tno) throws SQLException {
        String sql = "delete from food where tno =?";
        @Cleanup Connection con = FoodConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pre = con.prepareStatement(sql);

        pre.setLong(1, tno);
        pre.executeUpdate();

    }
    //수정---------------------------------------------
    public void updateOne(FoodVO foodVO) throws SQLException {
        String sql = " update food set menu=?, price=? where tno=?";
        @Cleanup Connection con = FoodConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pre = con.prepareStatement(sql);


        pre.setString(1, foodVO.getMenu());
        pre.setString(2, foodVO.getPrice());
        pre.setLong(3,foodVO.getTno());

        pre.executeUpdate();

    }

    }

