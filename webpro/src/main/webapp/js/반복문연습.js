/**
 * 람다 함수 : '=>'를 이용하는 함수
 * proc3 = (매개변수) => {}
 */
 function proc1(){
	// 1~10 합
	
	
}

 function proc2(){
	// 1~200 짝수 합
	hap = 0;
	
	for(i=1; i<=100; i++){
		//if(i % 2 == 0) hap += i;
		if(i % 2 != 0) continue;
		hap += i;
	}
	
	document.querySelector('#result2').innerHTML = hap;
}

 /*function proc3(){}*/
 /*proc3 = function(){}*/
 proc3 = () =>{
	var hap = 0; // 숫자로 
	var str = ""; //문자로
	
	while(true){
		// 입력
		su = parseInt(prompt("수를 입력"));
		
		// 비교
		if(su == 0) break;
		
		// 더하기
		hap += su;
		str += su + " ";
	}	
	
	res = "입력한 값들 : " + str + "<br>";
	res += "더한 합 : " + hap;
	
	document.querySelector('#result3').innerHTML = res;
}

