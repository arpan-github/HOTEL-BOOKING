package com.book.hotel.Crontroller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.hotel.Entity.User;
import com.book.hotel.Helper.Massage;
import com.book.hotel.Repository.UserRepository;
import com.book.hotel.Service.UserService;

@Controller
@RequestMapping("/log")
public class Loging {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/sing-up")
	public String singup(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "public/singup";
	}

	@GetMapping("/loging")
	public String loging(Model model) {

		return "public/loging";
	}

	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		if (user.getEmail() == null) {

			session.setAttribute("message", new Massage("Successfilly Register !!", "alart-danger"));
			return "redirect:/log/sing-up";
		} else {
			user.setRoll("ROLE_USER");
			user.setEnabled(true);
			user.setDate(new Date());
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			userService.userData(user);

			session.setAttribute("message", new Massage("Successfilly Register !!", "alart-success"));
			return "redirect:/log/sing-up";
		}
	}

	@GetMapping("/view-single-user/{usreId}")
	public String showOne(@PathVariable("usreId") Integer usreId, Model m) {

		Optional<User> getone = this.userRepository.findById(usreId);
		User user = getone.get();
		m.addAttribute("user", user);

		return "admin/showHotelPost";
	}
	
	@GetMapping("/profile/view-user-profile/{usreId}")
	public String showprofile(@PathVariable("usreId") Integer usreId, Model m) {

		Optional<User> getone = this.userRepository.findById(usreId);
		User user = getone.get();
		m.addAttribute("user", user);

		return "user/profilePage";
	}
}
