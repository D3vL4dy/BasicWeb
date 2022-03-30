<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<% 
	// servlet에서 실행결과를 공유하여 꺼내기
	BuyerVO vo = (BuyerVO)request.getAttribute("buyer");
%>

{
	"id" : "<%= vo.getBuyer_id() %>",
	"name" : "<%= vo.getBuyer_name() %>",
	"bank" : "<%= vo.getBuyer_bank() %>",
	"bankname" : "<%= vo.getBuyer_bankname() %>",
	"bankno" : "<%= vo.getBuyer_bankno() %>",
	"lgu" : "<%= vo.getBuyer_lgu() %>",
	"mail" : "<%= vo.getBuyer_mail() %>",
	"addr1" : "<%= vo.getBuyer_add1() %>",
	"addr2" : "<%= vo.getBuyer_add2() %>",
	"zip" : "<%= vo.getBuyer_zip() %>"
}