package com.book.hotel.Crontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.book.hotel.Entity.Post;
import com.book.hotel.Entity.User;
import com.book.hotel.Helper.Massage;
import com.book.hotel.Repository.PostRepository;
import com.book.hotel.Repository.UserRepository;

@Controller
@RequestMapping("/post")
public class PostCrontroller {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println(userName);

		User user = userRepository.getUserByUserName(userName);
		System.out.println(user);
		model.addAttribute("user", user);
	}

	@GetMapping("/category/{catagoryId}/posts")
	public String savePostPage(@PathVariable("catagoryId") Integer catagoryId, Model m) {

		return "admin/AddPost";
	}

	@PostMapping("/saveHotel")
	public String savePost(@ModelAttribute Post post, @RequestParam("postimage") MultipartFile file,
			Principal principal, HttpSession session) throws IOException {

		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);

			if (file.isEmpty()) {
				post.setImage("default.png");
			} else {
				post.setImage(file.getOriginalFilename());

				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			}
			post.setUser(user);
			post.setDate(new Date());
			user.getPosts().add(post);
			this.userRepository.save(user);

			System.out.println(post);
			session.setAttribute("massage", new Massage("Your form is added !!...", "success"));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("massage", new Massage("somthing went wrong!!plese try agen ...", "danger"));
		}
		return "redirect:/post/show-all-hotel/0";
	}

	@GetMapping("/show-all-hotel/{page}")
	public String showAllHotel(@PathVariable("page") Integer page, Model model, Principal principal) {

		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);

		// pageble
		PageRequest pagenumber = PageRequest.of(page, 3);

		Page<Post> post = this.postRepository.findPostByUser(user.getUsreId(), pagenumber);
		model.addAttribute("post", post);

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", post.getTotalPages());

		return "admin/showHotelPost";
	}

	@GetMapping("/delete-post-hotel/{postId}")
	public String deletePost(@PathVariable("postId") Integer postId, Model model, HttpSession session) {

		Optional<Post> postone = this.postRepository.findById(postId);
		Post post = postone.get();

		this.postRepository.delete(post);

		session.setAttribute("message", new Massage("post Delete successfully!! ", "success"));
		return "redirect:/post/show-all-hotel/0";
	}

	@GetMapping("/update-post-hotel/{postId}")
	public String updatePost(@PathVariable("postId") Integer postId, Model model) {

		Post post = this.postRepository.findById(postId).get();
		model.addAttribute("post", post);

		return "admin/updatePostPage";
	}

	@PostMapping("/update-hotel")
	public String updateHotel(@ModelAttribute Post post, @RequestParam("postimage") MultipartFile file, Model model,
			HttpSession session, Principal principal) {
		try {

			// old post
			Post oldpost = this.postRepository.findById(post.getPostId()).get();

			if (!file.isEmpty()) {

				// delete file

				File deletefile = new ClassPathResource("static/img").getFile();
				File file1 = new File(deletefile, oldpost.getImage());
				file1.delete();

				// update file
				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

				post.setImage(file.getOriginalFilename());
			} else {

				post.setImage(oldpost.getImage());
			}

			User user = this.userRepository.getUserByUserName(principal.getName());

			post.setUser(user);
			post.setDate(new Date());

			this.postRepository.save(post);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/post/show-all-hotel/0";
	}
}
