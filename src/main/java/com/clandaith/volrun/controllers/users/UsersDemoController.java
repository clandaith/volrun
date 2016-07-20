package com.clandaith.volrun.controllers.users;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clandaith.volrun.entities.Demo;
import com.clandaith.volrun.helpers.ControllerHelper;
import com.clandaith.volrun.services.DemoService;
import com.clandaith.volrun.services.StoreService;

@Controller
@RequestMapping("/users/demo")
public class UsersDemoController extends ControllerHelper {
	private static final Logger LOGGER = Logger.getLogger(UsersDemoController.class);

	@Autowired
	DemoService demoService;

	@Autowired
	StoreService storeService;

	@RequestMapping("/scheduler")
	public String buildNewDemo(Model model, HttpSession session) {
		LOGGER.info("buildNewDemo");

		model.addAttribute("demo", new Demo());
		model.addAttribute("userId", getUser(session).getId());
		model.addAttribute("storeList", storeService.getAll());

		return "users/demoScheduler";
	}

	@RequestMapping(value = "/scheduler", method = RequestMethod.POST)
	public String saveNewDemo(@Valid Demo demo, BindingResult bindingResult, Model model, HttpSession session) {
		LOGGER.info("saveNewDemo");

		if (bindingResult.hasErrors()) {
			LOGGER.info("bindingResult.hasErrors()");

			for (ObjectError obj : bindingResult.getAllErrors()) {
				LOGGER.info(obj.toString());
			}

			return "users/demoScheduler";
		} else {

			LOGGER.info("store id: " + demo.getStoreId());

			demo.setDemoStore(storeService.getStore(demo.getStoreId()));
			demo.setDemoUser(getUser(session));
			demo.setUserId(getUser(session).getId());
			demoService.saveDemo(demo);

			model.addAttribute("scheduledDemo", demo);

			return "redirect:/users/index?demoEntered";
		}
	}

	@RequestMapping("/reporter")
	public String showUncompletedDemos(Model model, HttpSession session) {
		LOGGER.info("showUncompletedDemos");

		model.addAttribute("uncompletedDemos", demoService.getUncompletedDemosByUser(getUser(session).getId()));

		return "users/demoReporter";
	}

	@RequestMapping(value = "/reporter", method = RequestMethod.POST)
	public String saveCompletedDemo(@Valid Demo demo, BindingResult bindingResult, Model model, HttpSession session) {
		LOGGER.info("saveCompletedDemo");

		if (bindingResult.hasErrors()) {
			LOGGER.info("bindingResult.hasErrors()");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				LOGGER.info(obj.toString());
			}

			return "users/demoReporter";
		} else {
			Demo originalDemo = (Demo)session.getAttribute("originalDemo");

			originalDemo.setCompleted(demo.getCompleted());
			originalDemo.setPostNotes(demo.getPostNotes());
			originalDemo.setNumberOfDemos(demo.getNumberOfDemos());
			originalDemo.setNumberOfPeople(demo.getNumberOfPeople());
			originalDemo.setStoreResponse(demo.getStoreResponse());

			demoService.saveDemo(originalDemo);

			session.removeAttribute("originalDemo");
			model.addAttribute("completedDemo", originalDemo);

			return "redirect:/users/index?demoCompleted";
		}
	}

	@RequestMapping("/reporter/{demoId}")
	public String getSpecificUncompletedDemo(@PathVariable Integer demoId, Model model, HttpSession session) {
		LOGGER.info("getSpecificUncompletedDemo: " + demoId);

		Demo demo = demoService.getDemo(demoId);

		if (demo == null) {
			model.addAttribute("uncompletedDemos", demoService.getUncompletedDemosByUser(getUser(session).getId()));
			model.addAttribute("errorMessage", "That demo doesn't exist.  Please select another demo.");
		} else if (demo.getUserId().equals(getUser(session).getId())) {
			demo.setCompleted(true);
			session.setAttribute("originalDemo", demo);
			model.addAttribute("demo", demo);
		} else {
			model.addAttribute("uncompletedDemos", demoService.getUncompletedDemosByUser(getUser(session).getId()));
			model.addAttribute("errorMessage", "This demo wasn't scheduled by you.  Please select another demo.");
		}

		return "users/demoReporter";
	}
}
