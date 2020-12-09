package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoApplication {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="everybody") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/about")
	public String about(Model model) {
		return "about";
	}

}