package com.book.hotel.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.hotel.Entity.User;
import com.book.hotel.Repository.UserRepository;
import com.book.hotel.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void userData(User user) {
		this.userRepository.save(user);
	}
}
