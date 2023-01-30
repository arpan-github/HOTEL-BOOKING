package com.book.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.hotel.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
