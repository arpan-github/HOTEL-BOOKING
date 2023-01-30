package com.book.hotel.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.hotel.Entity.Search;
import com.book.hotel.Repository.SearchRepository;
import com.book.hotel.Service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchRepository searchRepository;

	@Override
	public void saveSearchHotel(Search search) {
		this.searchRepository.save(search);
	}

	@Override
	public List<Search> getAllSearchHotel() {

		return searchRepository.findAll();
	}

}
