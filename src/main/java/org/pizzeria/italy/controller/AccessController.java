package org.pizzeria.italy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AccessController {

	@GetMapping
	public String getHome() {

		return "home";
	}

	@GetMapping("/user")
	public String getUser() {

		return "home";
	}

	@GetMapping("/admin")
	public String getAdmin() {

		return "admin";
	}

	@GetMapping("/useradmin")
	public String getUserAdmin() {

		return "useradmin";
	}
}