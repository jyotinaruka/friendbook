package io.github.jyotinaruka.friendbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.github.jyotinaruka.friendbook.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	
}
