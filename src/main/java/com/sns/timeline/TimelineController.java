package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	@Autowired
	private TimelineBO timelineBO;
	

	
	@GetMapping("/timeline-view")
	public String timelineView(Model model) {
		
		
		//DB조회 - post
		//List<PostEntity> postList = postBO.getPostList();

		//model.addAttribute("postList", postList);
		
		//DB조회 - comment
		//List<Comment> commentList = commentBO.getCommentListByPostId();
		
		//model.addAttribute("commentList", commentList);
		List<CardView> cardViewList	= timelineBO.generateCardViewList();
		
		model.addAttribute("cardViewList", cardViewList);
		model.addAttribute("viewName", "timeline/timeline");
	
		return "template/layout";
	}
	
}
