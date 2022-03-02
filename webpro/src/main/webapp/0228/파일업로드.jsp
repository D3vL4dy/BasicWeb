<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1{
		background : #a4f198;
		color : #f8f9f0;
	}
	p{
		font-size : 2.0em;
	}
</style>
</head>
<body>
	<h1>JSP : Java Server Page</h1>
	
	<%
		request.setCharacterEncoding("UTF-8"); // text일 경우 
		String userName = request.getParameter("name"); // (html)name에 있는 값을 가져옴
		String userId = request.getParameter("id");
		String fileName = request.getParameter("file");
		String no = request.getParameter("no");
		
		out.print("<p>이름 : " + userName + "</p>");
		out.print("<p>아이디 : " + userId + "</p>");
		out.print("<p>첨부파일 : " + fileName + "</p>");
		out.print("<p>번호 : " + no + "</p>");
	%>
</body>
</html>