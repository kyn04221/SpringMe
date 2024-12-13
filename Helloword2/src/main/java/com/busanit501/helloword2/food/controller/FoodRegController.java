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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name = "foodRegController", urlPatterns = "/food/register")

public class FoodRegController extends HttpServlet {

    private FoodService foodService = FoodService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet FoodRegController");
        req.getRequestDispatcher("/WEB-INF/food/foodReg.jsp").forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FoodDTO foodDTO = FoodDTO.builder()
                .menu(req.getParameter("menu"))
                .price(req.getParameter("price"))
                .build();
        log.info("doPost TodoReg2Controller");
        log.info(foodDTO);

        try{
            foodService.register(foodDTO);
        }catch(Exception e){
            e.printStackTrace();
        }

        resp.sendRedirect("/food/list");
    }
}