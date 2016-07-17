package com.clandaith.volrun.helpers;

import org.apache.log4j.Logger;

import com.clandaith.volrun.entities.Store;
import com.clandaith.volrun.entities.User;

public class AddressFormatter {
	private static final Logger LOGGER = Logger.getLogger(AddressFormatter.class);

	public static String formatStoreAddress(Store store) {
		String address = store.getAddress1() + ", " + store.getAddress2() + ", " + store.getCity() + ", " + store.getState() + ", "
						+ store.getZip() + ", " + store.getCountry();
		LOGGER.info(address);

		return address;
	}

	public static String formatUserAddress(User user) {
		String address = user.getAddress1() + ", " + user.getAddress2() + ", " + user.getCity() + ", " + user.getState() + ", "
						+ user.getZip() + ", " + user.getCountry();
		LOGGER.info(address);

		return address;
	}
}
