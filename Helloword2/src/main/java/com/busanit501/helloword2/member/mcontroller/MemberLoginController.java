package com.busanit501.helloword2.member.mcontroller;

import com.busanit501.helloword2.member.dto.MemberDTO;
import com.busanit501.helloword2.member.service.MemberLoginService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "MemberLoginController", urlPatterns = "/member/mlogin")
public class MemberLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("MemberLoginController doGet ");
        req.getRequestDispatcher("/WEB-INF/member/login.jsp")
                .forward(req, resp);
    }

    // 로직 처리,
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("MemberLoginController doPost ");
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");


        try {
            MemberDTO memberDTO = MemberLoginService.INSTANCE.login(mid, mpw);
            // 세션에, 위의 로그인 정보를 저장,
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", memberDTO);
            resp.sendRedirect("/member/list");
        } catch (SQLException e) {
            resp.sendRedirect("/mlogin?result=error");
        }

    }


}