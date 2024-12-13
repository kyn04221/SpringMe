package com.busanit501.helloword2.calc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "calcinputController", urlPatterns = "/calc/input")

public class CalcinputController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet 호출 함");

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/calc/calc_input.jsp");
        rd.forward(req, resp);
        req.getRequestDispatcher("/WEB-INF/calc/calc_input.jsp").forward(req, resp);
    }


}
