package com.book.hotel.Crontroller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.hotel.Entity.Post;
import com.book.hotel.Entity.Search;
import com.book.hotel.Entity.User;
import com.book.hotel.Repository.PostRepository;
import com.book.hotel.Repository.SearchRepository;
import com.book.hotel.Repository.UserRepository;
import com.book.hotel.Service.PostService;

@Controller
@RequestMapping("/find")
public class SearchCrontroller {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostService postServic;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SearchRepository searchRepository;

	@GetMapping("/search")
	public String savePost(@ModelAttribute Search search, @RequestParam("keyword") String keyword, Principal principal,
			HttpSession session, Model model) throws IOException {

		List<Post> hotelList = this.postRepository.findPostByLocation(keyword);
		model.addAttribute("hotelList", hotelList);

		String name = principal.getName();
		User user = this.userRepository.getUserByUserName(name);

		search.setUser(user);
		search.setSerchingTime(new Date());
		user.getSearch().add(search);
		this.userRepository.save(user);

		return "user/show-search-post";
	}

	@GetMapping("/view-single-post/{postId}/{page}")
	public String showOne(@PathVariable("postId") Integer postId, @PathVariable("page") Integer page, Model model) {

		Optional<Post> getone = this.postRepository.findById(postId);
		Post post = getone.get();
		model.addAttribute("post", post);

		Optional<Search> one = this.searchRepository.findById(postId);
		Search search = one.get();
		model.addAttribute("search", search);

		PageRequest pagenumber = PageRequest.of(page, 50);

		Page<Post> allPost = this.postServic.getAllPost(pagenumber);
		model.addAttribute("allPost", allPost);
		model.addAttribute("title", "show all Post");

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allPost.getTotalPages());

		return "user/single_Post_User";
	}

//	@GetMapping("/filter")
//	public String PriceFilte(@ModelAttribute Search search, @RequestParam("priceFilter") String priceFilter,
//			Principal principal, HttpSession session, Model model) throws IOException {
//
//		List<Post> hotelList = this.postRepository.findPostByPrice(priceFilter);
//		model.addAttribute("hotelList", hotelList);
//
//		String name = principal.getName();
//		User user = this.userRepository.getUserByUserName(name);
//
//		search.setUser(user);
//		search.setSerchingTime(new Date());
//		user.getSearch().add(search);
//		this.userRepository.save(user);
//
//		return "filter_Price";
//	}

}
