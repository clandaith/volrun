package com.clandaith.volrun.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clandaith.volrun.services.UserRoleService;
import com.clandaith.volrun.services.UserService;

@Controller
public class UsersController {
	private static final Logger LOGGER = Logger.getLogger(UsersController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;

	@RequestMapping("/users/index")
	public String index(Model model) {
		LOGGER.info("allUsers");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/index";
	}

	@RequestMapping("/users/demoscheduler")
	public String demoSchedule(Model model) {
		LOGGER.info("demoSchedule");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/demoScheduler";
	}

	@RequestMapping("/users/demoreporter")
	public String demoReport(Model model) {
		LOGGER.info("demoReport");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/demoReporter";
	}

	@RequestMapping("/users/tournamentscheduler")
	public String tournamentSchedule(Model model) {
		LOGGER.info("tournamentSchedule");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/tournamentScheduler";
	}

	@RequestMapping("/users/tournamentreporter")
	public String tournamentReport(Model model) {
		LOGGER.info("tournamentReport");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/tournamentReporter";
	}

	@RequestMapping("/users/viewfiles")
	public String viewFiles(Model model) {
		LOGGER.info("viewFiles");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/viewfiles";
	}

	@RequestMapping("/users/localusers")
	public String localUsers(Model model) {
		LOGGER.info("localUsers");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/localusers";
	}
}
