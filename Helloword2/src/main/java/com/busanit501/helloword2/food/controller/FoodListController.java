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
import java.util.List;


@Log4j2
@WebServlet(name = "foodListController", urlPatterns = "/food/list")

public class FoodListController extends HttpServlet {

    private FoodService foodService = FoodService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet TodoList2Controller");

        try{
            List<FoodDTO> foodList = foodService.listAll();
            req.setAttribute("List", foodList);
            req.getRequestDispatcher("/WEB-INF/food/foodList.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/food/foodList.jsp");
//        rd.forward(req, resp);

          // req.getRequestDispatcher("/WEB-INF/food/foodList.jsp") .forward(req, resp);
    }
}