package com.book.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.hotel.Entity.BookHotel;

public interface BookHotelRepository extends JpaRepository<BookHotel, Integer> {

}
