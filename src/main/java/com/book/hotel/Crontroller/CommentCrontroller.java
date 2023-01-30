package com.book.hotel.Crontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.book.hotel.Entity.Comment;
import com.book.hotel.Entity.User;
import com.book.hotel.Helper.Massage;
import com.book.hotel.Repository.UserRepository;

@Controller
@RequestMapping("/comment")
public class CommentCrontroller {

	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private CommentRepository commentRepository;

	@PostMapping("/savecomment")
	public void saveComment(@ModelAttribute Comment comment,@RequestParam("hotelcomment")String hotelcomment, @RequestParam("postimage") MultipartFile file,
			Principal principal, HttpSession session) throws IOException {

		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);

			if (file.isEmpty()) {
				comment.setCommentImg("default.png");
			} else {
				comment.setCommentImg(file.getOriginalFilename());

				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			}
			comment.setUser(user);
			comment.setDate(new Date());
			user.getComment().add(comment);
			this.userRepository.save(user);
			

			System.out.println(comment);
			session.setAttribute("massage", new Massage("Your form is added !!...", "success"));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("massage", new Massage("somthing went wrong!!plese try agen ...", "danger"));
		}
		
	}
}
