
document.querySelector('input[name="content"]').addEventListener('change',changeCall);

function changeCall() {
	//사용자가 입력한 값
	let content = document.querySelector('input[name="content"]').value;
	let writer = document.querySelector('input[name="writer"]').value;
	
	fetch('msg', {
		method: 'POST',
		headers:{'Content-Type':'application/x-www-form-urlencoded'},
		body: 'writer=' + writer + '&content=' + content	
	})
	.then(result => {
	console.log(result);
	e.target.value = ''; // e.target => input
	})
	.catch(err => console.log(err));
};

setInterval(e => {
	let lastMsg = -1;
	fetch('msg')
		.then(result => result.json())
		.then(resolve => {
			//실행되면 기존의 메시지를 remove해 줌
			let divs = document.querySelectorAll('.row');
			divs.forEach(elem => elem.remove());

			fitArr = resolve.filter(elem => {
				console.log(elem);
				return elem.message_id > lastMsg - 10;
			});

			//조회된 메시지를 새로 그려주기
			let show = document.getElementById('show');
			fitArr.forEach(row => {
				lastMsg = row.message_id;
				let div = createRow(row);
				show.append(div);
			});
		})
		.catch(error => console.log(error))
}, 3000);

//메세지를 받아서 (한줄) 생성
function createRow(message) {
	let div = document.createElement('div');
	div.setAttribute('class', 'row');
	console.log(message);
	let txt = document.createTextNode(message.writer + '> ' + message.content);
	div.append(txt);
	//div.innerText = `${message.writer} > ${message.content}`; 
	return div;
};
