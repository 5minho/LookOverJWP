<%@ page import="spms.vo.Project" %><%--
  Created by IntelliJ IDEA.
  User: minho
  Date: 2018. 9. 7.
  Time: AM 12:16
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
<%!String[] stateMsg = new String[] {"준비", "진행", "완료", "취소"};%>
<%Project project = (Project) request.getAttribute("project");%>
<jsp:include page="/Header.jsp"/>
<h1>프로젝트 등록</h1>
<form action="update.do" method="post">
    <ul>
        <li>
            <label for="no">
                번호 <input id="no" type="text" name="no" value="${project.no}" readonly>
            </label>
        </li>
        <li>
            <label for="title">
                제목 <input id="title" type="text" name="name" value="${project.name}">
            </label>
        </li>
        <li>
            <label for="content">
                내용 <textarea rows="5" cols="40" id="content" name="content">${project.content}</textarea>
            </label>
        </li>
        <li>
            <label for="startDate">
                시작일 <input id="startDate" type="text" name="startDate" placeholder="예)2018-08-19" value="${project.startDate}">
            </label>
        </li>
        <li>
            <label for="endDate">
                종료일 <input id="endDate" type="text" name="endDate" placeholder="예)2018-08-19" value="${project.endDate}">
            </label>
        </li>
        <li>
            <label for="state">
                상태
            </label>
            <select id="state" name="state">
                <option value="0" ${project.state==0 ? "selected" : ""}>준비</option>
                <option value="1" ${project.state==1 ? "selected" : ""}>진행</option>
                <option value="2" ${project.state==2 ? "selected" : ""}>완료</option>
                <option value="3" ${project.state==3 ? "selected" : ""}>취소</option>
            </select>
        </li>
        <li>
            <label for="tags">
                태그 <input id="tags" type="text" name="tags" value="${project.tags}">
            </label>
        </li>
    </ul>
    <input type="submit" value="저장">
    <input type="button" value="삭제" onclick="location.href='delete.do?no=${project.no}';">
    <input type="reset" value="취소" onclick="location.href='list.do'">
</form>
<jsp:include page="/Tail.jsp"/>
</body>
</html>