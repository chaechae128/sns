<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="w-100 d-flex justify-content-center mt-3 mb-3">
	<div class="col-6 signUp">
		<h2 class="font-weight-bold">회원 가입</h2>
		<div class="p-4 bg-light rounded shadow">
			<form class="" method="" action="">
				<div>ID</div>
				<div class="d-flex align-items-center">
					<input type="text" class="form-control mt-2 col-9 mr-3" name="id"
						id="id" placeholder="ID를 입력해주세요"> <input type="button"
						value="중복확인" class="btn btn-info" id="IdCheckBtn">
				</div>
				<small class="text-danger d-none" id="duplicationText">사용중인
					아이디 입니다.</small> <small class="text-success d-none" id="availableText">사용가능한
					아이디 입니다.</small>

				<div>password</div>
				<input type="password" class="form-control mt-2 col-7"
					name="password" id="password" placeholder="비밀번호를 입력해주세요">

				<div>confirm password</div>
				<input type="password" class="form-control mt-2 col-7"
					name="confirmPassword" id="confirmPassword"
					placeholder="비밀번호를 입력해주세요">

				<div>이름</div>
				<input type="text" class="form-control mt-2 col-9" name="name"
					id="name" placeholder="이름을 입력해주세요">

				<div>이메일</div>
				<input type="text" class="form-control mt-2 col-9" name="email"
					id="email" placeholder="이메일을 입력해주세요">

				<div class="d-flex justify-content-center">
					<input type="button" id="signUpBtn" class="btn btn-primary mt-4"
						value="가입하기">
				</div>

			</form>
		</div>
	</div>
</div>