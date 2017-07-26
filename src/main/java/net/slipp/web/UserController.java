package net.slipp.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	//private List<User> users=new ArrayList<User>();
	
	@Autowired
	private UserRepository ur;
	
	@PostMapping ("")
	public String create(User user){
		
		System.out.println(user);
		//users.add(user);
		ur.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users", ur.findAll());
		return "/user/list";
	}

	@GetMapping("/form")
	public String form(){
		return "/user/form";
	}
	
	@GetMapping("/{id}/form")
	public String updateform(@PathVariable Long id,Model model){
		model.addAttribute("user", ur.findOne(id) );
		return "/user/updateform";
	}
	
	@PostMapping("/{id}")
	public String update(@PathVariable Long id,User newUser){
		User user=ur.findOne(id);
		user.update(newUser);
		ur.save(user); //실제 db 업데이트
		return "redirect:/users";
	}
	
	@GetMapping("/loginform")
	public String loginform(){
		return "user/login";
	}
	
	@PostMapping("login")
	public String login(String userid, String password,HttpSession session){
		User user=ur.findByuserid(userid);
		if(user==null){
			return "redirect:/users/loginform";
			
		}
		if(!password.equals(user.getPassword())){
			
			return "redirect:/users/loginform";
		}
		session.setAttribute("user", user);
		return "redirect:/";
		}
	
}
