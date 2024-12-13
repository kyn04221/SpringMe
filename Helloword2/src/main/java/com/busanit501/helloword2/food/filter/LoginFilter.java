package com.busanit501.helloword2.food.filter;

import com.busanit501.helloword2.food.dto.FoodMemberDTO;
import com.busanit501.helloword2.food.service.FoodMemberService;
import com.busanit501.helloword2.jdbc.dto.MemberDTO;
import com.busanit501.helloword2.jdbc.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//톰캣 서버에서,
// 한글로 입력된 내용을, UTF8로 변환해서 보내기.
// 필터, 서버에 작업을 실행하기전에, 먼저 검사한다.
// 유효성 체크.
@WebFilter(urlPatterns = {"/food/*"})
@Log4j2
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter ,/food/* 하위로 들어오는 모든 url 에 대해서 로그인 체크함");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        HttpSession session = request.getSession();

        if(session.isNew()) {
            log.info("최초로 서버에 요청을함.");
            response.sendRedirect("/foodlogin");
            return;
        }

        if(session.getAttribute("loginInfo") != null) {
            log.info("2번째 이후로 서버에 요청을했고, 하지만, 로그인 정보는 없는 경우.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Cookie findCookie = findCookie(request.getCookies(), "rememberMe");

        if(findCookie == null) {
            response.sendRedirect("/foodlogin");
            return;
        }

        String getUuid = findCookie.getValue();

        try {

            FoodMemberDTO fmDTO = FoodMemberService.INSTANCE.getMemberWithUuidService(getUuid);
            log.info("memberDTO : ", fmDTO);

            if(fmDTO == null) {
                throw new Exception("쿠키값이 유효하지 않습니다.");
            }
            // 회원정보를 , 세션에 추가하기.
            session.setAttribute("loginInfo", fmDTO);
            //계속 필터 동작을 진행하겠다.
            // 만약 필터가 더이상 없다면, 원래대로 서버에서 나타내는 화면으로 이동함.
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login");

        }


        //임시로, 최초도 아니고, 로그인 처리가 되었다면, 그러면,
        // 정상적으로 접근하는 페이지로 이동 시켜 줄게.
        if(session.getAttribute("loginInfo") != null) {
            // 앞에서 임시로 테스트 할 때, mid+mpw 붙여서 확인.
//            String result  = (String) session.getAttribute("loginInfo");
            FoodMemberDTO fmDTO  = (FoodMemberDTO) session.getAttribute("loginInfo");
            log.info("session.getAttribute(\"loginInfo\") memberDTO : " + fmDTO);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        // 쿠키가 있는 경우
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                // cookie.getName(): 전체 쿠키 목록 요소의 이름
                // name : 찾고자하는 쿠키 이름.
                if (cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                } //if
            } // for
        } // if
        return findCookie;
    } // method
}