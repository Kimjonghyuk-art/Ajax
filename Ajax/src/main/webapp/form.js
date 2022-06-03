let myform = document.forms.myform;

myform.onsubmit = function() {
	console.log(myform);
	myform.cmd.value = "insert";
	myform.user.value = "JS유저";
	myform.pw.value = '1234';
	
	
};


