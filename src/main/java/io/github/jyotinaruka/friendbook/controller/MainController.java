package io.github.jyotinaruka.friendbook.controller;

import io.github.jyotinaruka.friendbook.model.Comment;
import io.github.jyotinaruka.friendbook.model.Event;
import io.github.jyotinaruka.friendbook.model.Post;
import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.service.EventService;
import io.github.jyotinaruka.friendbook.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
  @Autowired
  private UserService userService;

  @Autowired
  private EventService eventService;
  
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
  
  //Scott's code
  @RequestMapping("/events")
  public String events(@ModelAttribute("user") User user, Model model,
		  @ModelAttribute("event") Event event,
		  HttpSession session) {
//	if(session.getAttribute("userId") == null) {
//			return "redirect:/home";
//	}
  
	
	List<User> users = userService.findAll();
	model.addAttribute("users", users);
	List<Event> events = eventService.allEvents();
	model.addAttribute("events", events);
	
	Long userId = (Long) session.getAttribute("userId");
	User u = userService.findUserById(userId);
	model.addAttribute("user", u);
	
	return "events.jsp";
  }
  
  @RequestMapping("/test")
	public String test() {
		return "test.jsp";
	}
  
  
  @RequestMapping("/events/new")
  public String newEvent(@ModelAttribute("event") Event event, Model model, HttpSession session) {
//	  if(session.getAttribute("userId") == null) {
//			return "redirect:/home";
//		}
	  
	  List<User> users = userService.findAll();
		model.addAttribute("users", users);
	
		return "newEvent.jsp";
  }
  
  @RequestMapping(value="/events", method=RequestMethod.POST)
  public String submitEvent(@Valid @ModelAttribute("event") Event event, BindingResult result,
		  HttpSession session, Model model) {
	  if(result.hasErrors()) {

		  List<User> users = userService.findAll();
		  model.addAttribute("users", users);
			
		  return "newEvent.jsp";
	  }
	  else {
		  User host = userService.findUserById((Long) session.getAttribute("userId"));
		  event.setHost(host);
			
		  
		  eventService.submitEvent(event);
		  return "redirect:/events";
	  }
  }
  
  @RequestMapping("/events/{id}")
	public String showEvent(@PathVariable("id") Long id, Model model, 
			HttpSession session) {
//		if(session.getAttribute("userId") ==null) {
//			return "redirect:/login";
//		}
		Event event = eventService.findEvent(id);
		model.addAttribute("event", event);
		
		return "showEvent.jsp";
	}
  
  
  @RequestMapping("/events/{id}/edit")
	public String editEvent(@PathVariable("id") Long id, Model model, 
			HttpSession session) {
		
//		if(session.getAttribute("userId") ==null) {
//			return "redirect:/home";
//		}
		
		//
		Event event = eventService.findEvent(id);
		if(session.getAttribute("userId") != event.getHost().getId()) {
			return "redirect:/events";
		}
		
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
//		Event event = eventService.findEvent(id);
		model.addAttribute("event", event);
		
		return "editEvent.jsp";
	}
	
	@RequestMapping(value="/events/{id}", method=RequestMethod.PUT)
	public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result,
			HttpSession session, Model model, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			List<User> users = userService.findAll();
			model.addAttribute("users", users);
			
			model.addAttribute("event", event);
			
			return "editEvent.jsp";
		}
		else {
			User host = userService.findUserById((Long) session.getAttribute("userId"));
			event.setHost(host);
			
			eventService.updateEvent(event);
			return "redirect:/events";
		}
	}
  
	//Join
//	@RequestMapping("/events/{id}/join", method=RequestMethod.POST)
//	public String joinEvent(@)
//		event 
	
	
	
//	public String addProduct(@RequestParam("product") Long prodId, @PathVariable("id") Long id) {
//		Product product = prodService.findProduct(prodId);
//		product.addCategory(catService.findCategory(id));
//		prodService.updateProduct(product);
	
	
	
  	@RequestMapping("/events/{id}/delete")
	public String deleteEvent(@PathVariable("id") Long id) {
		eventService.deleteEvent(id);
		return "redirect:/events";
	}
}
