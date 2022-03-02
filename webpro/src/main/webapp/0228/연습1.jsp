<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("name");
		String userMail = request.getParameter("mail");
		String area = request.getParameter("area");
		
		area = area.replaceAll("\r", "").replaceAll("\n", "<br>");
		
		out.print("<p>이름 : " + userName + "</p>");
		out.print("<p>이메일 : " + userMail + "</p>");
		out.print("<p>내용 : <br>" + area + "</p>");
	%>
</body>
</html>