package com.sns.timeline.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;
import com.sns.common.FileManagerService;
import com.sns.timeline.post.entity.PostEntity;
import com.sns.timeline.post.repository.PostRepository;

@Service
public class PostBO {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManagerService;
	

	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	//input: userId, content, imagePath   output:void 
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = fileManagerService.saveFile(userLoginId, file);
		
		
		return postRepository.save(PostEntity.builder()
			                .userId(userId)
			                .content(content)
			                .imagePath(imagePath)
			                .build());
	}
	
	//int: userId		output: int(post 개수)
	public int countByUserId(int userId) {
		return postRepository.countByUserId(userId);
	}
	
	//int userId    output: postList
	public List<PostEntity> getPostListByUserId(int userId){
		return postRepository.findByUserId(userId);
	}
}
