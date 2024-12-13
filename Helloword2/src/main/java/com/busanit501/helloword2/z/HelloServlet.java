package com.busanit501.helloword2.z;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {

        //생명주기 확인용
        //최초생성
        System.out.println("최초 호출됨");
        message = "Hello World!";
    }
//호출마다 호출됨
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + 123123 + "</h1>");
        out.println("</body></html>");
        out.println("호출마다 호출됨");

    }
//소멸시 한번 호출됨
    public void destroy() {
        System.out.println("소멸시 호출됨");
    }
}