<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// servlet에서 공유한 데이터 꺼내기
	List<ProdVO> list = (List<ProdVO>)request.getAttribute("lguList");
%>   

[
 <% 
 	for(int i=0; i<list.size(); i++){
 		ProdVO vo = list.get(i);
 		if(i > 0) out.print(",");
 %>
		{
			"id" : "<%= vo.getProd_id() %>",
			"name" : "<%= vo.getProd_name() %>"
		}
 <%
 	}
 %>
]    