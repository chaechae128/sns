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
			<a href="#" class="mr-2 feed" data-post-id="${post.id}" data-post-imagePath="${post.imagePath}" data-toggle="modal" data-target="#modal"><img src="${post.imagePath}" class="card-image" alt="사진" width="300px" height="300px"></a> 
		</c:forEach>
	</div>

</div>


<!-- Modal -->
<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog .modal-xl">
    <div class="modal-content">
      <div class="modal-body d-flex">
        <img width="500px" height="700px" id="modal-image">
        <div class="bg-white" id="postComment">
        	<h4>댓글</h4>
        	<div>111sssssssssssssssssssssssssssssssss1111</div>
        	<div>22222222</div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
	$(document).ready(function(){
		//이미지 클릭시 모달 띄우기
		$(".feed").on('click', function(e) {
			e.preventDefault(); // a 태그 올라가는 현상 방지
			
			let postId = $(this).data("post-id"); // getting
			let postImagePath = $(this).data("post-imagePath); 
			//alert(postId);
			
			// 1개로 존재하는 모달에 재활용을 위해 data-post-id를 심는다.
			$("#modal").data("post-id", postId); // setting
			$("#modal-image").attr("src", "https://cdn.fanzeel.com/images/201906/5d0b3ea72915b.jpg");
		});
		
		// 모달 안에 있는 삭제하기 클릭
		
			
		
	});
</script>
