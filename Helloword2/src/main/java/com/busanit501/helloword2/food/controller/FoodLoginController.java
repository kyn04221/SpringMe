package com.busanit501.helloword2.food.controller;

import com.busanit501.helloword2.food.dto.FoodMemberDTO;
import com.busanit501.helloword2.food.service.FoodMemberService;
import com.busanit501.helloword2.jdbc.dto.MemberDTO;
import com.busanit501.helloword2.jdbc.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Log4j2
@WebServlet(name = "FoodLoginController", urlPatterns = "/foodlogin")
public class FoodLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodLoginController doGet ");
        request.getRequestDispatcher("/WEB-INF/food/foodlogin.jsp")
                .forward(request, response);
    }

    // 로직 처리,
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodLoginController doPost ");
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

        String auto = request.getParameter("auto");
        // 상태 변수,
        boolean rememberMe = auto != null && auto.equals("on");


        // 디비에가서, 해당 유저가 있으면, 임시로 세션에 저장,
        // 예외처리도 없음.
        // 조금있다 할 예정.
        // 임의로 세션 동작 여부만 확인중.

        try {
            // 로그인 했을 경우에는 uuid 값이 없는 상태,!!!
            FoodMemberDTO fmDTO = FoodMemberService.INSTANCE.login(mid, mpw);

            // 서비스에 외주주기.
            // 랜덤한 문자열 생성. UUID, 중복 되지 않는 문자열을 랜덤하게 생성.
            if (rememberMe) {
                String uuid = UUID.randomUUID().toString();
                // 디비에 uuid 업데이트 하는 경우이고,
                FoodMemberService.INSTANCE.updateUuid(mid, uuid);
                // 현재 로그인한 memberDTO, uuid 업데이트가 안된 상태임.
                // 업데이트 해주고, 세션에 저장할 예정.
                fmDTO.setUuid(uuid);

                // 쿠키를 생성해서, 웹브라우저에게 전달.
                Cookie rememberCookie = new Cookie("rememberMe", uuid);
                rememberCookie.setMaxAge(60*60*24*7);
                rememberCookie.setPath("/");
                response.addCookie(rememberCookie);
            }

            // 세션에, 위의 로그인 정보를 저장,
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", fmDTO);
            response.sendRedirect("/food/list");
        } catch (SQLException e) {
            response.sendRedirect("/foodlogin?result=error");
        }

    }
}