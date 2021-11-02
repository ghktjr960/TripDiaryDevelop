package com.tripdiary.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeForm() {
		return "write";
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.GET)
	public String diary() {
		return "diary";
	}

	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}

	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPage() {
		return "myPage";
	}
	
	
	
}
