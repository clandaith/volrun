package com.clandaith.volrun.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.services.DemoService;
import com.clandaith.volrun.services.TournamentService;
import com.clandaith.volrun.services.UserRoleService;
import com.clandaith.volrun.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger LOGGER = Logger.getLogger(AdminController.class);

	@Autowired
	UserService userService;

	@Autowired
	DemoService demoService;

	@Autowired
	TournamentService tournamentService;

	@Autowired
	UserRoleService userRoleService;

	@RequestMapping("/index")
	public String index(Model model) {
		LOGGER.info("index");
		return "admin/index";
	}

	@RequestMapping("/addviewfiles")
	public String addViewFiles(Model model) {
		LOGGER.info("addViewFiles");
		return "admin/addViewFiles";
	}

	@RequestMapping("/currentmembers")
	public String currentMembers(Model model) {
		LOGGER.info("currentMembers");
		model.addAttribute("users", userService.getAllUsers());

		return "admin/currentMembers";
	}

	@RequestMapping("/deletemember/{id}")
	public String deleteMember(@PathVariable Integer id, Model model) {
		LOGGER.info("deleteMember");
		User user = userService.getUserById(id);
		userService.deleteUser(id);
		model.addAttribute("message", "User " + user.getFirstName() + " " + user.getLastName() + " has been deleted.");

		return "admin/currentMembers";
	}

	@RequestMapping("/viewmember/{id}")
	public String viewMember(@PathVariable Integer id, Model model) {
		LOGGER.info("viewMember");
		model.addAttribute("", userService.getUserById(id));

		return "admin/currentMembers";
	}

	@RequestMapping("/demorequests")
	public String demoRequests(Model model) {
		LOGGER.info("demoRequests");

		return "admin/demoRequests";
	}

	@RequestMapping("/membershiprequests")
	public String membershipRequests(Model model) {
		LOGGER.info("membershipRequests");

		return "admin/membershipRequests";
	}

	@RequestMapping("/scheduleddemos")
	public String scheduledDemos(Model model) {
		LOGGER.info("scheduledDemos");
		model.addAttribute("demos", demoService.getAllUncompletedDemos());
		model.addAttribute("lookupType", "Scheduled");

		return "admin/demoList";
	}

	@RequestMapping("/completeddemos")
	public String completedDemos(Model model) {
		LOGGER.info("completedDemos");
		model.addAttribute("demos", demoService.getAllCompletedDemos());
		model.addAttribute("lookupType", "Completed");

		return "admin/demoList";
	}

	@RequestMapping("/completedtournaments")
	public String completedTournaments(Model model) {
		LOGGER.info("completedTournaments");
		model.addAttribute("tournaments", tournamentService.getAllCompletedTournaments());
		model.addAttribute("lookupType", "Completed");

		return "admin/tournamentList";
	}

	@RequestMapping("/scheduledtournaments")
	public String scheduledTournaments(Model model) {
		LOGGER.info("scheduledTournaments");
		model.addAttribute("tournaments", tournamentService.getAllUncompletedTournaments());
		model.addAttribute("lookupType", "Scheduled");

		return "admin/tournamentList";
	}

	@RequestMapping("/tournament/scheduled/{id}")
	public String scheduledTournament(@PathVariable Integer id, Model model) {
		LOGGER.info("scheduledTournament");
		model.addAttribute("tournament", tournamentService.getTournament(id));

		return "admin/viewTournament";
	}

	@RequestMapping("/demo/scheduled/{id}")
	public String scheduledDemo(@PathVariable Integer id, Model model) {
		LOGGER.info("scheduledDemo");
		model.addAttribute("demo", demoService.getDemo(id));

		return "admin/viewDemo";
	}

	@RequestMapping("/sendmessage")
	public String sendMessage(Model model) {
		LOGGER.info("sendMessage");

		return "admin/sendMessage";
	}
}
