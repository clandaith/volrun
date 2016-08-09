package com.clandaith.volrun.controllers.users;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clandaith.volrun.entities.Store;
import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.services.StoreService;
import com.clandaith.volrun.services.UserService;

@Controller
public class UserAddStore {
	private static final Logger LOGGER = Logger.getLogger(UserAddStore.class);

	@Autowired
	UserService userService;

	@Autowired
	StoreService storeService;

	@RequestMapping("/users/addstore")
	public String addStore(Model model) {
		LOGGER.info("addStore");

		model.addAttribute("userId", getUser().getId());

		return "users/addStore";
	}

	@RequestMapping(path = "/users/addstore", method = RequestMethod.POST)
	public String saveStore(@Valid Store store, BindingResult bindingResult) {
		LOGGER.info("saveStore");

		if (bindingResult.hasErrors()) {
			LOGGER.info("bindingResult.hasErrors()");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				LOGGER.info(obj.toString());
			}
		} else {
			storeService.saveStore(store);
		}

		return "users/addStore";
	}

	private User getUser() {
		User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		LOGGER.info("Email address: " + user.getEmailAddress());
		return user;
	}
}
