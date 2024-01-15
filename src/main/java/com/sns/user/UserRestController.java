package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.user.bo.UserBO;
import com.sns.user.common.EncryptUtils;
import com.sns.user.entity.UserEntity;

@RequestMapping("/user")
@RestController
public class UserRestController {
	@Autowired
	private UserBO userBO;
		
	/**
	 * 아이디 중복확인API
	 * @param loginId
	 * @return
	 */
	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		//DB조회
		UserEntity userEntity = userBO.getUserEntityByLoginId(loginId);
		
		//응답
		Map<String, Object> result = new HashMap<>();
		if(userEntity != null) { //중복
			result.put("code", 200);
			result.put("is_duplicated_id", true);
		}else {
			result.put("code", 200);
			result.put("is_duplicated_id", false);
		}
		
		return result;
	}
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email){
		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		//DB insert
		Integer userId = userBO.addUser(loginId, hashedPassword, name, email);
		//응답값
		Map<String, Object> result = new HashMap<>();
		if(userId != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "회원가입에 실패했습니다.");
		}
		
		return result;
	}
	
	
}
