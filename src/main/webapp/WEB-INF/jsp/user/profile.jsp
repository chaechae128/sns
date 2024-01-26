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
			<a href="#" class="mr-2 feed" data-post-id="${post.id}" data-post-content="${post.content}" data-post-image-path="${post.imagePath}" data-toggle="modal" data-target="#modal"><img src="${post.imagePath}" class="card-image" alt="사진" width="300px" height="300px"></a> 
		</c:forEach>
	</div>

</div>


<!-- Modal -->
<div class="modal fade bg-white" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog .modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-body d-flex">
        <img id="modalImage" src="" width="600px" height="700px">
        <div id="content" value=""></div>
        <div class="bg-white col-12" id="postComment">
        	<div class="d-flex justify-content-between">
        		<h4 class="ml-3">댓글</h4>
        		<a href="#" data-dismiss="modal"><img src="/static/img/x.jpg" width="50px"></a>
   
        	</div>
        	<div>
        	<%-- 댓글 내용들 --%>
        	
        	<c:forEach items="${cardViewList}" var="cardView">
			
			<c:forEach items="${profile.postList}" var="post">
			<c:forEach items="${cardView.commentList}" var="commentView">
			<c:if test="${commentView.comment.postId == post.id}">
				<div class="d-flex justify-content-start m-2">
					<div>${post.id}</div>
					<div>${commentView.comment.postId}</div>
					<div class="font-weight-bold">${commentView.userEntity.loginId}</div>
					<div class="ml-2 ">${commentView.comment.content}</div>
				</div>
			</c:if>
			</c:forEach>	
			</c:forEach>
			<%--<c:if test="${cardView.post.id eq post.id}"> -->
			<%-- </c:if> --%>
        	</c:forEach>
        	
        	</div>
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
			let postImagePath = $(this).data("post-image-path");
			let postContent = $(this).data("post-content");
			//alert(postImagePath);
			//alert(postContent);
			
			// 1개로 존재하는 모달에 재활용을 위해 data-post-id를 심는다.
			$("#modal").data("post-id", postId); // setting
			$("#modalImage").attr("src", postImagePath);
			$("#content").data("post-content", postContent);
			$("#content").attr("value", postContent);
			//$("#modalImage").src=postImagePath;
			//$("#modal").modal('show');
			//$("#contents.body-contents").val(data);
		});
		
		// 모달 안에 있는 삭제하기 클릭
		
			
		
	});
</script>
