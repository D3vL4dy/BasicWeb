/**
 * 
 */
 
 function proc(){
	//div id="d1"를 검색
	var vd = document.getElementById("d1"); // var을 적으면 지역변수
	
	cr = parseInt(Math.random() * 256); // var을 적지 않으면 전역변수
	cg = parseInt(Math.random() * 256); 
	cb = parseInt(Math.random() * 256); 
	
	cr = cr.toString(16);
	cg = cg.toString(16);
	cb = cb.toString(16);
	
	vd.style.background = "#" + cr + cg + cb;
	
}