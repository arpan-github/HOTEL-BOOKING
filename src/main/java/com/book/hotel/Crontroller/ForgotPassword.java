package com.book.hotel.Crontroller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.hotel.Entity.User;
import com.book.hotel.Repository.UserRepository;
import com.book.hotel.ServiceImpl.EmailService;

@Controller
@RequestMapping("/forgot")
public class ForgotPassword {

	Random random = new Random(1000);

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/forgot-password")
	public String Forgot() {

		return "public/forgot-password";
	}

	@PostMapping("/send-otp")
	public String sendotp(@RequestParam("username") String username, HttpSession session) {

		System.out.println(username);

		int otp = random.nextInt(9999);
		System.out.println(otp);

		// write to send otp

		String subject = "OTP From The Palatin web";

		String message = "<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
				+ "<div style=\"margin:50px auto;width:70%;padding:20px 0\">"
				+ "<div style=\"border-bottom:1px solid #eee\">"
				+ "<a href=\"\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">The Palatin web</a>"
				+ "</div>" + "<p style=\"font-size:1.1em\">Hi,</p>"
				+ "<p>Forget your <b>The Palatin web</b> password?Use the following OTP to complete your Sign in procedures. OTP is valid for 5 minutes.</p>"
				+ "<p>Your OTP(one time password) is mentioned below.Please don't share OTP with anyone .</p>"
				+ "<h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">"
				+ otp + "</h2>" + "<p style=\"font-size:0.9em;\">The Palatin<br />Thank you</p>"
				+ " <hr style=\"border:none;border-top:1px solid #eee\" />"
				+ "<div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
				+ "<p>Your Brand Inc</p>" + "<p>1600 Amphitheatre Parkway</p>" + "<p>California</p>" + "</div>"
				+ "</div>" + "</div>";

		String to = username;

		boolean flag = this.emailService.sendEmail(subject, message, to);

		if (flag) {

			session.setAttribute("myotp", otp);
			session.setAttribute("email", username);
			return "public/verify-otp";
		} else {

			session.setAttribute("message", "Check email id ..");

			return "public/forgot-password";
		}
	}

	@PostMapping("/verify-otp")
	public String verifyotp(@RequestParam("otp") int otp, HttpSession session) {

		int myotp = (int) session.getAttribute("myotp");
		System.out.println("your otp" + otp);
		System.out.println("my otp" + myotp);

		String email = (String) session.getAttribute("username");

		if (myotp == otp) {
			User user = this.userRepository.getUserByUserName(email);

			System.out.println(user);
			return "public/password-chang-form";
		} else {

			return "public/verify-otp";
		}
	}

	@PostMapping("/chang-password")
	public String changpassword(@RequestParam("newPasswordone") String newPasswordone, HttpSession session) {

		String email = (String) session.getAttribute("username");
		User user = this.userRepository.getUserByUserName(email);
		user.setPassword(this.bCryptPasswordEncoder.encode(newPasswordone));
		this.userRepository.save(user);
		return "redirect:/log/loging";

	}
}
