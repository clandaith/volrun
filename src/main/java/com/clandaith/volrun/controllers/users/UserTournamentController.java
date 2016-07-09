package com.clandaith.volrun.controllers.users;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clandaith.volrun.entities.Tournament;
import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.services.TournamentService;
import com.clandaith.volrun.services.UserService;

@Controller
public class UserTournamentController {
	private static final Logger LOGGER = Logger.getLogger(UserTournamentController.class);

	@Autowired
	TournamentService tournamentService;

	@Autowired
	UserService userService;

	@RequestMapping("/users/tournamentscheduler")
	public String scheduleTournament(Model model) {
		LOGGER.info("scheduleTournament");

		model.addAttribute("tournament", new Tournament());
		model.addAttribute("userId", getUser().getId());

		return "users/tournamentScheduler";
	}

	@RequestMapping(value = "/users/tournamentscheduler", method = RequestMethod.POST)
	public String saveTournamentSchedule(Tournament tournament, BindingResult bindingResult) {
		LOGGER.info("saveTournamentSchedule");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/tournamentScheduler";
	}

	@RequestMapping("/users/tournaments")
	public String getTournaments(Model model) {
		LOGGER.info("getTournaments");
		model.addAttribute("userId", getUser().getId());

		return "users/tournamentReporter";
	}

	@RequestMapping("/users/tournamentreporter")
	public String tournamentReport(Model model) {
		LOGGER.info("tournamentReport");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/tournamentReporter";
	}

	@RequestMapping("/users/tournamentreporter/{tournamentId}")
	public String saveTournamentReport(@PathVariable Integer tournamentId, Model model) {
		LOGGER.info("saveTournamentReport");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/tournamentReporter";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	private User getUser() {
		User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		LOGGER.info("Email address: " + user.getEmailAddress());
		return user;
	}
}
