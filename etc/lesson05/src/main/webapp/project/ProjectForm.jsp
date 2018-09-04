<%--
  Created by IntelliJ IDEA.
  User: minho
  Date: 2018. 9. 5.
  Time: AM 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>프로젝트 등록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>프로젝트 등록</h1>
<form action="add.do" method="post">
    <ul>
        <li>
            <label for="title">
                제목 <input id="title" type="text" name="name">
            </label>
        </li>
        <li>
            <label for="content">
                내용 <textarea rows="5" cols="40" id="content" name="content"></textarea>
            </label>
        </li>
        <li>
            <label for="startDate">
            시작일 <input id="startDate" type="text" name="startDate" placeholder="예)2018-08-19">
            </label>
        </li>
        <li>
            <label for="endDate">
                종료일 <input id="endDate" type="text" name="endDate" placeholder="예)2018-08-19">
            </label>
        </li>
        <li>
            <label for="tags">
                태그 <input id="tags" type="text" name="tags">
            </label>
        </li>
    </ul>
    <input type="submit" value="추가">
    <input type="reset" value="취소">
</form>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
