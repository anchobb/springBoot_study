<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1> UPLOAD FORM </h1>
<div>
MSG : ${msg }
</div>
<form action="${pageContext.request.contextPath}/upload/reqUpload" method="POST" enctype="multipart/form-data" >
	<input type="file" name="files"	multiple		/>
	<input type="submit" value="upload" />
</form>
<hr />
<a href="${pageContext.request.contextPath}/upload/list"> Show FileList </a>


</body>
</html>
