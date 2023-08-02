package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	private String name;
	private String location;
	private String language;
	private String comment;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@PostMapping("/dashboard")
	public String getData(@ModelAttribute("formulario") Formulario formulario) {
		name = formulario.getName();
		location = formulario.getLocation();
		language = formulario.getLanguage();
		comment = formulario.getComment();
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("name", name);
		model.addAttribute("location", location);
		model.addAttribute("language",language);
		model.addAttribute("comment", comment);
		return "dashboard";
	}
}