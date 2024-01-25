package com.sns.timeline.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.timeline.post.entity.PostEntity;
import com.sns.timeline.post.repository.PostRepository;

@Service
public class PostBO {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private FileManagerService fileManagerService;

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;

	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}

	// input: userId, content, imagePath output:void
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = fileManagerService.saveFile(userLoginId, file);

		return postRepository.save(PostEntity.builder().userId(userId).content(content).imagePath(imagePath).build());
	}

	// int: userId output: int(post 개수)
	public int countByUserId(int userId) {
		return postRepository.countByUserId(userId);
	}

	// int userId output: postList
	public List<PostEntity> getPostListByUserId(int userId) {
		return postRepository.findByUserId(userId);
	}

	public void deletePostByPostId(int postId, int userId) {
		// 기존 글 가져오기
		PostEntity post = postRepository.findById(postId).orElse(null);
		// 글 삭제
		postRepository.deleteById(postId);
		// 이미지 있으면 삭제
		if (post.getImagePath() != null) {
			fileManagerService.deleteFile(post.getImagePath());
		}
		// 댓글들 삭제
		commentBO.deleteCommentByPostId(postId);

		// 좋아요들 삭제
		likeBO.deleteLikeByPostIdUserId(postId, userId);

	}
}
