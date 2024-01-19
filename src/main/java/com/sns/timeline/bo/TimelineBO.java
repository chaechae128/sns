package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.timeline.domain.CardView;
import com.sns.timeline.post.bo.PostBO;
import com.sns.timeline.post.entity.PostEntity;
import com.sns.user.bo.UserBO;

@Service
public class TimelineBO {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	
	//input:x	output:List<CardView>
	//보통 DB에서 가져오는게 아니라 가공을 해서 가져오면 메소드 이름은 generate
	public List<CardView> generateCardViewList(){
		List<CardView> cardViewList = new ArrayList<>();
		CardView cardView = new CardView();
		// 글 목록을 가져온다
		List<PostEntity> postList = postBO.getPostList();
		//<UserEntity> userList = userBO.get
			
		// 글 목록 반목문 순회
		//post = > cardView => cardViewList 에 넣기
		for(int i = 0; i<postList.size(); i++) {
			cardView.setPost(postList.get(i));	
			cardViewList.add(cardView);
		}
		
		return cardViewList;
	}
}
