<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 20.
  Time: 오후 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--${}표기법으 ㄴ서버에서 넘어온 데이터를 조회할때 사용하는 EL 표기법}--%>
<%--다른 프론트 엔진인 타임리프를 사용할 경우 형식 많이 사용--%>
<h1>아이디:${param.username}</h1>
<h1>비밀번호:${param.password}</h1>
</body>
</html>
