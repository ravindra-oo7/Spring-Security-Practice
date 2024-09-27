package com.javabeans.vid04Practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping("/greet")
    public String greeting(@RequestParam String name) {
        return "<h1>Hello " + name + "</h1>"; 
    }
}

