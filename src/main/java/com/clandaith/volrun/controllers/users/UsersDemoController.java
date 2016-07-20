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
	public String demoSchedule(Model model, HttpSession session) {
		LOGGER.info("demoSchedule");

		model.addAttribute("demo", new Demo());
		model.addAttribute("userId", getUser(session).getId());
		model.addAttribute("storeList", storeService.getAll());

		return "users/demoScheduler";
	}

	@RequestMapping(value = "/scheduler", method = RequestMethod.POST)
	public String demoScheduleSaver(@Valid Demo demo, BindingResult bindingResult, HttpSession session) {
		LOGGER.info("demoScheduleSaver");

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
		}

		return "users/demoScheduler";
	}

	@RequestMapping("/reporter")
	public String demoReporter(Model model, HttpSession session) {
		LOGGER.info("demoReport");

		model.addAttribute("uncompletedDemos", demoService.getUncompletedDemosByUser(getUser(session).getId()));

		return "users/demoReporter";
	}

	@RequestMapping(value = "/reporter", method = RequestMethod.POST)
	public String saveCompletedDemo(@Valid Demo demo, BindingResult bindingResult, HttpSession session) {
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
		}

		return "users/demoReporter";
	}

	@RequestMapping("/reporter/{demoId}")
	public String demoReporterSpecific(@PathVariable Integer demoId, Model model, HttpSession session) {
		LOGGER.info("demoReport");

		Demo demo = demoService.getDemo(demoId);

		if (demo.getUserId().equals(getUser(session).getId())) {
			session.setAttribute("originalDemo", demo);
			model.addAttribute("demo", demo);
		} else {

		}

		return "users/demoReporter";
	}

}
