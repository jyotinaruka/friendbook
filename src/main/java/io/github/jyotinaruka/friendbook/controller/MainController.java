package io.github.jyotinaruka.friendbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.github.jyotinaruka.friendbook.model.Post;
import io.github.jyotinaruka.friendbook.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String homePage(Model model) {
		userService.allPosts();
		userService.allComments();
		return "home.jsp";
	}
	@PostMapping("/home")
	public String home(@Valid @ModelAttribute("post")Post post, Model model, BindingResult result) {
		userService.createPost(post);
		return "redirect:/home";
	}
}
