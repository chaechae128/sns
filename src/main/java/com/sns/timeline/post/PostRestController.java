package com.sns.timeline.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.timeline.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;
@RequestMapping("/post")
@RestController
public class PostRestController {
	@Autowired
	private PostBO postBO;
	
	/**
	 * 글 생성API
	 * @param content
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam(value ="content", required=false) String content,
			@RequestParam("file") MultipartFile file,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		String userLoginId = (String)session.getAttribute("userLoginId");
		//로그인 풀려있는지 체크
		Integer userId = (Integer) session.getAttribute("userId");
		if(userId == null) {
			result.put("code", 500);
			result.put("error_message", "로그인이 되어있지 않습니다. 로그인 후 이용해주세요");
			return result;
		}
		//DB insert
		postBO.addPost(userId, userLoginId, content, file);
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session){
		
		int userId = (int) session.getAttribute("userId");
		//db delete
		postBO.deletePostByPostId(postId, userId);
		//응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
