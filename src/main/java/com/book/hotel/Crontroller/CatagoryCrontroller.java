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

import com.book.hotel.Entity.Catagory;
import com.book.hotel.Entity.User;
import com.book.hotel.Helper.Massage;
import com.book.hotel.Repository.CatagoryReposerory;
import com.book.hotel.Repository.UserRepository;

@Controller
@RequestMapping("/Catagory")
public class CatagoryCrontroller {

	@Autowired
	private CatagoryReposerory catagoryReposerory;

	@Autowired
	private UserRepository userRepository;

//	@GetMapping("/addCatagory/{usreId}")
//	public String addcatagory(Model model,@PathVariable("usreId") Integer usreId) {
//
//		Optional<User> getone = this.userRepository.findById(usreId);
//		User user = getone.get();
//		model.addAttribute("user", user);
//
//		// create model attribute to bind form data
//		Catagory catagory = new Catagory();
//		model.addAttribute("catagory", catagory);
//		return "admin/AddCatagory";
//	}
	
	@GetMapping("/addCatagory")
	public String addcatagory(Model model) {

		// create model attribute to bind form data
		Catagory catagory = new Catagory();
		model.addAttribute("catagory", catagory);
		return "admin/AddCatagory";
	}

	@PostMapping("/saveCatagory")
	public String savePost(@ModelAttribute Catagory catagory, @RequestParam("Categoryimage") MultipartFile file,
			Principal principal, HttpSession session) throws IOException {

		try {
			String name = principal.getName();
			User user = this.userRepository.getUserByUserName(name);

			if (file.isEmpty()) {
				catagory.setImage("default.png");
			} else {
				catagory.setImage(file.getOriginalFilename());

				File savefile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			}
			catagory.setUser(user);
			catagory.setDate(new Date());
			user.getCatagory().add(catagory);
			this.userRepository.save(user);

			System.out.println(catagory);
			session.setAttribute("massage", new Massage("Your form is added !!...", "success"));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("massage", new Massage("somthing went wrong!!plese try agen ...", "danger"));
		}
		return "redirect:/Catagory/viewAllCatagory/0";
	}

	@GetMapping("/viewAllCatagory/{page}")
	public String showAllCatagory(@PathVariable("page") Integer page, Model model, Principal principal) {

		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);

		// pageble
		PageRequest pagenumber = PageRequest.of(page, 3);

		Page<Catagory> catagory = this.catagoryReposerory.findCatagoryByUser(user.getUsreId(), pagenumber);
		model.addAttribute("allCatagory", catagory);

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", catagory.getTotalPages());

		return "admin/viewAll_Catagory";
	}

}
