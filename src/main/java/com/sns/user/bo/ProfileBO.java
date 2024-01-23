package com.sns.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.timeline.post.bo.PostBO;
import com.sns.timeline.post.entity.PostEntity;
import com.sns.user.domain.ProfileView;
import com.sns.user.entity.UserEntity;

@Service
public class ProfileBO {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	public ProfileView generateProfileView(int userId) {
		ProfileView profileView = new ProfileView();
		//postList img만
		List<PostEntity> postList = postBO.getPostListByUserId(userId);
		profileView.setPostList(postList);
		
		//post개수
		int postCount = postBO.countByUserId(userId);
		profileView.setPostCount(postCount);
		
		//user 정보
		UserEntity user = userBO.getUerEntityById(userId);
		profileView.setUser(user);
		
		
		return profileView;
	}
}
