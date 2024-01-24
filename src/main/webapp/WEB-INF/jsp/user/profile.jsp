<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<%-- 로그인 아이디 --%>
	<h1 class="ml-2">${userLoginId}</h1>
	<div class="d-flex justify-content-between">
		<%-- 이미지 프로필 --%>
		<img class="profile m-3" src="https://cdn.fanzeel.com/images/201906/5d0b3ea72915b.jpg" alt="프로필 이미지">
		<%-- 게시물, 팔로워, 팔로잉 수 --%>
		<div class="col-7 d-flex justify-content-around">
			<div>
				<div class="font-weight-bold">${profile.postCount}</div>
				<span>게시물</span>
			</div>
			<div>
				<div class="font-weight-bold">504</div>
				<span>팔로워</span>
			</div>
			<div>
				<div class="font-weight-bold">360</div>
				<span>팔로잉</span>
			</div>
		</div>
	</div>
	
	<%-- 피드 목록 --%>
	<div class="feedList mt-3 d-flex col-9">
		<%-- 카드 이미지 영역 --%>
		<c:forEach items="${profile.postList}" var="post">
			<a href="#" class="mr-2 feed" data-post-id="${post.id}"><img src="${post.imagePath}" class="card-image" alt="사진" width="300px" height="300px"></a> 
		</c:forEach>
	</div>

</div>

<script>
	$(document).ready(function(){
		//이미지 클릭시 피드 상세 페이지
		$(".feed").on('click', function(){
			let postId = $(this).data("post-id");
			alert(postId);
			
		});//feed클릭
		
	});
</script>
