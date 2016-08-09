package com.clandaith.volrun.helpers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.clandaith.volrun.entities.Store;
import com.clandaith.volrun.entities.User;
import com.clandaith.volrun.services.StoreService;
import com.clandaith.volrun.services.UserService;

public class ControllerHelper {
	private static final Logger LOGGER = Logger.getLogger(ControllerHelper.class);

	@Autowired
	UserService userService;

	@Autowired
	StoreService storeService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new LenientDateParser("yyyy-MM-dd"));
	}

	public User getUser(HttpSession session) {
		User user = null;
		// if (session.getAttribute("onlineUser") != null) {
		// user = (User)session.getAttribute("onlineUser");
		// LOGGER.info("User is in the session");
		// } else {
		user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		// session.setAttribute("onlineUser", user);
		// LOGGER.info("User not in session.  Adding to session.");
		// }

		LOGGER.info("Email address: " + user.getEmailAddress());
		return user;
	}

	public Store getStore(Integer storeId, HttpSession session) {
		Store store = null;
		LOGGER.info("Getting store: store-" + storeId);

		// if (session.getAttribute("store-" + storeId) != null) {
		// store = (Store)session.getAttribute("store-" + storeId);
		// LOGGER.info("Store is in the session.  Returning that.");
		// } else {
		store = storeService.getStore(storeId);
		// LOGGER.info("Store is not in the session.  Saving it out.");
		// }

		LOGGER.info("Returning store: " + store.getStoreName());

		return store;
	}
}
