package com.busanit501.helloword2.login2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    @WebServlet(name = "LoginInputController", urlPatterns = "/login/input")
    public class LoginInputController extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("서블릿 접근 후 화면 접속");

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/todo/odoList.jsp");


            rd.forward(req, resp);
        }


    }