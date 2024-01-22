package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/comment")
public class CommentRestController {
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 댓글 생성
	 * @param postId
	 * @param content
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("postId") int postId,
			@RequestParam("content") String content,
			HttpSession session){
		//userLoginId 조회
		int userId = (int)session.getAttribute("userId");
		
		//DB insert
		commentBO.addComment(userId, postId, content);
		//응답값
		Map<String, Object> result = new HashMap<>();
		result.putIfAbsent("code", 200);
		result.putIfAbsent("result", "성공");
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("commentId") int commentId){
		//db delete
		commentBO.deleteCommentById(commentId);
		
		//응답값
		Map<String, Object> result = new HashMap<>();
		result.putIfAbsent("code", 200);
		result.putIfAbsent("result", "성공");
		
		return result;
	}
	
}
