 // 리스트 출력.
    let promise = new Promise(function (resolve, reject) {
      setTimeout(e => {
        reject('error');
      }, 2000);
    });
    // promise: fullfilled, pending, rejected.
    // promise.then().catch()
    promise.then(function (result) {
      console.log(result)
    }).catch(function (error) {
      console.log(error);
    });

    fetch('../ajax.do?emp=json')
      .then(result => result.json() // stream() => json
      ).then(json => showList(json))
      .catch(function (err) {
        console.log(err);
      });

    function showList(json) {
      let list = document.getElementById('list');
      for (let i = 0; i < json.length; i++) {
        list.append(makeTr(json[i])); // data[i]=> employeeId,firstName,
      }
    }

    // modify:변경.
    document.getElementById('modify').addEventListener('click', modifyFnc);
    function modifyFnc() {
      myform.cmd.value = 'update';
      fetchFunc(modifyCallBack);
    }
	// 기존값을 수정값으로 변경해주는 callback.
    function modifyCallBack(data) {
      let oldTr = document.getElementById('key_' + data.employeeId);
      let newTr = makeTr(data);
      oldTr.parentNode.replaceChild(newTr, oldTr);
    }

    // submit:저장.
    let myform = document.forms.frm;
    myform.addEventListener('submit', submitFnc);
    function submitFnc(e) {
      e.preventDefault();
      fetchFunc(submitCallBack);
    }
	// 신규값을 리스트에 추가해주는 callback.
    function submitCallBack(data) {
      document.getElementById('list').append(makeTr(data))
    }

    function fetchFunc(callBack) {
      let formData = new FormData(myform);
      let params = []; // [[],[],[]]
      for (let data of formData.entries()) {
        params.push(`${data[0]}=${data[1]}`);
        console.log(`${data[0]}=${data[1]}`);
      }
      let param = params.join('&'); // k=v&k1=v1&k2=v2
	console.log(param);
      fetch('../ajax.do', {
          method: 'post',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          body: param
        })
        .then(resut => resut.json())
        .then(result => callBack(result))
        .catch(err => console.log(err));
    }

    // 데이터 한건 입력.
    let fields = ['employeeId', 'firstName', 'lastName', 'email', 'hireDate', 'jobId'];

    function makeTr(emp) {
      let tr = document.createElement('tr');
      tr.setAttribute('id', 'key_' + emp.employeeId);
      tr.addEventListener('click', trClick); // callback.
      fields.forEach(field => {
        let td = document.createElement('td');
        td.innerText = emp[field];
        tr.append(td);
      });
      return tr;
    }

    function trClick() {
      // tr클릭 -> this:tr
      let date = this.children[4].innerText.substring(0, 10);
      myform.empId.value = this.children[0].innerText;
      myform.fname.value = this.children[1].innerText;
      myform.lname.value = this.children[2].innerText;
      myform.email.value = this.children[3].innerText;
      myform.hdate.value = date;
      myform.job.value = this.children[5].innerText;
    }