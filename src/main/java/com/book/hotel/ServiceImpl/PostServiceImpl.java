package com.book.hotel.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.book.hotel.Entity.Catagory;
import com.book.hotel.Entity.Post;
import com.book.hotel.Repository.CatagoryReposerory;
import com.book.hotel.Repository.PostRepository;
import com.book.hotel.Service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private CatagoryReposerory catagoryReposerory;

	@Autowired
	private PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public Post createPost(Post post, Integer catagoryId) {

		Catagory catagory = this.catagoryReposerory.findById(catagoryId).orElseThrow();

		Post posts = new Post();
		posts.setDate(new Date());
		posts.setCatagory(catagory);

		Post newPost = this.postRepository.save(posts);

		return newPost;
	}

	@Override
	public Post updatePost(Post post, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Page<Post> getAllPost(Pageable p) {

		return postRepository.findAll(p);
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAllPostByCatagoryId(Integer catagoryId) {

		return null;
	}

	@Override
	public List<Post> getPostByUser(Long usreId) {
		// TODO Auto-generated method stub
		return null;
	}

}
