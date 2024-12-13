package com.busanit501.helloword2.jdbc.dao;

import com.busanit501.helloword2.jdbc.dto.TodoVO;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    //하나 저장하기---------------------------------------------
    public void insert(TodoVO vo) throws SQLException {
        String sql = "insert into tbl_todo(title, dueDate, finished) values(?,?,?)";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pr = con.prepareStatement(sql);

        pr.setString(1, vo.getTitle());
        pr.setDate(2, Date.valueOf(vo.getDueDate()));
        pr.setBoolean(3, vo.isFinished());

        pr.executeUpdate();
    }

    //전체 조회하기---------------------------------------------
    public List<TodoVO> selectAll() throws SQLException {
        String sql = "select * from tbl_todo";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        // 넘어온 데이터를 임시로 보관할 리스트 인스턴스 만들고,
        // 반복문 통해서, 넘어온 각행을 리스트에 요소로 하나씩 담기.
        List<TodoVO> list = new ArrayList<>();
        while (resultSet.next()) {
            TodoVO todoVO = TodoVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(todoVO);
        }
        return  list;
    }

    //하나 조회하기---------------------------------------------
    public TodoVO selectOne(Long tno) throws SQLException{
        String sql = "select * from tbl_todo where tno = ?";
        @Cleanup Connection con = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pre = con.prepareStatement(sql);

        pre.setLong(1, tno);
        @Cleanup ResultSet rs = pre.executeQuery();

        rs.next();
        TodoVO todoVO = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .finished(rs.getBoolean("finished"))
                .build();
        return todoVO;
    }

    //삭제---------------------------------------------
    public void deleteTodo(Long tno) throws SQLException {
        String sql = "delete from tbl_todo where tno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        preparedStatement.executeUpdate();

    }
    //수정---------------------------------------------
    public void updateOne(TodoVO todoVO) throws SQLException {
        String sql = " update tbl_todo set title=?, dueDate=?, finished=?" +
                " where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 화면에서 넘겨받은 변경할 데이터를 DTO -> VO 변환 후에(서비스에서 할 예정.)
        // VO 에서 꺼내서, 디비로 데이터 전달하는 과정.
        preparedStatement.setString(1, todoVO.getTitle());
        preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
        preparedStatement.setBoolean(3,todoVO.isFinished());
        preparedStatement.setLong(4,todoVO.getTno());
        preparedStatement.executeUpdate();

    }


    public String getTim(){
        String now = null;

        try (Connection con = ConnectionUtil.INSTANCE.getConnection();
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
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("select now()");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        now = resultSet.getString(1);
        return now;
    }
    }




