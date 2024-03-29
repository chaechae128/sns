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
				<%--file 태그를 숨겨두고 이미지를 클릭하면 file태그를 클릭한 것과 같은 효과 --%>
				<input type="file" id="file" accept=".jpg, .jpeg, .gif, .png" class="d-none">

				<%--이미지에 마우스를 올리면 마우스 커서가 변하도록 적용  --%>
				<div class="d-flex">
					<a href="#" id="fileUploadBtn"><img src="/static/img/image_icon.png" class="ml-1" width="35px" alt="이미지"></a>
					<%-- 업로드 된 임시 이미지 파일 이름 나타내는 곳 --%>
					<div id = "fileName" class="ml-2"></div>
				</div>
				<button id="writeBtn" class="btn btn-primary m-2">업로드</button>
				
				
			</div>
		</div>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		<div class="timeline-box my-5">
		<c:forEach items="${cardViewList}" var="cardView">
			<%-- 카드 1 --%>
			<div class="card mt-3">
				<%--글쓴이, 더보기(삭제) --%>
				<div class="d-flex justify-content-between bg-light align-items-center">
					<div class="font-weight-bold pl-2">${cardView.user.loginId}</div>
					<%-- (더보기  ... 버튼) 로그인 된 사람과 글쓴이 정보가 일치할 때만 노출 --%>
					<c:if test="${userId eq cardView.post.userId}">
					<a href="#" class="more-btn" data-toggle="modal" data-target="#modal" data-post-id="${cardView.post.id}"><img src="/static/img/more-icon.png" class="ml-1" width="40px" alt="더보기"></a>
					</c:if>
				</div>
				
				<%-- 카드 이미지 영역 --%>
				<div class="card-img">
					<img src="${cardView.post.imagePath}" class="w-100 card-image" alt="사진"> 
				</div>
				<%--좋아요 영역 --%>
				<div class="card-like d-flex justify-content-start m-2">
					<%--cardView.filledLike --%>
					<c:choose>
						<c:when test="${cardView.filledLike eq true}">
							<a href="#" class="like-btn" data-post-id="${cardView.post.id}"><img src="/static/img/heart-icon.png" alt="채워진 하트" width="20px" height="20px"></a>
						</c:when>
						<c:otherwise>
							<a href="#" class="emptylike-btn" data-post-id="${cardView.post.id}"><img src="/static/img/empty-heart-icon.png" alt="비워진 하트" width="20px" height="20px"></a>
						</c:otherwise>
					</c:choose>
					
					
					
					<div class="ml-1">좋아요 ${cardView.likeCount}개</div>
				</div>
				<%-- 글 영역 --%>
				<div class="card-post d-flex justify-content-start" >
					<div class="font-weight-bold ml-2">${cardView.user.loginId}</div>
					<div class="ml-2">${cardView.post.content}</div>
				</div>
				
				<%-- 댓글 제목 --%>
				<div class="card-comment-desc border-bottom">
					<div class="ml-2 mb-1 mt-3 font-weight-bold">댓글</div>
				</div>
				
				<%-- 댓글 목록 --%>
				<div class="card-comment-list m-2">
					<%-- 댓글 내용들 --%>
					<c:forEach items="${cardView.commentList}" var="commentView">
						<div class="d-flex justify-content-start m-2">
							
							
							<div class="font-weight-bold">${commentView.userEntity.loginId}</div>
							<div class="ml-2 ">${commentView.comment.content}</div>
							<%-- 댓글 삭제 버튼 --%>
							<a href="#" class="comment-del-btn" data-comment-id="${commentView.comment.id}" data-user-id="${userId}">
								<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10" height="10">
							</a>
							
						</div>

					</c:forEach>
				</div>
				<%-- 댓글 쓰기 --%>
				<div class="comment-write d-flex border-top mt-2">
					<input type="text" class="form-control border-0 mr-2 comment-input" placeholder="댓글 달기"/> 
					<button type="button" class="commentBtn btn btn-light" data-user-id="${userId}" data-post-id="${cardView.post.id}">게시</button>
				</div>
			</div>
			</c:forEach>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<%-- 
		modal-sm: 작은 모달창 
		modal-dialog-centered: 수직 기준 가운데 위치
	--%>
	<div class="modal-dialog modal-sm modal-dialog-centered">
		<div class="modal-content text-center">
			<div class="py-3 border-bottom">
    			<a href="#" id="postDelete">삭제하기</a>
    		</div>
			<div class="py-3">
    			<a href="#" data-dismiss="modal">취소하기</a>
    		</div>
		</div>
	</div>
</div>


<script>
	$(document).ready(function(){
		//파일 이미지 클릭 = > 숨겨져 있는 id="file" 동작시킨다.
		$("#fileUploadBtn").on('click', function(e){
			e.preventDefault(); // a태그의 기본 동작 멈춤(스크롤이 위로 올라감)
			$("#file").click(); //input file을 클릭한 효과
		});//fileUploadBtn
		
		//사용자가 이미지를 선택하는 순간 유효성 확인 및 업로드된 파일명 노출
		$("#file").on('change', function(e){
			//alert("이미지 선택");
			//취소를 누를 때 비어있는 경우 처리
			if (file == null){
				$("#file").val(""); //파일 태그 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
				$("#fileName").text(""); //보여지지는 파일명 지우기
				return;
			}
			
			let fileName = e.target.files[0].name; // teletob.jpg
         	console.log(fileName);
			
			//확장자 유효성 체크
			let ext = fileName.split(".").pop().toLowerCase();
			//alert(ext);
			
			if(ext != "jpg" && ext != "jpeg" && ext != "gif" && ext != "png"){
				alert("이미지 파일만 업로드 할 수 있습니다");
				$("#file").val(""); //파일 태그 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
				$("#fileName").text(""); //보여지지는 파일명 지우기
				return; 
			}
			
			//유효성 통과한 이미지의 경우 파일명 노출
			$("#fileName").text(fileName);

			
		});
		
		$("#writeBtn").on('click', function(){
			//alert("업로드 버튼 클릭");
			
			//글 내용
			let content = $("#writeTextArea").val();
			//이미지
			let file = $("#file").val();
			
			//validation
			if(!file){
				alert("이미지 파일을 선택하세요");
				return;
			}
			
			let ext = file.split(".").pop().toLowerCase();
			//alert(ext);
			
			if(ext != "jpg" && ext != "jpeg" && ext != "gif" && ext != "png"){
				alert("이미지 파일만 업로드 할 수 있습니다");
				$("#file").val(""); //파일 태그 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
				$("#fileName").text(""); //보여지지는 파일명 지우기
				return; 
			}
			
			//form 태그 구성
			let formData = new FormData();
         	formData.append("content", content);
         	formData.append("file", $("#file")[0].files[0]);
			
			//ajax
			$.ajax({
				//request
				type: "POST"
				,url: "/post/create"
				,data: formData // form을 통째로 보냄
				,enctype:"multipart/form-data" //파일 업로드를 위한 필수 설정  * 특히 이미지 있을 떄 반드시 필요
				,processData:false //파일 업로드를 위한 필수 설정
				,contentType:false //파일 업로드를 위한 필수 설정  dataparameter를 보낼 떄 원랜 String 으로 보내는데 난 객체로 보내겠다.
				
				//response
				,success:function(data){
					if(data.code == 200){
				
						location.reload(true);
					}else {
						alert(data.error_message);
					}
				}
				,error:function(status, response, error){
					alert("글을 저장하는데 실패했습니다.");
				}
			
			});//ajax
			
		});//writeBtn
		
		
		//댓글 달기
		$(".commentBtn").on('click', function(e){
			e.preventDefault();
			//alert("commentBtn");
			let userId = $(this).data("user-id");
			//alert(userId);
			if(!userId){
				// 비로그인 시 로그인 화면 이동
				alert("로그인 후 이용 가능합니다");
				location.href="/user/sign-in-view";
				return;
			}
		
			let postId = $(this).data("post-id");
			//alert(postId);
			
			//댓글 내용 가져오기
			//1)이전 태그 값 가져오기
			//let content = $(this).prev().val(); //하나 전 태그 -> input
			//alert(content);
			
			//2)형제 태그 중에서 input태그에 있는 값 가져오기
			let content = $(this).siblings("input").val();  //siblings(): 형제 태그 가져오기
			//alert(content);
			
			//validation
			if(!content){
				alert("댓글 내용을 입력하세요");
				return;
			}
			
			$.ajax({
				type:"POST"
				,url:"/comment/create"
				,data:{"postId":postId, "content":content}
			
				,success:function(data){
					if(data.code==200){
						location.reload(true);
					} else{
						alert(data.error_message);
					}
				}
				,error:function(request, status, error){
					alert("댓글을 저장하는데 실패했습니다.");
				}
				
			
			});//ajax
			
		});//comment-btn
		
		$(".comment-del-btn").on('click', function(e){
			e.preventDefault();
			let commentId =  $(this).data("comment-id");
			//alert(commentId);
			
			let userId = $(this).data("user-id");
			//alert(userId);
			if(!userId){
				// 비로그인 시 로그인 화면 이동
				alert("로그인 후 이용 가능합니다");
				location.href="/user/sign-in-view";
				return;
			}
			
			$.ajax({
				type:"DELETE"
				,url:"/comment/delete"
				,data:{"commentId":commentId}
			
				,success:function(data){
					if(data.code==200){
						alert("댓글을 삭제하였습니다");
						location.reload(true);
					} else{
						alert(data.error_message);
					}
				}
				,error:function(request, status, error){
					alert("댓글을 삭제하는데 실패했습니다.");
				}
			});
		});//comment-del-btn
		
		
		$(".emptylike-btn").on('click', function(e){
			e.preventDefault();
			let postId = $(this).data("post-id");
			//alert("비워진하트" + postId);

			$.ajax({
				url:"/like/"+postId 
			
				,success:function(data){
					if(data.code==200){
						location.reload(true);
					} else if (data.code== 300){
						alert(data.error_message);
						location.href="/user/sign-in-view";
					} else{
						alert(data.error_message);
					}
				}
				,error:function(request, status, error){
					alert("좋아요를 누르는데 실패했습니다.");
				}
			});
			
			
		}); //비워진 하트 -> 채워진 하트
		
		$(".like-btn").on('click', function(e){
			e.preventDefault();
			let postId = $(this).data("post-id");
			//alert("비워진하트" + postId);
			

			
			$.ajax({
				url:"/like/"+postId  //like/13
			
				,success:function(data){
					if(data.code==200){
						location.reload(true);
					} else if (data.code== 300){
						alert(data.error_message);
						location.href="/user/sign-in-view";
					} else{
						alert(data.error_message);
					}
				}
				,error:function(request, status, error){
					alert("좋아요를 취소하는데 실패했습니다.");
				}
			});
			
		}); //채워진 하트 -> 비워진 하트
		
		// 더보기(...) 클릭 => 모달 띄우기
		$(".more-btn").on('click', function(e) {
			e.preventDefault(); // a 태그 올라가는 현상 방지
			
			let postId = $(this).data("post-id"); // getting
			//alert(postId);
			
			// 1개로 존재하는 모달에 재활용을 위해 data-post-id를 심는다.
			$("#modal").data("post-id", postId); // setting
		});
		
		// 모달 안에 있는 삭제하기 클릭
		$("#modal #postDelete").on('click', function(e) {
			e.preventDefault(); // a 태그 위로 올라가는 현상 방지
			
			let postId = $("#modal").data("post-id");
			//alert(postId);
			
			$.ajax({
				type:"DELETE"
				,url:"/post/delete"
				,data:{"postId":postId}
				,success:function(data){
					if(data.code == 200){
						location.reload();
					}else{
						alert(data.error_message)
					}
				}
				,error:function(request, status, error){
					alert("글을 삭제하는데 실패했습니다");
				}
			});//ajax
			
			
		});
	});
	
	
</script>