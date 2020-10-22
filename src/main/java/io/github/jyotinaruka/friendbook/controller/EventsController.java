package io.github.jyotinaruka.friendbook.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.jyotinaruka.friendbook.model.Event;
import io.github.jyotinaruka.friendbook.model.User;
import io.github.jyotinaruka.friendbook.service.EventService;
import io.github.jyotinaruka.friendbook.service.UserService;

@Controller
public class EventsController {
	  @Autowired
	  private UserService userService;

	  @Autowired
	  private EventService eventService;

	  
	  //Scott's code
	  @RequestMapping("/events")
	  public String events(@ModelAttribute("user") User user, Model model,
			  @ModelAttribute("event") Event event,
			  HttpSession session) {
//		if(session.getAttribute("userId") == null) {
//				return "redirect:/home";
//		}
	  
		
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		List<Event> events = eventService.allEvents();
		model.addAttribute("events", events);
		
		Long userId = (Long) session.getAttribute("user_id");
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
//		  if(session.getAttribute("userId") == null) {
//				return "redirect:/home";
//			}
		  
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
			  User host = userService.findUserById((Long) session.getAttribute("user_id"));
			  event.setHost(host);
				
			  
			  eventService.submitEvent(event);
			  return "redirect:/events";
		  }
	  }
	  
	  @RequestMapping("/events/{id}")
		public String showEvent(@PathVariable("id") Long id, Model model, 
				HttpSession session) {
//			if(session.getAttribute("userId") ==null) {
//				return "redirect:/login";
//			}
			Event event = eventService.findEvent(id);
			model.addAttribute("event", event);
			
			return "showEvent.jsp";
		}
	  
	  
	  @RequestMapping("/events/{id}/edit")
		public String editEvent(@PathVariable("id") Long id, Model model, 
				HttpSession session) {
			
//			if(session.getAttribute("userId") ==null) {
//				return "redirect:/home";
//			}
			
			//
			Event event = eventService.findEvent(id);
//			if(session.getAttribute("user_id") != event.getHost().getId()) {
//				return "redirect:/events";
//			}
			
			
			List<User> users = userService.findAll();
			model.addAttribute("users", users);
			
//			Event event = eventService.findEvent(id);
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
				User host = userService.findUserById((Long) session.getAttribute("user_id"));
				event.setHost(host);
				
				eventService.updateEvent(event);
				return "redirect:/events";
			}
		}
	  
		//Join
		@RequestMapping("/events/{id}/join")
		public String addAttentee(@PathVariable("id") Long id, 
			@ModelAttribute("event") Event event, HttpSession session, BindingResult result){
//				User user = userService.findAll();
				Event e = eventService.findEvent(id);
				List<User> attendees = e.getAttendees();
				
				User user = userService.findUserById((Long) session.getAttribute("user_id"));
				attendees.add(user);	
				
				e.setAttendees(attendees);
				
				eventService.updateEvent(e);
				
//				userService.updateUser(user);
		
				return "redirect:/events";
					
		}

		
		
		
//		public String addProduct(@RequestParam("product") Long prodId, @PathVariable("id") Long id) {
//			Product product = prodService.findProduct(prodId);
//			product.addCategory(catService.findCategory(id));
//			prodService.updateProduct(product);
		
		
		
	  	@RequestMapping("/events/{id}/delete")
		public String deleteEvent(@PathVariable("id") Long id) {
			eventService.deleteEvent(id);
			return "redirect:/events";
		}
}
