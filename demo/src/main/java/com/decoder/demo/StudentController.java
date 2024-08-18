package com.decoder.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
	
	private List<Student> students = new ArrayList<Student>(
										List.of(new Student(1,"Ravindra",86),
												new Student(2,"Mayur",88), 
												new Student(3,"Ashutosh",77)
												)			
									);
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		return students;
	} 
	
	@GetMapping("/token")
	public CsrfToken getToken(HttpServletRequest req)
	{
		return (CsrfToken)req.getAttribute("_csrf");
	}
	
	@PostMapping("/students")
	public String addStudent(@RequestBody Student student)
	{
		boolean isSave = students.add(student);
		
		if(isSave)
			return "Student Added Successfully";
		else 
			return "Student Not added!!!";
	}

}
