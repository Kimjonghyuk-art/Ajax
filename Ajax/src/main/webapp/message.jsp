<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>message</title>
<style>
	#show {
		width: 500px;
		height: 300px;
		border: 1px solid blue;
	}
</style>
</head>
<body>
<%
	String user = request.getParameter("user");

%>
<div id="show">
	<div class="row">작성자 > test</div>
</div>

	
	<input type="text" name="content">
	<input type="hidden" name="writer" value="<%=user%>">

<script src="message.js"></script>


</body>
</html>