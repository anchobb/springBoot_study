<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>JOIN</title>
</head>
<body>
<h1>JOIN </h1>

<form action="${pageContext.request.contextPath}/member/join" method="post">
	<input type="text" 	name="userid" 		placeholder="userid"				 /><br/>
	<input type="password" name="password" 	placeholder="password"				/><br/>
	<input type="text" 	name="name" 			placeholder="name"					/><br/>
	<input type="text" 	name="phone" 		placeholder="phone"					/><br/>
	<input type="text" 	name="address" 		placeholder="address"				/><br/>
	<input type="text" name="birthday" 		placeholder="birthday"				/><br/>
	<input type="checkbox" name="hobbys" 	value="reading"						/> 독서
	<input type="checkbox" name="hobbys" 	value="moving" 						/> 영화감상
	<input type="checkbox" name="hobbys" 	value="swimming" 								/> 수영 <br/>
	<input type="text" name="hobbys2" />
	<input type="submit" />
</form>

</body>
</html>
