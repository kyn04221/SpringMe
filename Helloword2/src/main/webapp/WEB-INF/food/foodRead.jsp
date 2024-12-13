<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오후 4:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>tno 번호로 하나 조회하는 화면, 상세보기와 같은 역할. </h1>
menu : ${dto.menu}
<div>
<%--    <a href="/todo/update?tno=${dto.tno}">수정/삭제</a>--%>
    <a href="/food/list">목록가기</a>
</div>
</body>
</html>
