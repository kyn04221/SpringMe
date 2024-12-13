package com.busanit501.helloword2.jdbc.controller2;

import com.busanit501.helloword2.jdbc.dto.TodoDTO;
import com.busanit501.helloword2.jdbc.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name = "TodoList2Controller", urlPatterns = "/todo/list2")
public class TodoList2Controller extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = req.getServletContext();
        String result = (String) context.getAttribute("appTestName");
        log.info("TodoList2Controller ServletContext : 값 조회 확인중 : "+result);

        log.info("doGet TodoList2Controller");

        try{
            List<TodoDTO> todoList = todoService.listAll();
            req.setAttribute("List", todoList);
            req.getRequestDispatcher("/WEB-INF/todo2/todoList2.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        List<TodoDTO> todoList = TodoService.INSTANCE.getList();
//        req.setAttribute("list", todoList);

    }
}