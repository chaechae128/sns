package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.timeline.domain.CardView;
import com.sns.timeline.post.bo.PostBO;
import com.sns.timeline.post.entity.PostEntity;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class TimelineBO {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	//input:x	output:List<CardView>
	//보통 DB에서 가져오는게 아니라 가공을 해서 가져오면 메소드 이름은 generate
	public List<CardView> generateCardViewList(int userId){
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다
		List<PostEntity> postList = postBO.getPostList();
		//List <UserEntity> userList = userBO.getUserEntityByLoginId(null)
			
		// 글 목록 반목문 순회
		//post = > cardView => cardViewList 에 넣기
		for(PostEntity post : postList) {
			//post하나에 대응되는 하나의 카드를 만든다.
			CardView cardView = new CardView();
			
			//글 1개
			cardView.setPost(post);
			
			//글쓴이 정보
			UserEntity user = userBO.getUerEntityById(post.getUserId());
			cardView.setUser(user);
			
			//댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			cardView.setCommentList(commentList);	
			
			//좋아요 개수
			cardView.setLikeCount(likeBO.selectLikeCountByPostId(post.getId()));
			
			//로그인된 사람이 좋아요를 했는지 여부(비로그인일때는 개수만)
			cardView.setFilledLike(likeBO.isLikeByByPostIdUserId(post.getId(), userId));
			
			
			//★★★★ 마지막에 cardView를 list에 넣는다.
			cardViewList.add(cardView);
			
			
		}
		
		return cardViewList;
	}
}
