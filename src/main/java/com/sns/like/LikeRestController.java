package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;
	
	// GET:  /like?postId=1		@ReauestParam("postId")
	// GET:  /like/13			@PathVarialble
	
	@RequestMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable(name ="postId") int postId,
			HttpSession session){
		//로그인 여부 확인
		Map<String, Object> result = new HashMap<>();
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null) {
			result.put("code", 300);
			result.put("error_message", "로그인이 되어있지 않습니다. 로그인 후 이용해주세요");
			return result;
		}
		
		//BO 호출 -> likeToggle BO에서  like테이블에 있으면 비워진 하트 아니면 채워진 하트
		likeBO.LikeToggle(postId, userId);
		
		//응답값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
