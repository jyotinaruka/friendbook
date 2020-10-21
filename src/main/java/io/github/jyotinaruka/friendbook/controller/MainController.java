package io.github.jyotinaruka.friendbook.controller;

import io.github.jyotinaruka.friendbook.model.Comment;
import io.github.jyotinaruka.friendbook.model.Post;
import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
  @Autowired
  private UserService userService;

  @GetMapping("/home")
  public String homePage(
    Model model,
    HttpSession session,
    @ModelAttribute("post") Post post
  ) {
    Long userId = (Long) session.getAttribute("user_id");
    User u = userService.findUserById(userId);
    model.addAttribute("loginUser", u);
    model.addAttribute("allPosts", userService.allPosts());
    model.addAttribute("allComments", userService.allComments());

    SimpleDateFormat dateformat = new SimpleDateFormat(
      "EEEE',' 'the' d 'of' MMMM',' yyyy"
    );

    String localDate = dateformat.format(new Date());

    model.addAttribute("date", localDate);

    SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");

    String localTime = timeformat.format(new Date());
    model.addAttribute("time", localTime);

    model.addAttribute(post);
    model.addAttribute("findAll", userService.findAll());

    return "home.jsp";
  }

  @PostMapping("/home")
  public String home(
    @RequestParam("post") String post,
    Model model,
    HttpSession session,
    Long id
  ) {
    Long userId = (Long) session.getAttribute("user_id");
    User u = userService.findUserById(userId);

    Post post1 = new Post();
    post1.setMessage(post);

    post1.setPostedBy(u);
    userService.savePost(post1);

    model.addAttribute("allPosts", userService.allPosts());
    model.addAttribute("allComments", userService.allComments());
    //userService.createComment(comment,((userService.findPost(id))));

    return "redirect:/home";
  }

  @PostMapping("/comment/{id}")
  public String comment(
    @RequestParam("message") String comment,
    Model model,
    HttpSession session,
    @PathVariable("id") Long id
  ) {
    Long userId = (Long) session.getAttribute("user_id");
    User u = userService.findUserById(userId);

    Comment comment1 = new Comment();
    comment1.setMessage(comment);
    comment1.setPost(userService.findPost(id));
    comment1.setCommentedBy(u);
    userService.saveComment(comment1);

    return "redirect:/home";
  }
  
  @PostMapping("/like/{id}")
  public String like(Model model, HttpSession session, @PathVariable("id")Long id) {
	  	Long userId= (Long) session.getAttribute("user_id");
	  	User u = userService.findUserById(userId);
	  	
	  	Post like = new Post();
	  	like.setPostedBy(u);
	  	userService.savePost(like);
	  	return "redirect:/home";
  }
}
