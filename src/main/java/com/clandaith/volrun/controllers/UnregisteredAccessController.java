package com.clandaith.volrun.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.services.UserRoleService;
import com.clandaith.volrun.services.UserService;

@Controller
public class UnregisteredAccessController {
	private final Logger LOGGER = LogManager.getLogger(UnregisteredAccessController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserRoleService userRoleService;

	@RequestMapping("/requestmembership")
	public String userForm(Model model) {
		model.addAttribute("user", new User());
		return "requestmembership";
	}

	@RequestMapping(value = "/requestmembership", method = RequestMethod.POST)
	public String saveUser(User user) {
		LOGGER.info("Creating a new user: " + user.getUsername());

		BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
		LOGGER.info("Password: " + user.getPassword() + " :: " + bcryptEncoder.encode(user.getPassword()));
		user.setPassword(bcryptEncoder.encode(user.getPassword()));

		user.setEnabled(false);
		userService.saveUser(user);

		// UserRole userRole = new UserRole();
		// userRole.setUsername(user.getUsername());
		// userRole.setRole(ROLE.ROLE_USER);
		// userRoleService.saveUserRole(userRole);

		LOGGER.info("User and role created: " + user.getId() + " :: no role yet"); // + userRole.getId());

		return "redirect:/home?requestmembership";
	}
}
