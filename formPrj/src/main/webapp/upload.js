/**
 *  upload.js
 */

document.addEventListener('DOMContentLoaded', function() {
	let tbl = document.querySelector('#show table');
	let capt = document.createElement('caption');
	capt.innerHTML = '회원리스트';
	tbl.append(capt);
	// 리스트 출력.
	let ajax = new XMLHttpRequest();
	ajax.open('get', 'member?cmd=list');
	ajax.send();
	ajax.onload = function() {
		let data = JSON.parse(this.responseText)

		let tbody = document.querySelector('#show tbody');
		data.forEach(member => {
			tbody.append(makeTr(member));
		})
	}

	// form 기본 기능 => ajax 처리. (XMLHttpRequest, fetch)
	document.forms.memberFrm.addEventListener("submit", function(e) {
		e.preventDefault();

		let formData = new FormData(e.target); // e.target = form FormData 오브젝트 활용
		for (let ent of formData.entries()) {
			console.log(ent);
		};

		// get: url, post : 추가정보지정.	
		fetch('memberUpload', {
			method: 'post',
			headers : {
				'Content-type' : 'application/x-www-form-urlencoded'
			},
			body :  `cmd=delete&delId=${delId}`
		})
			.then(function(result) { // then 정상처리 후 2번째 then 실행
				return result.json();
			})
			.then(function(result) { 
				console.log(result);
			})
			.catch(function(err) {
				console.error(err);
			})
	})

});

let fields = ['membNo', 'membName', 'membPhone', 'membAddr', 'membBirth', 'membImage'];

// 회원정보 => tr 생성
function makeTr(member) {
	let tr = document.createElement('tr');
	tr.setAttribute('id', member.membNo); // tr의 attribute 중에 id를 활용.
	// tr.addEventListener('click', showDetail );
	fields.forEach(field => {  // 배열에 있는 갯수만큼 반복하겠습니다
		let td = document.createElement('td');
		// null, 0, undefined, '' => false 이외 true
		td.innerHTML = member[field] ?
			(field == 'membImage' ? '<img width = "60px" src = "images/' + member[field] + '">' : member[field]) : '';  // 공란이 아니면 필드에 이미지 있나 물어보고 없으면 노이미지, 아니면 '', 그 이외의것은 '' 
		// td.innerHTML = member[field] ?  member[field] : '';
		// 참이면 앞에값 아니면 뒤의값을 실행하겠다는 삼항연산자. 없으면 이미지와 그 이외의 항목들과 구분하고싶음 없는 값 중에서 맴버이미지 속성이면, 이미지 항목이면 'no img' 나머지 항목이면 ''  // 삼항연산자 뒤가 없으면 undifined 값이나옴.
		tr.append(td);
	})

	// 삭제버튼 
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.addEventListener('click', rowDelete, false); // 이벤트 전파방식으로 3번째 값을 줄 수 있는데 하위요소에서 상위요소로 가는 default false 버블링
	let td = document.createElement('td');
	td.append(btn);
	tr.append(td);

	// 체크박스 input type ='checkbox'
	let checkbox = document.createElement('input');
	checkbox.setAttribute('type', 'checkbox');
	// checkbox.addEventListner('checked', false);
	td = document.createElement('td');
	td.append(checkbox);
	tr.append(td);

	return tr;
}

	function rowDelete() {
		let delId = this.parentElement.parentElement.getAttribute('id');
		let formData = new FormData();
		formData.append("cmd", "delete");
		formData.append("delId", delId);
		// urlencoded : id=32&name=hong
		fetch('memberUpload', {
			method : 'post',
			headers : {'Content-type' : 'application/x-www-form-urlencoded'},
			body : `cmd=delete&delId=${delId}`
		})
		
		.then(function (result){
			return result.json();
		})
		.then(function (result){
			console.log(result);
			// 화면에서 지우도록 기능 추가
		})
		.catch(function (err){
			console.error(err);
		})
		
	}
