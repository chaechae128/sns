package com.sns.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.ProfileBO;
import com.sns.user.domain.ProfileView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private ProfileBO profileBO;
	
	@Autowired
	private TimelineBO timelineBO;
	/**
	 * 회원가입 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-up-view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/signUp");
		return "template/layout";	
	}
	
	/**
	 * 로그인 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/sign-in-view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/signIn");
		return "template/layout";
	}
	
	@RequestMapping("sign-out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/sign-in-	view";
	}
	
	
	@GetMapping("/profile-view")
	public String profileView(Model model, HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		ProfileView profile	= profileBO.generateProfileView(userId);
		List<CardView> cardViewList	= timelineBO.generateCardViewList(userId);
		
		model.addAttribute("cardViewList", cardViewList);
		model.addAttribute("profile", profile);
		model.addAttribute("viewName", "user/profile");
		
		return "template/layout";
	}
	
}
