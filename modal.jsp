<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="kr">
<head>
	<title>Modal Example</title>
 	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
	
	<script src="../js/jquery.serializejson.min.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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

})
</script>

<body>

    <div class="member_login">
            <div class="member_login_input">
                <input type="text" name="username" placeholder="아이디" >
            </div>

            <div class="member_login_input">
                <input type="password" name="password" placeholder="비밀번호">
            </div>

            <div class="find_password">
                <a href="/forgot-password">아이디 또는 비밀번호를 잊으셨나요?</a>
            </div>

            <div class="login_api">
                <a href="https://kauth.kakao.com/oauth/authorize?client_id=d860d38c992ca8bf5f07dcc3fd5122b9&redirect_uri=http://localhost:9090/member/auth/kakao/callback&response_type=code"><img src="/image/kakao_login_button.png"></a>
            </div>

    </div>
    
	<div class="form-group">
		<button class="btn btn-info mb-2 mr-sm-2" id="login">로그인</button>
		<button type="button" class="btn btn-info mb-2 mr-sm-2" data-toggle="modal" data-target="#myModal">회원가입</button>
									
		<div class="valid-feedback">Valid.</div>
		<div class="invalid-feedback">Please fill out this field.</div>
	</div>

	
	<!-- The Modal -->
	<div class="modal" id="myModal">
  		<div class="modal-dialog">
    		<div class="modal-content">

				<!-- Modal Header -->
		      	<div class="modal-header">
		        	<h4 class="modal-title">회원가입</h4>
		        	<button type="button" class="close" data-dismiss="modal">&times;</button>
		      	</div>

		      	<!-- Modal body -->
		      	<div class="modal-body">
		      			<div class="container">
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
									<label for="hp">휴대폰 번호</label>
									<input type="tel" class="form-control col-sm-3" id="hp" placeholder="010-1234-5678" name="mem_hp" required>
									<div class="valid-feedback">Valid.</div>
									<div class="invalid-feedback">Please fill out this field.</div>
								</div>
								
								<button type="submit" class="btn btn-primary btn-lg">가입</button>
							</form>
						</div>
		      	</div>

		      	<!-- Modal footer -->
		      	<div class="modal-footer">
		        	<button type="button" class="btn btn-danger" data-dismiss="modal">close</button>
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
