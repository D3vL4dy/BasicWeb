/**
 * 
 */
 
var boardWrite = function(){
	data : $('#ff').serializeJSON()
} 
var boardUpdate = function(){
	data : board // content, mail, subject, num
} 
 
var readHit = function(target) {
	$.ajax({
		url: '/board/HitUpdate.do',
		type: 'get',
		data: { "num": actionIdx }, // cont, renum
		success: function(res) {
			// db 성공 하면 화면 수정
			if (res.sw == "성공") {
				hit = $(target).parents('.card').find('.bhit');
				
				vhit = parseInt($(hit).text()) + 1;
				
				$(hit).text(vhit);
			}
		},
		error: function(xhr) {
			alert("상태 : " + xhr.status);
		},
		dataType: 'json'
	})
}
var replyUpdate = function() {
	$.ajax({
		url: '/board/ReplyUpdate.do',
		type: 'post',
		data: reply, // cont, renum
		success: function(res) {
			alert(res.sw);
			if (res.sw == "성공") {
				// 성공 했으면 화면에서 삭제한다
				vp3.html(modishow);
			}
		},
		error: function(xhr) {
			alert("상태 : " + xhr.status);
		},
		dataType: 'json'
	})
}

var replyDelete = function(target) {
	$.ajax({
		url: '/board/ReplyDelete.do',
		type: 'get',
		data: {
			"renum": actionIdx
		},
		success: function(res) {
			alert(res.sw);

			// 화면에서 지우기
			$(target).parents('.rcode').remove();
		},
		error: function(xhr) {
			alert("상태 : " + xhr.status);
		},
		dataType: 'json'
	})
}

var replyList = function(target) {
	// target변수는 등록버튼 또는 제목의 a태그

	$.ajax({
		url: '/board/ReplyList.do',
		type: 'get',
		data: {
			"bonum": actionIdx
		},
		success: function(res) {
			rcode = "";

			$.each(res, function(i, v) {
				rcode += '      <div class="rcode">';
				rcode += '         <p class="p1">';
				rcode += '            작성자 : ' + v.name + '&nbsp;&nbsp;&nbsp;';
				rcode += '            날짜 : ' + v.redate + '&nbsp;&nbsp;&nbsp;';
				rcode += '         </p>';
				rcode += '         <hr>';
				rcode += '         <p class="p2">';
				rcode += '            <input idx="' + v.renum + '" class="action" name="r_modify" type="button" value="댓글수정">';
				rcode += '            <input idx="' + v.renum + '" class="action" name="r_delete" type="button" value="댓글삭제">';
				rcode += '         </p>';
				rcode += '         <p class="p3">';
				rcode += v.cont.replace(/\r/g, "").replace(/\n/g, "<br>");
				rcode += '         </p>';
				rcode += '      </div>';

				cardBody = $(target).parents('.card').find('.card-body');

				cardBody.find('.rcode').remove().append(rcode);
				cardBody.append(rcode);
			})

		},
		error: function(xhr) {

		},
		dataType: 'json'
	})
}

var replyInsert = function(target) {
	$.ajax({
		url: '/board/ReplyInsert.do',
		type: 'post',
		data: reply, // reply객체 - bonum, name, cont
		success: function(res) {
			alert(res.sw);

			// 댓글 출력
			replyList(target);
		},
		error: function(xhr) {
			alert("상태 : " + xhr.status);
		},
		dataType: 'json'
	})
}

var boardDelete = function() {
	typevalue = $('#stype option:selected').val().trim();
	wordvalue = $('#sword').val().trim();

	$.ajax({
		url: '/board/BoardDelete.do',
		type: 'post',
		data: {
			"page": currentPage,
			"num": actionIdx,
			"type": typevalue,
			"word": wordvalue
		},
		success: function(res) {
			if (res.sw == "ok") {

				if (res.totalp < currentPage) {
					currentPage = res.totalp;
				}
				listServer();

			} else {
				alert("삭제 실패");
			}
		},
		error: function(xhr) {
			alert("상태 : " + xhr.status); // 200 : json형태 틀림, 404 : url/jsp, 500 : 코딩 잘못 중 하나 나옴
		},
		dataType: 'json'
	})
}

var listServer = function() {
	$.ajax({
		url: '/board/List.do',
		type: 'post',
		data:
		{
			'page': currentPage,
			'stype': typevalue,   // writer, content
			'sword': wordvalue
		},

		success: function(res) {

			code = '<div id="accordion">';

			$.each(res.datas, function(i, v) {

				code += '<div class="card">';
				code += '   <div class="card-header action" idx="' + v.num + '" name="title">';
				code += '      <a class="card-link" data-toggle="collapse" href="#collapse' + v.num + '">';
				code += v.subject + '</a>';
				code += '   </div>';
				code += '   <div id="collapse' + v.num + '" class="collapse" data-parent="#accordion">';
				code += '      <div class="card-body">';
				code += '         <p class="p1">';
				code += '            작성자 : <span class="bwr">' + v.writer + '</span>&nbsp;&nbsp;&nbsp;';
				code += '            이메일 : <span class="bma">' + v.mail + '</span>&nbsp;&nbsp;&nbsp;';
				code += '            날짜 : <span class="bda">' + v.wdate + '</span>&nbsp;&nbsp;&nbsp;';
				code += '            조회수 : <span class="bhit">' + v.hit + '</span>&nbsp;&nbsp;&nbsp;';
				code += '         </p>';
				code += '         <hr>';
				code += '         <p class="p2">';
				code += '            <input idx="' + v.num + '" class="action" name="modify" type="button" data-toggle="modal" data-target="#modiModal" value="수정">';
				code += '            <input idx="' + v.num + '" class="action" name="delete" type="button" value="삭제">';
				code += '         </p>';
				code += '         <p class="p3">';
				code += v.content;
				code += '         </p>';
				code += '         <p class="p4">';
				code += '            <textarea rows="" cols="80"></textarea>';
				code += '            <input idx="' + v.num + '" type="button" class="action" name="reply" value="등록">';
				code += '         </p>';
				code += '      </div>';
				code += '   </div>';
				code += '</div>';

			})   // 반복분 끝

			code += '</div>';

			$('.container').html(code);

			// 이전버튼 출력
			pcode = "";

			if (res.startp > 1) {
				pcode = '<ul class="pagination">';
				pcode += '<li class="page-item"><a class="page-link prev" href="#">Previous</a>';
				pcode += '</li></ul>';
			}

			// 페이지목록 출력
			pcode += '<ul class="pagination pager">';

			for (i = res.startp; i <= res.endp; i++) {

				if (currentPage == i) {
					pcode += '<li class="page-item active"><a class="page-link pnum" href="#">' + i + '</a></li>';
				} else {
					pcode += '<li class="page-item"><a class="page-link pnum" href="#">' + i + '</a></li>';
				}
			}
			pcode += '</ul>';

			// 다음버튼 출력
			if (res.endp < res.totalp) {
				pcode += '<ul class="pagination">';
				pcode += '<li class="page-item"><a class="page-link next" href="#">Next</a>';
				pcode += '</li></ul>';
			}

			$('#pagelist').html(pcode);

		},   // success 끝
		error: function(xhr) {
			alert("상태 : " + xhr.status)
		},
		dataType: 'json'

	})   // ajax 종료
}