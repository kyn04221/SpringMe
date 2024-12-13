<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오전 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>todoList</title>
</head>
<body>
<h1>${loginInfo} 님 환영합니다 </h1>
<%--  로그인 한 유저 표시--%>
임시 로그인한 유저 정보: ${loginInfo}
<form action="/logout" method="post">
    <button type="submit">로그아웃 테스트</button>
</form>
<h1>todoList 목록화면. </h1>
<a href="/todo/register2">글쓰기 폼이동</a>

<h2>todoRead 하나 조회 더미 </h2>
<a href="/todo/read2?tno=5">하나 조회</a>
?


<h2>JSTL 연습장</h2>
<h3>반복문, forEach 이용, var=변수명, items="데이터 목록" , 더 많이 사용함</h3>
<ul>
    <c:forEach var="dto" items="${List}">
        <li>
            <span>${dto.tno}</span>
            <span><a href="/todo/read2?tno=${dto.tno}">${dto.title}</a></span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "완료": "미완료"}</span>
        </li>
    </c:forEach>
</ul>

<%--</ul>--%>
<%--<H3>begin/end foreach"</H3>--%>
<%--<ul>--%>
<%--    <c:forEach var="dto" items="${list}" begin="1" end="5">--%>
<%--        <li>${dto}</li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>


<%--<H3>if</H3>--%>
<%--<ui>--%>
<%--    <c:forEach var="dto" items="${list}">--%>
<%--    <c:if test="${dto.tno % 2 == 0}">--%>
<%--        <li> 짝수 , ${dto}</li>--%>
<%--    </c:if>--%>
<%--    <c:if test="${dto.tno % 2 != 0}">--%>
<%--        <li>홀수, ${dto}</li>--%>
<%--    </c:if>--%>
<%--    </c:forEach>--%>
<%--</ui>--%>

<%--<H3>choose</H3>--%>
<%--<ui>--%>
<%--<c:forEach var="dto" items="${list}">--%>
<%--    <c:choose>--%>
<%--        <c:when test="${dto.tno % 2 == 0}">--%>
<%--            <li> 짝수 , ${dto}</li>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <li>홀수, ${dto}</li>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>
<%--</c:forEach>--%>
<%--</ui>--%>


<%--<h2>JSTL 변수 설정하고 사용하는 방법</h2>--%>
<%--<c:set var="todoDTO" value="${list[0]}"/>--%>

<%--<c:forEach var="dto" items="${list}">--%>
<%--    <c:if test="${dto.tno == todoDTO.tno}">--%>
<%--        ${dto}--%>
<%--    </c:if>--%>
<%--</c:forEach>--%>


</body>
</html>
