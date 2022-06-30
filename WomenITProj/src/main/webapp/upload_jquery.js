/**
 *  upload_jqueryjs
	  Ajax => XMLHttpRequest, fetch, jQuery.ajax() == $.ajax()
 */


$(document).ready(function() {

	//  출력.
	$.ajax({
		url: 'BookServ',
		// method: 'get', // 디폴트 get 방식
		// data: 'cmd=list',
		success: showList(result),
		error: function(err) {
			console.log(err);
		}
	});

	// DB 리스트 출력해주는 tr 만듬
	function showList(result) {
		let tbody = $('#show > table > tbody');

		$(result).each(function(idx, elem) { // 배열처럼 넘어와서 each 메소드로 루핑돔 // console.log(elem);

			// 삭제버튼
			let btn = $('<button />').text('삭제'); 
			btn.on('click', removeCallBack);

			let checkbox = $('<input />').attr('type', 'checkbox');

			tbody.append$('<tr />')
				.attr('id', elem.membNo)
				.append(checkbox)
				.append($('<td />').text(elem.bookNo),
					$('<td />').text(elem.bookName),
					$('<td />').text(elem.writer),
					$('<td />').text(elem.publisher),
					$('<td />').text(elem.money),
					$('<td />').append(btn))
		});
	}
	
	// 삭제버튼 콜백함수			
	function removeCallBack(e) {
		let membNo = $(e.target).parent().parent().attr('id');

		// DB 삭제
		$.ajax({
			url: 'BookServ',
			method: 'post',
			data: 'cmd=remove&delNo=' + membNo,
			success: function(result) { // 성공시
				if (result.retCode == 'Success') {
					$('#' + membNo).remove();
				} else {
					alert('처리중 에러');
				}
			},
			error: function(err) {  // 에러발생시 
				console.log(err);
			}
		});

	}

	// 입력 저장버튼
	$('form[name="bookFrm"]').on('submit', memberAddCallBack);
	
	// 저장버튼 눌렀을 때 이벤트
    function memberAddCallBack(e) {
		e.preventDefault(); // 이벤트 차단
		// console.log(this);
		console.log(document.getElementById('bookNo').value)

		let bookNo = document.getElementById('bookNo').value;
		let bookName = document.getElementById('bookName').value;
		let writer = document.getElementById('writer').value;
		let publisher = document.getElementById('publisher').value;
		let money = document.getElementById('money').value;

	$.ajax({
		url: 'BookServ',
		method: 'post',
		data: `bookNo=${bookNo}&bookName=${bookName}&writer=${writer}&publisher=${publisher}&money=${money}&cmd=add`,
		contentType: false,
		processData: false,
		dataType: 'json', // 결과값을 json 형식으로 변경. console.log(JSON.Parse(result));
		success: function(result) {
					showList(result);
					// 초기화
					 document.getElementById('bookNo').value='';
		             document.getElementById('bookName').value='';
		             document.getElementById('writer').value='';
		             document.getElementById('publisher').value='';
		             document.getElementById('money').value='';
            
		},
		error: function(err) {
			console.log(err)
		}
	})
}

}



