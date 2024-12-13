package com.busanit501.helloword2.member.mcontroller;

import com.busanit501.helloword2.member.dto.MemberDTO;
import com.busanit501.helloword2.member.dto.TodoDTO;
import com.busanit501.helloword2.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name = "MemberListController", urlPatterns = "/member/list")
public class MemberListController extends HttpServlet {

    private MemberService memberoService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet TodoList2Controller");

        try{
            List<MemberDTO> memberList = memberoService.listAll();
            req.setAttribute("List", memberList);
            req.getRequestDispatcher("/WEB-INF/member/memberList.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        List<TodoDTO> todoList = TodoService.INSTANCE.getList();
//        req.setAttribute("list", todoList);

    }
}