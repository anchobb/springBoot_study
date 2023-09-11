<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>FORM PAGE</h1>
	
	<form action="/memo/post" method="post">
		<div>${id} </div>
		<input type="number" name="id" 	placeholder="id"		/><br/>
		<div>${text} </div>
		<input name="text"	placeholder="text"		/><br/>
		<div>${writer} </div>
		<input name="writer" placeholder="writer"	/><br/>
		<div>${regdate} </div>
		<input name="regdate" placeholder="yyyy-MM-dd hh:mm:ss"	/><br/>
		<input type="submit" />
	</form>


</body>
</html>