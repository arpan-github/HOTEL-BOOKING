package com.book.hotel.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.hotel.Entity.BookHotel;
import com.book.hotel.Repository.BookHotelRepository;
import com.book.hotel.Service.BookHotelService;

@Service
public class BookHotelServiceImpl implements BookHotelService {

	@Autowired
	private BookHotelRepository bookHotelRepository;

	@Override
	public void saveBookHotel(BookHotel bookHotel) {
		this.bookHotelRepository.save(bookHotel);
	}

}
