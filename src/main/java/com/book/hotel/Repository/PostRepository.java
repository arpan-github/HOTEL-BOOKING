package com.book.hotel.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.hotel.Entity.Catagory;
import com.book.hotel.Entity.Post;
import com.book.hotel.Entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCatagory(Catagory catagory);

	@Query("from Post as d where d.user.usreId =:usreId")
	public Page<Post> findPostByUser(@Param("usreId") int usreId, Pageable p);

	@Query("SELECT p FROM Post p WHERE CONCAT(p.CityName, p.postname, p.hotelLocation) LIKE %?1%")
	public List<Post> findPostByLocation(@Param("location") String location);

	@Query(value = "select * from booking.post where rome_price < 2000 and hotel_location = 'Kolkata' ", nativeQuery = true)
	public List<Post> findPostByPrice();

	@Query(value = "select * from booking.post where city_name = 'Kolkata'", nativeQuery = true)
	public List<Post> findPostByKolkata();

}
