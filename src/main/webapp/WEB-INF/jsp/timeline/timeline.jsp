<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="timeline d-flex justify-content-center">
	<div class="mt-3 contents-box">
	
		<%-- 글쓰기 영역 로그인 된 사람만 보이게 --%>
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" placeholder="내용을 입력하세요" class="w-100 border-0"></textarea>
			<div class="d-flex justify-content-between">
				<a href="#" id="fileUploadBtn"><img src="/static/img/image_icon.png" class="ml-1" width="35px" alt="이미지"></a>
				<button id="writeBtn" class="btn btn-primary m-2">업로드</button>
			</div>
		</div>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
			<%-- 카드 1 --%>
			<div class="card mt-3">
				<%--글쓴이, 더보기(삭제) --%>
				<div class="d-flex justify-content-between bg-light align-items-center">
					<div class="font-weight-bold pl-2">${post.userId}</div>
					<a href="#" class="more-btn"><img src="/static/img/more-icon.png" class="ml-1" width="40px" alt="더보기"></a>
				</div>
				
				<%-- 카드 이미지 영역 --%>
				<div class="card-img">
					<img src="${post.imagePath}" class="w-100 card-image" alt="사진"> 
				</div>
				<%--좋아요 영역 --%>
				<div class="card-like d-flex justify-content-start m-2">
					<a href="#" class="like-btn"><img src="/static/img/heart-icon.png" alt="채워진 하트" width="20px" height="20px"></a>
					<div class="ml-1">좋아요 11개</div>
				</div>
				<%-- 글 영역 --%>
				<div class="card-post d-flex justify-content-start" >
					<div class="font-weight-bold ml-3">${post.userId}</div>
					<div class="ml-2">${post.content}</div>
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-3 mb-1 mt-3 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<%-- 댓글 내용들 --%>
					<div class="d-flex justify-content-start m-2">
						<div class="font-weight-bold">댓글쓰니</div>
						<div class="ml-2">댓글 내용</div>
						<%-- 댓글 삭제 버튼 --%>
						<a href="#" class="comment-del-btn">
							<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
						</a>
					</div>
					
				</div>
				
				<%-- 댓글 쓰기 --%>
				<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light">게시</button>
				</div>
			</div>
		</div>
	</div>
</div>