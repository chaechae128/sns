package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timeline")
@Controller
public class TimelineController {
	
	@GetMapping("/timeline-view")
	public String timelineView(HttpSession session, Model model) {
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/user/sign-in-view";
		}
		
		model.addAttribute("viewName", "timeline/timeline");
	
		return "template/layout";
	}
	
}
