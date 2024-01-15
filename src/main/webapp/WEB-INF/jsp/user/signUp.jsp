<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center mt-3 mb-3">
	<div class="col-6 signUp">
		<h2 class="font-weight-bold">회원 가입</h2>
		<div class="p-4 bg-light rounded shadow">
			<form id="signUpForm" method="post" action="/user/sign-up">
				<div>ID</div>
				<div class="d-flex align-items-center">
					<input type="text" class="form-control mt-2 col-9 mr-3" name="loginId" id="loginId" placeholder="ID를 입력해주세요"> 
						<input type="button" value="중복확인" class="btn btn-info" id="loginIdCheckBtn">
				</div>
				
				<%-- 아이디 체크 결과 --%>
				<div class="ml-3 mb-3">
					<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
					<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
					<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
				</div>

				<div>password</div>
				<input type="password" class="form-control mt-2 col-7" name="password" id="password" placeholder="비밀번호를 입력해주세요">

				<div>confirm password</div>
				<input type="password" class="form-control mt-2 col-7" name="confirmPassword" id="confirmPassword" placeholder="비밀번호를 입력해주세요">

				<div>이름</div>
				<input type="text" class="form-control mt-2 col-9" name="name"
					id="name" placeholder="이름을 입력해주세요">

				<div>이메일</div>
				<input type="text" class="form-control mt-2 col-9" name="email"
					id="email" placeholder="이메일을 입력해주세요">

				<div class="d-flex justify-content-center">
					<input type="submit" id="signUpBtn" class="btn btn-primary mt-4"
						value="가입하기">
				</div>

			</form>
		</div>
	</div>
</div>

<script>
	$(document).ready(function(){
		//alert("히히");
		
		//아이디 중복확인
		$("#loginIdCheckBtn").on('click', function(){
			
			//경고문구 초기화
			$("#idCheckLength").addClass("d-none");
			$("#idCheckDuplicated").addClass("d-none");
			$("#idCheckOk").addClass("d-none");
			
			let loginId = $("#loginId").val().trim();
			if(loginId.length < 4) {
				$("#idCheckLength").removeClass("d-none");
				return;
			}
			
			$.get("/user/is-duplicated-id", {"loginId":loginId})//request
			.done(function(data) {
				if(data.code == 200) {
					if(data.is_duplicated_id){//중복
						$("#idCheckDuplicated").removeClass("d-none");
					} else{ //중복x
						$("#idCheckOk").removeClass("d-none");
					}
				} else {
					alert(data.error_message);	
				}
			});
			
		});//loginIdCheckBtn 
		
		$('#signUpForm').on('submit', function(e) {
			e.preventDefault(); // submit 기능 막음
			
			//alert("회원가입");
			
			// validation check
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			let confirmPassword = $("#confirmPassword").val();
			let name = $("#name").val().trim();
			let email = $("#email").val().trim();
			
			if (!loginId) {
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if (!password || !confirmPassword) {
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			if (password != confirmPassword) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			
			if (!name) {
				alert("이름을 입력하세요.");
				return false;
			}
			
			if (!email) {
				alert("이메일을 입력하세요.");
				return false;
			}
			
			// 중복확인 후 사용 가능한 아이디인지 확인
			// => idCheckOk 클래스 d-none이 없을 때
			if ($("#idCheckOk").hasClass('d-none')) {
				alert("아이디 중복확인을 다시 해주세요.");
				return false;
			}  
			
			//alert("회원가입");
			
			// 1) 서버 전송: submit을 js에서 동작시킴
			//$(this)[0].submit(); // 화면 이동이 된다.
			
			// 2) AJAX: 화면 이동 되지 않음(콜백함수에서 이동) 응답값 JSON
			let url = $(this).attr("action");
			//alert(url);
			let params = $(this).serialize();
			console.log(params);
			
			$.post(url, params) //request
			.done(function(data){
				if(data.code == 200){
					alert("가입을 환영합니다. 로그인 해주세요.");
					location.href = "/user/sign-in-view"; // 로그인 화면으로 이동
				} else{
					alert(data.error_message);
				}
			});
		});
	});
	
</script>