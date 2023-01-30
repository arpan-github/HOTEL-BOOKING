package com.book.hotel.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.book.hotel.Entity.Catagory;
import com.book.hotel.Repository.CatagoryReposerory;
import com.book.hotel.Service.CatagoryService;

@Service
public class CatagoryServiceImpl implements CatagoryService {

	@Autowired
	private CatagoryReposerory catagoryReposerory;

	@Override
	public Page<Catagory> getAllCatagory(Pageable p) {
		return catagoryReposerory.findAll(p);
	}

	@Override
	public void saveCatagory(Catagory catagory) {
		this.catagoryReposerory.save(catagory);
	}

	@Override
	public Catagory getCatagoryById(Integer catagoryId) {
		Optional<Catagory> optional = catagoryReposerory.findById(catagoryId);
		Catagory catagory = null;
		if (optional.isPresent()) {
			catagory = optional.get();
		} else {
			throw new RuntimeException(" medicine not found for id :: " + catagoryId);
		}
		return catagory;
	}

	@Override
	public void deleteCatagoryById(Integer catagoryId) {
		this.catagoryReposerory.deleteById(catagoryId);
	}

	@Override
	public Page<Catagory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.catagoryReposerory.findAll(pageable);
	}

	/*
	 * @Override public List<Catagory> listAll(String keyword) { if (keyword !=
	 * null) { return catagoryReposerory.search(keyword); } return
	 * catagoryReposerory.findAll(); }
	 */
}
