/**
 * 
 */

/*var f = null;

window.onload = function() {
	f = document.ff // form 검색 접근

	f.addEventListener('submit', datapro);
} // window.onload 끝*/

function datapro() {
	
	f = document.ff;

	// 입력한 값을 가져온다.
	namevalue = f.name.value.trim();

	// 공백 검증
	if (namevalue.length < 1) {
		alert("이름을 입력하세요.");

		return false;
	}

	// 길이 검증
	if (namevalue.length < 2 || namevalue.length > 5) {
		alert("이름은 2~5사이입니다.");
		return false;
	}

	// 정규식
	namereg = /^[가-힣]{2,5}$/

	if (!(namereg.test(namevalue))) {
		alert("이름 형식 오류입니다.");
		return false;
	}
	//----------------------------------------------------------------
	// 아이디 검증
	idvalue = f.userid.value.trim();
	
	// 공백 검증
	if(idvalue.length < 1){
		alert("아이디를 입력하세요.");
		return false;
	}
	
	// 길이 검증
	if(idvalue.length < 4 || idvalue.length > 10){
		alert("아이디는 4~10사이입니다.");
		return false;
	}
	
	// 정규식
	idreg = /^[a-zA-Z][a-zA-Z0-9]{3,9}/;
	
	if(!(idreg.test(idvalue))){
		alert("아이디 형식 오류입니다.");
		return false;
	}
	//----------------------------------------------------------------
	// 비밀번호
	passvalue = f.pass.value.trim();
	
	// 공백
	if(passvalue.length < 1){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	
	// 길이
	if(passvalue.length < 4 || passvalue.length > 12){
		alert("비밀번호는 4~12사이입니다.");
		return false;
	}
	
	// 정규식
	passreg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{4,12}$/; 
	
	if(!(passreg.test(passvalue))){
		alert("비밀번호 형식 오류입니다.");
		return false;
	}
	//----------------------------------------------------------------
	// 이메일 검증
	emailvalue = f.email.value.trim();
	
	// 공백 검증
	if(emailvalue.length < 1){
		alert("이메일을 입력하세요.");
		return false;
	}
	
	// 정규식 -첫 글자는 영문자 1글자, 뒤는 영문자/숫자가 올 수 있다.
	// @ -
	emailreg = /^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-z]+){1,2}$/
	
	if(!(emailreg.test(emailvalue))){
		alert("이메일 형식 오류입니다.");
		return false;
	}	
	//----------------------------------------------------------------
	// 전화번호
	phvalue = f.phone.value.trim();
	
	// 공백 검증
	if(phvalue.length < 1){
		alert("전화번호를 입력하세요.");
		return false;
	}
	
	// 길이 검증 = 11자리 일치
	if (phvalue.length != 11) {
		alert("전화번호 11자리입니다.");
		return false;
	}
	
	// 정규식
	phreg = /^\d{3}\d{4}\d{4}$/;
	
	if(!(phreg.test(phvalue))){
		alert("전화번호 형식 오류입니다.");
		return false;
	}
	
	
	//alert("올바른 데이터 성공");
	return true;


} // datapro 끝


/* f.addr.value
f.birthday.value
f.email.value
f.phone.value
f.userid.value
f.pass.value */