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

	@RequestMapping("/admin/addviewfiles")
	public String addViewFiles(Model model) {
		LOGGER.info("addViewFiles");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/addViewFiles";
	}

	@RequestMapping("/admin/completeddemos")
	public String completedDemos(Model model) {
		LOGGER.info("completedDemos");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/completedDemos";
	}

	@RequestMapping("/admin/completedtournaments")
	public String completedTournaments(Model model) {
		LOGGER.info("completedTournaments");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/completedTournaments";
	}

	@RequestMapping("/admin/currentmembers")
	public String currentMembers(Model model) {
		LOGGER.info("currentMembers");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/currentMembers";
	}

	@RequestMapping("/admin/demorequests")
	public String demoRequests(Model model) {
		LOGGER.info("demoRequests");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/demoRequests";
	}

	@RequestMapping("/admin/membershiprequests")
	public String membershipRequests(Model model) {
		LOGGER.info("membershipRequests");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/membershipRequests";
	}

	@RequestMapping("/admin/scheduleddemos")
	public String scheduledDemos(Model model) {
		LOGGER.info("scheduledDemos");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/scheduledDemos";
	}

	@RequestMapping("/admin/scheduledtournaments")
	public String scheduledTournaments(Model model) {
		LOGGER.info("scheduledTournaments");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/scheduledTournaments";
	}

	@RequestMapping("/admin/sendmessage")
	public String sendMessage(Model model) {
		LOGGER.info("sendMessage");
		// model.addAttribute("users", userService.getAllUsers());

		return "admin/sendMessage";
	}
}
