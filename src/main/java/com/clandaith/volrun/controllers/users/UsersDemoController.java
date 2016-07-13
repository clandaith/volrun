package com.clandaith.volrun.controllers.users;

import java.util.Date;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clandaith.volrun.entities.Demo;
import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.helpers.LenientDateParser;
import com.clandaith.volrun.services.DemoService;
import com.clandaith.volrun.services.StoreService;
import com.clandaith.volrun.services.UserService;

@Controller
public class UsersDemoController {
	private static final Logger LOGGER = Logger.getLogger(UsersDemoController.class);

	@Autowired
	DemoService demoService;

	@Autowired
	UserService userService;

	@Autowired
	StoreService storeService;

	@RequestMapping("/users/demoscheduler")
	public String demoSchedule(Model model) {
		LOGGER.info("demoSchedule");

		model.addAttribute("demo", new Demo());
		model.addAttribute("userId", getUser().getId());
		model.addAttribute("storeList", storeService.getAll());

		return "users/demoScheduler";
	}

	@RequestMapping(value = "/users/demoscheduler", method = RequestMethod.POST)
	public String demoScheduleSaver(@Valid Demo demo, BindingResult bindingResult) {
		LOGGER.info("demoScheduleSaver");

		if (bindingResult.hasErrors()) {
			LOGGER.info("bindingResult.hasErrors()");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				LOGGER.info(obj.toString());
			}
		} else {
			demoService.saveDemo(demo);
		}

		return "users/demoScheduler";
	}

	@RequestMapping("/users/demoreporter")
	public String demoReporter(Model model) {
		LOGGER.info("demoReport");

		return "users/demoReporter";
	}

	@RequestMapping("/users/demoreporter/{demoId}")
	public String demoReporterSpecific(@PathVariable Integer demoId, Model model) {
		LOGGER.info("demoReport");

		return "users/demoReporter";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new LenientDateParser("yyyy-MM-dd"));
	}

	private User getUser() {
		User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		LOGGER.info("Email address: " + user.getEmailAddress());
		return user;
	}
}
