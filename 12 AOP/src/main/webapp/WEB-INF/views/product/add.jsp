<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
	 
	 <!-- BS -->
	  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	  <!-- axios -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	 <style>
        *{}
        body{}
        .wrapper{ width : 1500px; height : auto; border : 1px solid gray; padding : 20px;margin : 100px auto;}
        .wrapper>header{}
        .wrapper>header>.top--header{}
        .wrapper>header>nav{}


        .wrapper>main{
            display : flex;
            justify-content : left;
            align-items : center;
            margin : 10px;
        }
        .wrapper>main>*{
        	margin:20px;
        }
        .wrapper>main>section{

        }

         .wrapper>main>section:nth-child(1){

         }
         form input{
          margin :5px;
         }
        .wrapper>footer{}
    </style>

  <style>
    .product_img{
      width : 300px;
      height: 300px;

    }
	.upload-box {
      width: 100%;
      height : 300px;
      margin: 10px auto;
      border: 5px dashed lightgray;
      padding: 10px;
      position: relative;

    }

    .plus {
      height:100%;
      display:flex;
      justify-content:center;
      align-items: center;

      font-size: 7em;
      color: lightgray;
      position:relative;
      top:-5%;
    }

    #preview {
        width: 100%;
        height : 150px;
        margin: 10px auto;


        padding: 10px;
        position: relative;
        display: flex;
        justify-content: flex-start;
        flex-wrap: wrap;
        overflow: auto;

    }

    img {
        width: 50px;
        height: 50px;
        border: 1px solid gray;
        margin : 2px;
    }
    
    .product_info_block{
    	display:flex;
    	justify-content: start;
    	align-items: start;
    	
    
    }
    
    .add_product_btn{
    	margin:4px;
    }
    
    
</style>


</head>
<body>

<div class="wrapper">
  <h1>PRODUCT ADD</h1>
  <header>
    <div class="top--header">${msg}</div>
    <nav>   </nav>
  </header>
  <main>
    <section class="product_img_block">
      <div><h4>상품이미지</h4></div>
      <div class="product_img">
        <div class="upload-box">
          <div class="plus">+</div>
        </div>
        <div id="preview"></div>
      </div>
    </section>

    <section class="product_info_block">
      <div >
        <form  onsubmit="return false" name="productform" >
          <input name="productCode" class="form-control" placeholder="productCode">
          <input name="productName" class="form-control" placeholder="productName">
          <input name="productType" class="form-control" placeholder="productType">  
          <button  class="btn btn-primary add_product_btn w-100">제품등록</button><br />
        </form>
      </div>
    </section>


  </main>
  <footer>
  </footer>
</div>

<script>

	const formData = new FormData(); //폼태그 관련 정보 저장
	

	const uploadBox_el = document.querySelector('.upload-box'); 
	//dragenter / dragover / dragleave / drop
	
	uploadBox_el.addEventListener('dragenter',function(e){
		e.preventDefault();
		console.log("dragenter..");
	});	
	uploadBox_el.addEventListener('dragover',function(e){
		e.preventDefault();
		uploadBox_el.style.opacity='0.6';
		console.log("dragover..");
	});	
	uploadBox_el.addEventListener('dragleave',function(e){
		e.preventDefault();
		uploadBox_el.style.opacity='1';
		console.log("dragleave..");
	});	
	uploadBox_el.addEventListener('drop',function(e){
		e.preventDefault();
		console.log("drop..");
		console.log(e);
		console.log(e.dataTransfer);
		console.log(e.dataTransfer.files[0]);
		
		//유효성체크(이미지만, 하나씩만, 용량제한 ..)
		
		
		//미리보기
		const file = e.dataTransfer.files[0];
		const reader = new FileReader();
		reader.readAsDataURL(file);
		
		reader.onload = function(event){
			const preview = document.getElementById('preview');
			const img = document.createElement('img');
			img.setAttribute('src', event.target.result);
			preview.appendChild(img);
		/* 	$('#preview').append("<img src="+event.target.result+"/>"); */
		};
		//formData에 저장
		formData.append('files',file);
		
	});	
		
	const add_product_btn_el = document.querySelector('.add_product_btn');
	add_product_btn_el.addEventListener('click',function(){
		const productCode = document.productform.productCode.value;
		const productName = document.productform.productName.value;
		const productType = document.productform.productType.value;
		formData.append('productCode',productCode);
		formData.append('productName',productName);
		formData.append('productType',productType);
		
		axios.post('http://localhost:8080/product/upload',formData,{headers:{'content-Type' : 'multipart/form-data'}})
		.then(response=>{
			alert("SUCCESS");
			location.reload();
		})
		.catch(error=>{alert("FAIL");})
		
	})
	
</script>


</body>
</html>
