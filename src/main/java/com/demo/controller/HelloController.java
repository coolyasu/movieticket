package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/helloworld")
//	@RequestMapping(value="/" ,method=RequestMethod.GET)
	public String helloWorld() {
		return "Hello World";
	}
}
