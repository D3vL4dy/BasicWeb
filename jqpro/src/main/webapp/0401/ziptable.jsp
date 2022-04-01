<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="../js/jquery-3.6.0.min.js"></script>
<script>
var vsido;
var vgugun;
var vdong;

$(function() {
	// 시도 찾기
	$.ajax({
		url : '/jqpro/ZipController.do',
		type : 'post',
		data : { // 정해진 특정 데이터가 있을 경우 사용 (전체를 가져오면 적지 않는다.)
			'gb' : 1
		},
		success : function(res) {
			str = "";
			$.each(res, function(i, v) {
				str += "<option>" + v + "</option>";
			})

			$('#sido').append(str);
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status);
		},
		dataType : 'json'
	})

	$('#sido').on('change', function() {
		sido = $("option:selected", this).val();

		$.ajax({
			url : '/jqpro/ZipController.do',
			type : 'post',
			data : {
				'gb' : 2,
				'sido' : sido
			},
			success : function(res) {
				str = "";
				$.each(res, function(i, v) {
					str += "<option>" + v + "</option>";
				})

				$('#gugun').html(str);
			},
			error : function(xhr) {
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
	})

	// gugun을 change해서 dong을 검색한다.
	$('#gugun').on('change', function() {
		//gugun = $('option:sekected', this).val();
		gugun = $(this).val(); // 값이 1개만 선택되므로 this로 해도 돼

		$.ajax({
			url : '/jqpro/ZipController.do',
			type : 'post',
			data : {
					'gb' : 3,
					'sido' : sido,
					'gugun' : gugun
			},
			success : function(res) {
				str = "";
				$.each(res, function(i, v) {
					str += "<option>" + v + "</option>";
				})
				$('#dong').html(str);
			},
			error : function(xhr) {
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
	})

		$('#dong').on('change', function() {
		dong = $(this).val();

		$.ajax({
			url : '/jqpro/ZipController.do',
			type : 'post',
			data : {
					'gb' : 4,
					'sido' : sido,
					'gugun' : gugun,
					'dong' : dong
			},
			success : function(res) {
				str = "<table><br>";
				$.each(res, function(i, v) {
					str += "<tr><td>" + v.zipcode + " " + v.sido
						+ " " + v.gugun + " " + v.dong
						+ "</td>";
					})
				$('#addr').html(str);
			},
			error : function(xhr) {
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
			})

		})
	})
</script>
</head>
<body>
	<select id="sido"></select>
	<select id="gugun"></select>
	<select id="dong"></select>
	
	<div id="addr"></div>
</body>
</html>