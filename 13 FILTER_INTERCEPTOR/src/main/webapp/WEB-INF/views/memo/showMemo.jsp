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
		<a href="javascript:void(0)" class="memo">
			<span>${dto.id }</span>&nbsp;&nbsp;<span>${dto.text }</span>
		</a>
		&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="cancel" data-id="${dto.id }"> X </a><br />

	</c:forEach>
</div>
<hr />

<h1>POST MEMO</h1>
<div class="postMemo">
	<form onsubmit="return false" name="post_form">
		<textarea name="postArea"></textarea>
		<button class="post_btn">POST</button>
	</form>
</div>
<hr />
<h1>UPDATE MEMO</h1>
<div class="updateMemo">
	<form onsubmit="return false" name="update_form">
		<input name="updateId" disabled/><br />
		<textarea name="updateArea"></textarea>
		<button class="update_btn">UPDATE</button>
	</form>
</div>

<!-- axios cdn -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	//Post MEMO
	const post_btn_el = document.querySelector(".post_btn");
	post_btn_el.addEventListener('click',function(){
		console.log("post_btn_el clicked..");
		const postArea_el = document.post_form.postArea;
		console.log("area's value",postArea_el.value)
		//요청 헤더 타입 지정
		const header = { headers : {"Content-Type" : "application/json"}}
		//파라미터
		const params = {text:postArea_el.value}
		axios.post("/memo/add",params,header)
		/* 성공했을 때 */
		.then(response=>{
			alert("Memo add Success!", response);
			location.reload();
		})
		/* 실패했을 때 */
		.catch(error=>{
			alert("Memo add Fail.", error);
		});
	})

	//UPDATE MEMO http://localhost:8080/app/memo/put2

	//01 메모 클릭 시 update form으로 전달
	const memo_els = document.querySelectorAll('.memo');
	memo_els.forEach((memo_el)=>{
		memo_el.addEventListener('click',function(){
			console.log('memo clicked..', memo_el.children[1].innerHTML)/* children[1]이 {dto.text}임! {dto.id}는 [0] */
			const form=document.update_form;
			form.updateId.value=memo_el.children[0].innerHTML;
			form.updateArea.value=memo_el.children[1].innerHTML;
		})
	})

	//02 업데이트 요청하기
	const update_btn_el = document.querySelector(".update_btn");
	update_btn_el.addEventListener('click',function(){
		console.log("update_btn_el clicked..");
		const updateArea_el = document.update_form.updateArea;
		const updateId_el = document.update_form.updateId;
		console.log("area's value",updateArea_el.value)
		//요청 헤더 타입 지정
		const header = { headers : {"Content-Type" : "application/json"}}
		//파라미터
		const params = {id:updateId_el.value ,text:updateArea_el.value}
		axios.put("/memo/put2",params,header)
		/* 성공했을 때 */
		.then(response=>{
			alert("Memo update Success!", response);
			location.reload();
		})
		/* 실패했을 때 */
		.catch(error=>{
			alert("Memo update Fail.", error);
		});
	})

	//03 DELETE(http://localhost:8080/app/memo/remove/{id})
	//1) .cancel Els 가져오기
	const cancel_els = document.querySelectorAll('.cancel');
	cancel_els.forEach((cancel_el)=>{
		cancel_el.addEventListener('click',function(){
			const id = cancel_el.getAttribute('data-id');
			console.log("remove id : "+id);

			axios.delete("/memo/remove/"+id)
			.then(response=>{
				location.reload();
			})
			.catch(error=>{console.log(error);});
		})
	})

</script>


</body>
</html>