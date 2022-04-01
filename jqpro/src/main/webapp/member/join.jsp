<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="kr">
<head>
	<title>Bootstrap Example</title>
 	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	
	<script src="../js/jquery.serializejson.min.js"></script>
</head>

<script>
$(function(){
	// id중복체크
	$('#idchk').on('click', function() {
		event.preventDefault();
		
		// 입력한 값을 가져온다
		idvalue = $('#uid').val().trim();
		
		// 서버로 전송
		$.ajax({
			url : '<%= request.getContextPath() %>/IdCheck.do',
			type : 'get',
			data : { "uid" : idvalue },
			success : function(res){
				//alert(res.flag);
				$('#idspan').html(res.flag).css('color', 'red');
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
	})
	
	// 우편번호 검색
	$('#zipsearch').on('click', function(){
		event.preventDefault();
		
		window.open("zipsearch.html", "우편번호", "width=500 height=400");
		
	})
	
	// 우편번호 찾기
	$('#btn1').on('click', function(){
		// 입력한 값 가져온다.
		dongvalue = $('#dong').val().trim();
		console.log(dongvalue);
		
		// 서버로 전송
		$.ajax({
			url : '/jqpro/ZipSearch.do',
			type : 'get',
			data : { "dongdong" : dongvalue },
			success : function(res){
				str ="<table>";
				str += "<tr><td>우편번호</td>";
				str += "<td>주소</td>"; // 시도구군동
				str += "<td>번지</td></tr>";
				
				$.each(res, function(i, v){
					
					var bunji = v.bunji;
					if(typeof v.bunji == "undefined")
						bunji = "";
					
					str += "<tr class='ziptr'><td>" + v.zipcode + "</td>";
					str += "<td>" + v.sido + v.gugun + v.dong + "</td>"; // 시도구군동
					str += "<td>" + bunji + "</td></tr>";
				})
				
				str += "</table>";
				
				$('#result1').html(str);
				
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
	})
	
	// 검색 결과에서 선택하여 부모창으로 값을 넘기기
	$('#result1').on('click', '.ziptr', function(){
		zipcode = $("td:eq(0)", this).text();
		addr = $('td:eq(1)', this).text();
		
		$('#zip').val(zipcode);
		$('#add1').val(addr);
		
		$('#dong').val("");
		$('#result1').empty();
		$('#myModal').modal('hide');
	})
	
	$('form').on('submit', function(){
		// form에서 입력한 모든 값을 가져온다. (서버로 전송하기 위해)
		fdata1 = $('form').serialize();
		fdata2 = $('form').serializeArray();
		fdata3 = $('form').serializeJSON(); // 여러 데이터를 한번에 가져오려고 사용
		
		console.log(fdata1);
		console.log(fdata2);
		console.log(fdata3);
		
		$.ajax({
			url : '<%= request.getContextPath()%>/Insert.do',
			data : fdata3,
			type : 'post',
			success : function(res){
				//alert(res.flag);
				$('#joinspan').html(res.flag).css('color', 'red');
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
		
		return false;
		
	})
	
})
</script>
<style>
#dong {
	margin-left: 20px;
}

.ziptr:hover {
	background: lime;
}
</style>
<body>

	<div class="container">
		<h2>회원가입</h2>
		<form class="needs-validation" novalidate>
			<div class="form-group">
				<label for="uid">아이디</label>
				<button class="btn btn-info mb-2 mr-sm-2" type="button" id="idchk">중복검사</button>
				
				<input type="text" class="form-control col-sm-3" id="uid" placeholder="Enter userid" name="mem_id" required>
				<span id="idspan"></span>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			
			<div class="form-group">
				<label for="uname">이름</label>
				<input type="text" class="form-control col-sm-3" id="uname" placeholder="Enter username" name="mem_name" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input type="password" class="form-control col-sm-3" id="pwd" placeholder="Enter password" name="mem_pass" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			
			<div class="form-group">
				<label for="bir">생년월일</label>
				<input type="date" class="form-control col-sm-3" id="bir" placeholder="Enter birth" name="mem_bir" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			
			<div class="form-group">
				<label for="hp">휴대폰 번호</label>
				<input type="tel" class="form-control col-sm-3" id="hp" placeholder="010-1234-5678" name="mem_hp" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			
			<div class="form-group">
				<label for="mail">이메일</label>
				<input type="email" class="form-control col-sm-3" id="mail" placeholder="abcd123@email.com" name="mem_mail" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			
			<div class="form-group">
				<label for="zip">우편번호</label>
				<button class="btn btn-info mb-2 mr-sm-2" id="zipsearch">번호검색</button>
				<button type="button" class="btn btn-info mb-2 mr-sm-2" data-toggle="modal" data-target="#myModal">번호검색modal</button>
				
				<input type="text" class="form-control col-sm-3" id="zip" name="mem_zip" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			
			<div class="form-group">
				<label for="add1">주소</label>
				<input type="text" class="form-control col-sm-5" id="add1" name="mem_add1" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
			
			<div class="form-group">
				<label for="add2">상세주소</label>
				<input type="text" class="form-control col-sm-5" id="add2" name="mem_add2" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
	    
	    	<button type="submit" class="btn btn-primary btn-lg">Submit</button>
	    	<span id="joinspan"></span>
		</form>
	</div>
	
	<!-- The Modal -->
	<div class="modal" id="myModal">
  		<div class="modal-dialog">
    		<div class="modal-content">

				<!-- Modal Header -->
		      	<div class="modal-header">
		        	<h4 class="modal-title">우편번호 찾기</h4>
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		      	</div>

		      	<!-- Modal body -->
		      	<div class="modal-body">
					<input type="text" id="dong">
					<input type="button" value="확인" id="btn1">
					<div id="result1"></div>
		      	</div>

		      	<!-- Modal footer -->
		      	<div class="modal-footer">
		        	<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		      	</div>
	    	</div>
	  	</div>
	</div>

	<script>
	// Disable form submissions if there are invalid fields
		(function() {
			'use strict';
			window.addEventListener('load', function() {
	    		// Get the forms we want to add validation styles to
	    		var forms = document.getElementsByClassName('needs-validation');
			    // Loop over them and prevent submission
			    var validation = Array.prototype.filter.call(forms, function(form) {
	      			form.addEventListener('submit', function(event) {
	        			if (form.checkValidity() === false) {
				          event.preventDefault();
				          event.stopPropagation();
	        			}
	        			form.classList.add('was-validated');
	      			}, false);
	    		});
	  		}, false);
		})();
	</script>

</body>
</html>
