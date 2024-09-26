package com.javabeans.vid03PP01.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class loginController {
	
	@PostMapping
	public String logIn(@RequestParam String userName, @RequestParam String password)
	{
		if(userName == "ravindra" && password=="pass@123")
		{
			return "Hello"+userName;			
		}
		else
		{
			return "Hello User";
		}
	}
	
}
