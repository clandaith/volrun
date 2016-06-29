package com.clandaith.volrun.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clandaith.volrun.services.UserRoleService;
import com.clandaith.volrun.services.UserService;

@Controller
public class AdminController {
	private static final Logger LOGGER = Logger.getLogger(AdminController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;

	@RequestMapping("/admin/index")
	public String index(Model model) {
		LOGGER.info("index");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/index";
	}
}
