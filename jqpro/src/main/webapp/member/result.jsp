<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// servlet에서 request에 저장한 공유 데이터
	String res = (String) request.getAttribute("keyres");
	//String input = (String) request.getAttribute("formId");
	//if(res.equals(input))

	if(res != null){
%>    
	{
		"flag" : "<%= res %>님 가입 성공"
	}	
<% 
	}else{ 
%>
	{
		"flag" : "가입 실패"
	}
<% 
	}
%>	
