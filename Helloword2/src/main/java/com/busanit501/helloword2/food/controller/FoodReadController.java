package com.busanit501.helloword2.food.controller;

import com.busanit501.helloword2.food.dto.FoodDTO;
import com.busanit501.helloword2.food.service.FoodService;
import com.busanit501.helloword2.jdbc.dto.TodoDTO;
import com.busanit501.helloword2.jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "FoodReadController", urlPatterns = "/food/read")

public class FoodReadController extends HttpServlet {

    private FoodService foodService = FoodService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        System.out.println("doGet TodoRead2Controller 하나 조회 예제");


        try {
            // 클릭한 게시글 번호를 가지고 와야함.
            Long tno = Long.parseLong(request.getParameter("tno"));
            FoodDTO foodDTO = foodService.get(tno);
            // 화면에 전달하기.
            request.setAttribute("dto", foodDTO);
            request.getRequestDispatcher("/WEB-INF/food/foodRead.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}