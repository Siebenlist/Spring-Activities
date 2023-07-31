package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("")
	public String homePage(HttpSession session, Model model) {
	    Integer count = (Integer) session.getAttribute("count");

	    if (count == null) {
	        count = 0;

	    } else {
	    	count++;
	    }


	    session.setAttribute("count", count);
	    
	    model.addAttribute("count", count);

	    return "home";
	}
	
	@GetMapping("/hub")
	public String secPage(HttpSession session, Model model) {
	    Integer count = (Integer) session.getAttribute("count");

	    if (count == null) {
	        count = 0;
	    } else {
	    	count += 2;
	    }


	    session.setAttribute("count", count);
	    
	    model.addAttribute("count", count);

	    return "hub";
	}
	
	@GetMapping("/counter")
	public String countPage() {
	    return "counting";
	}
}
