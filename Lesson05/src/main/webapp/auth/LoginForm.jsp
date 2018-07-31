<%--
  Created by IntelliJ IDEA.
  User: minho
  Date: 2018. 7. 31.
  Time: PM 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h2>사용자 로그인</h2>
<form action="login" method="post">
    이메일 : <input type="text" name="email"><br>
    암호 : <input type="password" name="password"><br>
    <input type="submit" value="로그인">
</form>
</body>
</html>
