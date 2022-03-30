<%@page import="kr.or.ddit.prod.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// servlet에서 공유한 데이터 꺼내기
	ProdVO vo = (ProdVO)request.getAttribute("prodId");
%>   
		{
			"id" : "<%= vo.getProd_id() %>",
			"name" : "<%= vo.getProd_name() %>",
			"lgu" : "<%=vo.getProd_lgu() %>",
			"buyer" : "<%= vo.getProd_buyer() %>",
			"cost" : "<%= vo.getProd_cost() %>",
			"price" : "<%= vo.getProd_price() %>",
			"sale" : "<%= vo.getProd_sale() %>",
			"size" : "<%= vo.getProd_size() %>",
			"color" : "<%= vo.getProd_color() %>"
		}
