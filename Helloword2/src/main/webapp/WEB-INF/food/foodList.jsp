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
  <title>food List</title>
</head>
<body>
<h1>${loginInfo.mid} 님 환영합니다 </h1>
<%--  로그인 한 유저 표시--%>
임시 로그인한 유저 정보: ${loginInfo.mid}
임시 조회한 게시글 번호 정보:${cookie.viewTodos.value}
임시 로그인한 uuid 정보: ${loginInfo.uuid}
<form action="/foodlogout" method="post">
  <button type="submit">로그아웃 테스트</button>
</form>

<h1>음료List</h1>
<a href="/food/register">메뉴판 보기</a>
<h2>todoRead 하나 조회 더미 </h2>
<a href="/food/read?tno=5">하나 조회</a>

<h2>JSTL 연습장</h2>
<h3>반복문, forEach 이용, var=변수명, items="데이터 목록" , 더 많이 사용함</h3>
<ul>
  <c:forEach var="dto" items="${List}">
    <li>
      <span>${dto.tno}</span>
      <span><a href="/food/read?tno=${dto.tno}">${dto.menu}</a></span>
      <span>${dto.price}</span>
    </li>
  </c:forEach>
</ul>

</body>
</html>
