package com.book.hotel.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.hotel.Entity.Catagory;

public interface CatagoryReposerory extends JpaRepository<Catagory, Integer> {

	@Query("from Catagory as d where d.user.usreId =:usreId")
	public Page<Catagory> findCatagoryByUser(@Param("usreId") int usreId, Pageable p);

}
