package net.slipp.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class HomeController {
	@GetMapping("/")
	public String welcome(){
		
		return "index";
	}
}
