<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>SHOW MEMO</h1>
	<div class="showMemo">
		<c:forEach items="${list}" var="dto">
			<span>${dto.id}</span>&nbsp;&nbsp;<span>${dto.text}</span><br/>
		</c:forEach>
	</div>
	<hr>
</body>
</html>