package com.book.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.hotel.Entity.Search;

public interface SearchRepository extends JpaRepository<Search, Integer>{

	
}
