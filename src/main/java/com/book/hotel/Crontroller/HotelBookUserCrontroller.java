package com.book.hotel.Crontroller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.book.hotel.Entity.BookHotel;
import com.book.hotel.Entity.Post;
import com.book.hotel.Entity.User;
import com.book.hotel.Repository.PostRepository;
import com.book.hotel.Repository.UserRepository;
import com.book.hotel.Service.PostService;

@Controller
@RequestMapping("/booking")
public class HotelBookUserCrontroller {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostService postServic;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/show-hotel")
	public String Show() {

		return "user/show_hotel";
	}

	@GetMapping("/show-all-hotel-book/{page}")
	public String showAllHotel(@PathVariable("page") Integer page, Model model) {

		PageRequest pagenumber = PageRequest.of(page, 3);

		Page<Post> allPost = this.postServic.getAllPost(pagenumber);
		model.addAttribute("allPost", allPost);
		model.addAttribute("title", "show all Post");

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allPost.getTotalPages());
		return "user/showandbookhotel";
	}

	@GetMapping("/view-single-post/{postId}/{page}")
	public String showOne(@PathVariable("postId") Integer postId, @PathVariable("page") Integer page, Model model) {

		Optional<Post> getone = this.postRepository.findById(postId);
		Post post = getone.get();
		model.addAttribute("post", post);

		PageRequest pagenumber = PageRequest.of(page, 50);

		Page<Post> allPost = this.postServic.getAllPost(pagenumber);
		model.addAttribute("allPost", allPost);
		model.addAttribute("title", "show all Post");

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allPost.getTotalPages());

		return "user/single_Post_User";
	}

//------------------------------------------------------
	@GetMapping("/book-hotel/{postId}/{page}")
	public String bookhotel(@PathVariable("postId") Integer postId, @PathVariable("page") Integer page, Model model) {

		Optional<Post> getone = this.postRepository.findById(postId);
		Post post = getone.get();
		model.addAttribute("post", post);

		PageRequest pagenumber = PageRequest.of(page, 50);

		Page<Post> allPost = this.postServic.getAllPost(pagenumber);
		model.addAttribute("allPost", allPost);
		model.addAttribute("title", "show all Post");

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allPost.getTotalPages());

		return "user/book-hotel";
	}

	@PostMapping("/booking-hotel/{postId}")
	public String saveHotelBook(@PathVariable("postId") Integer postId, @ModelAttribute BookHotel bookHotel,
			Principal principal, HttpSession session, Model model, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("email") String email,
			@RequestParam("checkInDate") String checkInDate, @RequestParam("checkOutDate") String checkOutDate,
			@RequestParam("totalRoom") int totalRoom, @RequestParam("adults") int adults,
			@RequestParam("kids") int kids, @RequestParam("ac") String ac) throws IOException, ParseException {

		// post details
		Optional<Post> getone = this.postRepository.findById(postId);
		Post post = getone.get();
		model.addAttribute("post", post);

		// total room and total price
		int romePrice = post.getRomePrice();
		int tRoom = totalRoom;
		int roomandroomp = (romePrice * tRoom);
		model.addAttribute("totalroomandtotalprice", roomandroomp);

		// total room and parcentage price
		int percentage = (int) ((roomandroomp / 100) * 2.5);
		model.addAttribute("CGSTSGST", percentage);

		// total amount
		int total = (roomandroomp + percentage + percentage);
		model.addAttribute("totalamount", total);

		// pay amount
		int payamount = (int) ((total / 100) * 20);
		model.addAttribute("payamount", payamount);

		System.out.println("adults" + adults);
		System.out.println("kids" + kids);
		System.out.println(kids + adults + "total");
		System.out.println("ac" + ac);

		// if adults =3 and want 1 room
		if (totalRoom == 1 & adults == 3) {

			if (ac.isEmpty()) {

				int roomprice = post.getRomePrice();
				int oneparc = roomprice / 2;
				int price = roomprice + oneparc;
				
				int pricetRoom=(price*tRoom);
				model.addAttribute("totalroomandtotalprice", pricetRoom);
				
				int CGSTSGST = (int) ((pricetRoom / 100) * 2.5);
				model.addAttribute("CGSTSGST", CGSTSGST);
				
				int total1 = (pricetRoom + CGSTSGST + CGSTSGST);
				model.addAttribute("totalamount", total1);

				// pay amount
				int payamount1 = (int) ((total1 / 100) * 20);
				model.addAttribute("payamount", payamount1);
				
				System.out.println("total price" + pricetRoom);
			} else {

				int roomprice = post.getACRoomprice();
				int oneparc = roomprice / 2;
				int price = roomprice + oneparc;
				model.addAttribute("totalprice", price);

				System.out.println("total price" + price);
			}
		}
		// if adults =4 and want 1 room
		if (totalRoom == 1 & adults == 4) {

			if (ac.isEmpty()) {

				int roomprice = post.getRomePrice();
				int oneparc = roomprice * 2;
				int price = roomprice + oneparc;
				model.addAttribute("totalprice", price);

				System.out.println("total price" + price);
			} else {

				int roomprice = post.getACRoomprice();
				int oneparc = roomprice * 2;
				int price = roomprice + oneparc;
				model.addAttribute("totalprice", price);

				System.out.println("total price" + price);
			}
		}

		String name = principal.getName();
		User user = this.userRepository.getUserByUserName(name);

		bookHotel.setPost(post);
		bookHotel.setUser(user);
		bookHotel.setBookingtime(new Date());
		user.getBookHotel().add(bookHotel);
		this.userRepository.save(user);

		model.addAttribute("firstname", firstname);
		model.addAttribute("lastname", lastname);
		model.addAttribute("email", email);
		model.addAttribute("checkInDate", checkInDate);
		model.addAttribute("checkOutDate", checkOutDate);
		model.addAttribute("totalRoom", totalRoom);

		return "user/ShowBookingHotel";
	}
}
