package com.decoder.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decoder.demo.model.Users;
import com.decoder.demo.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	public Users register(Users user)
	{
		return userRepo.save(user);
	}
	
}
