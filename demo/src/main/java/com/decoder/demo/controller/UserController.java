package com.decoder.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.decoder.demo.model.Users;
import com.decoder.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public Users register(@RequestBody Users user)
	{
		return userService.register(user);
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user)
	{
		return userService.login(user);
	}

}
