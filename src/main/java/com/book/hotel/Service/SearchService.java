package com.book.hotel.Service;

import java.util.List;

import com.book.hotel.Entity.Search;

public interface SearchService {

	void saveSearchHotel(Search search);

	List<Search> getAllSearchHotel();
}
