package com.busanit501.helloword2.member.mcontroller;


import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Log4j2
@WebServlet(name = "LogoutController", urlPatterns = "/logout")
public class MemberLogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("doPost LogoutController");

        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate();

        resp.sendRedirect("/");
    }


}
