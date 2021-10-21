package com.tripdiary.web;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "main";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		return "main";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeForm() {
		return "write";
	}
	
	@RequestMapping(value = "/diary", method = RequestMethod.GET)
	public String diary() {
		return "diary";
	}
	
	@RequestMapping(value = "/pick", method = RequestMethod.GET)
	public String pick() {
		return "pick";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		return "about";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn() {
		return "signIn";
	}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String myPage() {
		return "myPage";
	}
	
	
	
}
