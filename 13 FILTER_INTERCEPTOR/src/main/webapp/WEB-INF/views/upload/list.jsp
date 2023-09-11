<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1> LIST FILE </h1>
<div>UPLOAD DIR : ${rootDir}</div>
<div>
	<c:forEach items='${rootDir.listFiles()}' var='subdir'>
		<hr />
		FOLDER : ${subdir.getPath()}
		<hr />
		<c:forEach items='${subdir.listFiles() }' var='file'>
		
			<a class="item" href="javascript:void(0)" data-dir="${subdir.getPath()}" data-file="${file.getName()}">
				${file}
			</a><br />
		</c:forEach>
	</c:forEach>
</div>

<script>
	const projectPath = '${pageContext.request.contextPath}';
	
	const item_els = document.querySelectorAll('.item');
	item_els.forEach((item)=>{
		item.addEventListener('click',function(){
			const filepath = encodeURIComponent(item.getAttribute('data-dir')+"\\"+item.getAttribute('data-file'))
			alert(filepath);
			location.href=projectPath+"/download?path="+filepath;
		})
	})
</script>

</body>
</html>
