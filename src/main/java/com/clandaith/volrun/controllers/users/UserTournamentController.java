package com.clandaith.volrun.controllers.users;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clandaith.volrun.entities.Tournament;
import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.helpers.ControllerHelper;
import com.clandaith.volrun.services.StoreService;
import com.clandaith.volrun.services.TournamentService;
import com.clandaith.volrun.services.UserService;

@Controller
@RequestMapping("/users/tournament")
public class UserTournamentController extends ControllerHelper {
	private static final Logger LOGGER = Logger.getLogger(UserTournamentController.class);

	@Autowired
	TournamentService tournamentService;

	@Autowired
	UserService userService;

	@Autowired
	StoreService storeService;

	@RequestMapping("/scheduler")
	public String buildNewTournament(Model model) {
		LOGGER.info("buildNewTournament");

		model.addAttribute("tournament", new Tournament());
		model.addAttribute("userId", getUser().getId());
		model.addAttribute("storeList", storeService.getAll());

		return "users/tournamentScheduler";
	}

	@RequestMapping(value = "/scheduler", method = RequestMethod.POST)
	public String saveNewTournament(@Valid Tournament tournament, BindingResult bindingResult, HttpSession session) {
		LOGGER.info("saveNewTournament");

		if (bindingResult.hasErrors()) {
			LOGGER.info("bindingResult.hasErrors()");

			for (ObjectError obj : bindingResult.getAllErrors()) {
				LOGGER.info(obj.toString());
			}

			return "users/tournamentScheduler";
		} else {
			LOGGER.info("store id: " + tournament.getStoreId());

			tournament.setTournamentStore(storeService.getStore(tournament.getStoreId()));
			tournament.setTournamentUser(getUser(session));
			tournament.setUserId(getUser(session).getId());
			tournamentService.saveTournament(tournament);
		}
		return "users/tournamentScheduler";
	}

	public String getUsersTournaments(Model model) {
		LOGGER.info("getUsersTournaments");

		List<Tournament> tournaments = tournamentService.getUncompletedTournamentsByUser(getUser().getId());
		model.addAttribute("userId", getUser().getId());
		model.addAttribute("tournaments", tournaments);

		return "users/tournamentReporter";
	}

	@RequestMapping(path = "/reporter", method = RequestMethod.PUT)
	public String saveCompletedTournament(Model model) {
		LOGGER.info("saveCompletedTournament");
		// model.addAttribute("users", userService.getAllUsers());

		return "users/tournamentReporter";
	}

	@RequestMapping("/reporter/{tournamentId}")
	public String getUncompletedTournament(@PathVariable Integer tournamentId, Model model, HttpSession session) {
		LOGGER.info("getUncompletedTournament");

		Tournament tournament = tournamentService.getTournament(tournamentId);

		if (tournament.getUserId().equals(getUser().getId())) {
			session.setAttribute("originalTournament", tournament);
			model.addAttribute("tournament", tournament);
		} else {

		}

		return "users/tournamentReporter";
	}

	private User getUser() {
		User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		LOGGER.info("Email address: " + user.getEmailAddress());
		return user;
	}
}
