package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/date")
	public String datePage(Model model) {
        Date date = new Date();
        SimpleDateFormat newDate = new SimpleDateFormat("EEEE, dd 'de' MMMM, yyyy", new Locale("es"));
        String formattedDate = newDate.format(date);
        model.addAttribute("date", formattedDate);
        return "date";
	}
	
	@GetMapping("/time")
	public String timePage(Model model) {
		Date time = new Date();
		SimpleDateFormat newDate = new SimpleDateFormat("hh:mm a", new Locale("es"));
		String formattedTime = newDate.format(time);
		model.addAttribute("time", formattedTime);
		return "time";
	}
}
