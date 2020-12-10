package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.models.Student;
import com.example.demo.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoApplication {

	@Autowired
	private Repository repository;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="everybody") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/gallery")
	public String gallery(Model model) {
		Iterable<Student> url = repository.findAll();
		model.addAttribute("url", url);
		return "gallery";
	}

	@GetMapping("/image/add")
	public String imageAdd() {
		return "image-add";
	}

	@PostMapping("/image/add")
	public String imagePostAdd(@RequestParam String enteredURL) {
		repository.save(new Student(enteredURL));
		return "redirect:/gallery";
	}

}