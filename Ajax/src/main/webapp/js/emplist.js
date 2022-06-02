	


	let myform = document.forms.frm;

  	//수정 이벤트
  	document.getElementById('modify').addEventListener('click', function(e){
  		myform.cmd.value = 'update';	
			ajaxPost(modifyCallBack);
  	});
  
	function modifyCallBack(e) {
		//console.log(xhtp.responseText);
			let data = JSON.parse(xhtp.responseText);
			let oldTr = document.getElementById('key_'+ data.employeeId);
			let newTr = makeTr(data);
			oldTr.parentNode.replaceChild(newTr, oldTr); //기존에 있던 tr요소를 새로운 tr요소로 교체함
			
	};
  	//서브밋: 저장 이벤트
  	myform.onsubmit = function(e) {
  		e.preventDefault();
  		ajaxPost(addCallBack);
  	};
 
	function addCallBack(e) {
		let data = JSON.parse(xhtp.responseText);
		console.log(xhtp.responseText);
		document.getElementById('list').append(makeTr(data));
	};

  	function ajaxPost(callBackFnc) {
  	/*	let empId = myform.empId.value;
  		let fname = myform.fname.value;
  		let lname = myform.lname.value;
  		let email = myform.email.value;
  		let job = myform.job.value;
  		let hdate = myform.hdate.value;
  		let cmd = myform.cmd.value; */
  		
  		let formData = new FormData(myform);
  		let params = []; // [[],[],[]]
  		
  		for(let data of formData.entries()) {
		console.log(data[0], data[1]);// [[key=value], []]
		params.push(`${data[0]}=${data[1]}`);
		};
  		let param = params.join('&'); // k = v&k=1=v1&v2=v2*/
  		
  		/*let param = `cmd=${cmd}&fname=${fname}&lname=${lname}&email=${email}&job=${job}&hdate=${hdate}&empId=${empId}`;*/
  		console.log(param);
  			
  		let xhtp = new XMLHttpRequest();
  		xhtp.open('POST', '../ajax.do');
  		xhtp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded'); //?key=val&key1=val1
  		xhtp.send(param);
  		xhtp.onload = callBackFnc;
  	};
  	
 
  	//리스트 출력 
  	let xhtp = new XMLHttpRequest();
  	xhtp.open('Get', '../ajax.do?emp=json');
  	xhtp.send();
  	
  	xhtp.onload = function () {
  		console.log(xhtp.responseText);
  		let data = JSON.parse(xhtp.responseText);
  		let list = document.getElementById('list');
  		for(let i = 0; i<data.length; i++) {
  			list.append(makeTr(data[i]));
  		};
  	};
  	
  	//데이터 한건 입력
  	let fields = ['employeeId','firstName','lastName','email','hireDate','jobId'];
  	
  	function makeTr(emp) {
  		let tr = document.createElement('tr');
  		tr.setAttribute('id', 'key_'+emp.employeeId);
  		tr.addEventListener('click', trClick); // callback 함수 
  		
  		let list = document.getElementById('list');
  		list.append(tr);
  		fields.forEach(element => {
  			let td = document.createElement('td');
  			//console.log(emp[element]);
  			td.innerText = emp[element];
  			tr.append(td);
  		});
  		return tr;	
  	};

  	function trClick() {
  		//tr 클릭 > This.tr
  		console.log(this.children[4].innerText.substring(0,10));
  		
  		myform.empId.value = this.children[0].innerText;
  		myform.fname.value = this.children[1].innerText;
  		myform.lname.value = this.children[2].innerText;
  		myform.email.value = this.children[3].innerText;
  		myform.hdate.value = this.children[4].innerText.substring(0,10);
  		myform.job.value = this.children[5].innerText;
  		
  	};
  	
  	
  	