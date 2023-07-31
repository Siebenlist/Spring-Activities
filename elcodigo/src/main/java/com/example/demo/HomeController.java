package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	
	@GetMapping("")
	public String homePage() {
		return "home";
	}

	@PostMapping("/verify")
	public String verify(@RequestParam(value="code") String code, RedirectAttributes redirectAttributes) {
		if(!code.equals("bushido")) {
			redirectAttributes.addFlashAttribute("error", "Codigo incorrecto, intenta otra vez :).");
			return "redirect:/";
		}
		return "redirect:/success";
	}
	
	@GetMapping("/success")
	public String createError() {
		return "success"; 
	}
}
