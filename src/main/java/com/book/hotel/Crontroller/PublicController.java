package com.book.hotel.Crontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController {

	@GetMapping("/")
	public String home() {
		return "public/index";
	}

	@GetMapping("/about-us")
	public String aboutus() {
		return "public/about-us";
	}

	@GetMapping("/blog")
	public String blog() {
		return "public/blog";
	}

	@GetMapping("/contact")
	public String contact() {
		return "public/contact";
	}

	@GetMapping("/elements")
	public String elements() {
		return "public/elements";
	}

	@GetMapping("/rooms")
	public String rooms() {
		return "public/rooms";
	}

	@GetMapping("/services")
	public String services() {
		return "public/services";
	}

}
