package com.busanit501.helloword2.food.controller;


import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Log4j2
@WebServlet(name = "FoodLogoutController", urlPatterns = "/foodlogout")

public class FoodLogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("doPost FoodLogoutController");

        HttpSession session = req.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate();

        resp.sendRedirect("/");
    }


}
