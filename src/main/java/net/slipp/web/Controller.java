package net.slipp.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
	@GetMapping("/hello")
	public String welcome(String name,int age,Model model){
		System.out.println(name);
		model.addAttribute("name",name);
		model.addAttribute("age", age);
		return "welcome";
	}
}
