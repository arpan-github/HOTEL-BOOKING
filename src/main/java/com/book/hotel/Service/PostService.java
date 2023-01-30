package com.book.hotel.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.book.hotel.Entity.Post;

public interface PostService {

	Post createPost(Post post, Integer catagoryId);

	Post updatePost(Post post, Integer postId);

	void deletePost(Integer postId);

	Page<Post> getAllPost(Pageable p);

	Post getPostById(Integer postId);

	List<Post> getAllPostByCatagoryId(Integer catagoryId);

	List<Post> getPostByUser(Long usreId);

}
