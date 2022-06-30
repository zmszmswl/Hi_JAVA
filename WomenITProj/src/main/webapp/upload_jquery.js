/**
 *  upload_jqueryjs
	  Ajax => XMLHttpRequest, fetch, jQuery.ajax() == $.ajax()
 */

$(document).ready(function() {
	// 페이지에 리스트 출력.
	$.ajax({
		url: 'BookServ',
		method: 'get', // 디폴트 get 방식
		data: 'cmd=list',
		success: showList,
		error: function(err) {
			console.log(err);
		}
	});
	
	// 추가버튼 클릭.
	$('form[name="bookFrm"]').on('submit', memberAddCallBack);
	
});
function memberAddCallBack(e) {
	e.preventDefault();
	console.log(e.target);
	let fData = new FormData(e.target); // input 값 =>
	for(let d of fData.entries()) { // 키와 속성 모두 
		console.log(d);
	}
	
	// ajax => 데이터 등록, 화면에도 추가.
	$.ajax({
		url :'BookServ',
		method : 'post',
		data : fData,
		contentType : false,
		processData : false,
		dataType : 'json', // 결과값을 json 형식으로 변경. console.log(JSON.Parse(result));
		success : function(result){
			console.log(result);
			let tbody = $('#show > table > tbody');
			tbody.append(makeTr(result));
		},
		error : function(err){
			console.log(err)
		}
	});
}

function showList(result) { // 조회
	let tbody = $('#show > table > tbody');
	$(result).each(function(idx, elem) { // 배열처럼 넘어와서 each 메소드로 루핑돔 // console.log(elem);
		tbody.append(makeTr(elem));
	});
}

function makeTr(elem) {
	let btn = $('<button />').text('삭제'); // 삭제버튼
	btn.on('click', removeCallBack); // 버튼 이벤트 등록. CallBack)함수는 나중에 이벤트가 발생할때만 실행.
	// null, '', 0, undefined => false;
	return $('<tr />')
					  .attr('id', elem.membNo)
					  .append($('<td />').text(elem.bookNo),
							 $('<td />').text(elem.bookName),
							 $('<td />').text(elem.writer),
							 $('<td />').text(elem.publisher),
							 $('<td />').text(elem.money))
					
	
}

function removeCallBack(e){ // 이벤트가 발생하는 콜백함수에는 e가 반드시 매개값으로 넘어와야함 event로 접근해야함.
	// 비동기호출 fetch와 비슷한 방법 : jquery.ajax
		let membNo = $(e.target).parent().parent().attr('id');
		$.ajax({
			url: 'member',
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
			});  // 버튼 이벤트 등록.
		 
}

