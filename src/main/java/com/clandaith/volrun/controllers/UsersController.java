package com.clandaith.volrun.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clandaith.volrun.services.FileService;
import com.clandaith.volrun.services.UserService;

@Controller
public class UsersController {
	private static final Logger LOGGER = Logger.getLogger(UsersController.class);

	@Autowired
	FileService fileService;

	@Autowired
	UserService userService;

	@RequestMapping("/users/index")
	public String usersIndex(Model model) {
		LOGGER.info("usersIndex");
		// We can load any specific messages here

		return "users/index";
	}

	@RequestMapping("/users/viewfiles")
	public String viewFiles(Model model) {
		LOGGER.info("viewFiles");
		model.addAttribute("files", fileService.getAll());

		return "users/viewfiles";
	}

	@RequestMapping("/users/localusers")
	public String localUsers(Model model) {
		LOGGER.info("localUsers");
		model.addAttribute("users", userService.getAllUsers());

		return "users/localusers";
	}
}
