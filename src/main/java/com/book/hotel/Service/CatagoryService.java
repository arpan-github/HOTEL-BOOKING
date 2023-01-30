package com.book.hotel.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.book.hotel.Entity.Catagory;

public interface CatagoryService {

	Page<Catagory> getAllCatagory(Pageable p);

	void saveCatagory(Catagory catagory);

	Catagory getCatagoryById(Integer catagoryId);

	void deleteCatagoryById(Integer catagoryId);

	Page<Catagory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	//spublic List<Catagory> listAll(String keyword);
}
