/*
 * 함수 안에서 var, let으로 변수 선언 시 지역변수
 */
 
print = (out) =>{
	str = "";
	
	for (i = 0; i < fr.length; i++) {
		str += fr[i] + "<br>";
	}
	
	document.querySelector(out).innerHTML = str;
	
}


proc1 = () => {
	fr = ["사과", "복숭아", "딸기", "바나나"];

	//	document.querySelector('#result1').innerHTML = fr;
	print("#result1");
}


proc2 = () => {
	fr = new Array("사과", 12000, "파인애플", 345.6789, "망고");
	
	print("#result2");
}


proc3 = () => {
	fr = new Array();
	fr[0] = "사과";
	fr[1] = 100;
	fr[2] = 123.456;
	
	print("#result3");
}
