<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="timeline d-flex justify-content-center w-100">
	<div class="mt-3 w-50">
		<div class="border">
			<div><textarea rows="3" cols="70" class="border-0">내용을 입력하세요</textarea></div>
			<div class="d-flex justify-content-between">
				<a href="#"><img src="/static/img/image_icon.png" class="ml-1" width="40px" alt="이미지"></a>
				<button type="submit" class="btn btn-primary m-2">업로드</button>
			</div>
		</div>
		
		<div class="card mt-3">
			<%--로그인된 사용자 --%>
			<div class="d-flex justify-content-between bg-light align-items-center">
				<div class="font-weight-bold pl-2"> marobiana</div>
				<a href="#"><img src="/static/img/more-icon.png" class="ml-1" width="40px" alt="더보기"></a>
			</div>
			<%-- 이미지 영역 --%>
			<div>
				<img src="/static/img/panda.jpg" class="w-100" alt="사진"> 
			</div>
			<%--좋아요 영역 --%>
			<div class="d-flex justify-content-start m-2">
				<img src="/static/img/heart-icon.png" alt="채워진 하트" width="20px" height="20px">
				<div class="ml-1">좋아요 11개</div>
			</div>
			<%-- post - content --%>
			<div class="d-flex justify-content-start" >
				<div class="font-weight-bold"> marobiana</div>
				<div class="ml-2">비지도 학습을 해본 결과입니다. cluster알고리즘을 사용해봤어요</div>
			</div>
			<div class="font-weight-bold mt-2">댓글</div>
			
			<%-- 댓글 영역 --%>
			<div class="border-top mb-2">
				<div class="d-flex justify-content-start m-2">
					<div class="font-weight-bold">hagulu</div>
					<div>: 분류가 잘 되었군요~ </div>
				</div>
				<div class="d-flex justify-content-start m-2">
					<div class="font-weight-bold">jun-coffee</div>
					<div>: 철이 없었죠 분류를 위해 클러스터를 썼다는게 </div>
				</div>
			</div>
			
			<%-- 댓글 쓰기 --%>
			<div class="d-flex">
  				<input type="text" class="form-control" placeholder="댓글 달기" aria-label="Recipient's username" aria-describedby="basic-addon2">
  				<div class="input-group-append">
    				<button class="input-group-text btn" id="basic-addon2">게시</button>
  				</div>
			</div>
		</div>
	</div>
</div>