package com.clandaith.volrun.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clandaith.volrun.entities.Demo;
import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.services.DemoService;
import com.clandaith.volrun.services.UserService;

@Controller
public class UsersController {
	private static final Logger LOGGER = Logger.getLogger(UsersController.class);

	@Autowired
	DemoService demoService;

	@Autowired
	UserService userService;

	@RequestMapping("/users/index")
	public String index(Model model) {
		LOGGER.info("allUsers");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/index";
	}

	@RequestMapping("/users/demoscheduler")
	public String demoSchedule(Model model) {
		LOGGER.info("demoSchedule");

		LOGGER.info("Username: " + SecurityContextHolder.getContext().getAuthentication().getName());

		User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		LOGGER.info("Email address: " + user.getEmailAddress());

		model.addAttribute("demo", new Demo());
		model.addAttribute("userId", user.getId());

		return "users/demoScheduler";
	}

	@RequestMapping(value = "/users/demoscheduler", method = RequestMethod.POST)
	public String demoScheduleSaver(@Valid Demo demo, BindingResult bindingResult) {
		LOGGER.info("demoSchedule");

		// demoService.saveDemo(demo);

		if (bindingResult.hasErrors()) {
			LOGGER.info("bindingResult.hasErrors()");
			LOGGER.info(bindingResult.getAllErrors().toString());
		}

		return "users/demoScheduler";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
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
