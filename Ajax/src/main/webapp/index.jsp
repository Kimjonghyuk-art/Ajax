<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
<h3>우왕ㅋ굳z</h3>

<form action="Servlet.do" method="POST">
	<input type="text" name="formname" value="우왕ㅋ굳ㅋ"><br>
	<input type="number" name="formage" value="22"><br>
	<input type="submit" value="요청">

</form>
<!-- a태그는 GET 방식임 -->
<a href="Servlet.do?formname=주호쨩&formage=30">Servlet.html a태그로 파라미터 넘겨버리깃</a>

<script>
	let xhtp = new XMLHttpRequest();
	xhtp.open('GET', 'TestServlet');
	xhtp.send();
	xhtp.onload = function() {
		let result = xhtp.responseXML;
		console.log(result);
		let names = result.getElementsByTagName('name');
		let ages = result.getElementsByTagName('age');
		console.log(names);
		for(let i =0; i<names.length; i++) {
			console.log(names[i].textContent);
			console.log(ages[i].textContent);
			let p = document.createElement('p');
			p.textContent = names[i].textContent;
			document.body.append(p);
			p = document.createElement('p');
			p.textContent = ages[i].textContent;
			document.body.append(p);
		
		}
		
	}


</script>


</body>
</html>