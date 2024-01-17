package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.post.bo.PostBO;
import com.sns.timeline.post.entity.PostEntity;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	@Autowired
	private PostBO postBO;
	@GetMapping("/timeline-view")
	public String timelineView(HttpSession session, Model model) {
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/user/sign-in-view";
		}
		//DB조회 
		List<PostEntity> postList = postBO.getPostList();
		model.addAttribute("postList", postList);
		
		
		model.addAttribute("viewName", "timeline/timeline");
	
		return "template/layout";
	}
	
}