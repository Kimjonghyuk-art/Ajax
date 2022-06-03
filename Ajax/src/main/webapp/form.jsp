<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form.jsp</title>
</head>
<body>
  <form name="myform" action="FormServ" method="post">
	<input type="text" name="user"><br>
	<input type="text" name="pw"><br>
	<input type="hidden" name="command" value="search"><br>
	<input type="submit" value="전송">
  </form>
 <script src="form.js"></script>
</body>
</html>