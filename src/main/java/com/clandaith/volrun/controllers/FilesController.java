package com.clandaith.volrun.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FilesController {
	private static final Logger LOGGER = LogManager.getLogger(FilesController.class);

	@RequestMapping("/")
	public String listFiles(Model model) {
		LOGGER.info("we're here");

		return "files/index";
	}
}
