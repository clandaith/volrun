package com.clandaith.volrun.helpers;

import org.apache.log4j.Logger;

import com.clandaith.volrun.entities.Store;
import com.clandaith.volrun.entities.User;

public class AddressFormatter {
	private static final Logger LOGGER = Logger.getLogger(AddressFormatter.class);

	public static String formatStoreAddress(Store store) {
		StringBuilder builder = new StringBuilder();
		builder.append(formatField(store.getAddress1()));
		builder.append(formatField(store.getAddress2()));
		builder.append(formatField(store.getCity()));
		builder.append(formatField(store.getState()));
		builder.append(formatField(store.getZip()));
		builder.append(formatField(store.getCountry()));

		LOGGER.info(builder.toString());

		return builder.toString();
	}

	public static String formatUserAddress(User user) {
		StringBuilder builder = new StringBuilder();
		builder.append(formatField(user.getAddress1()));
		builder.append(formatField(user.getAddress2()));
		builder.append(formatField(user.getCity()));
		builder.append(formatField(user.getState()));
		builder.append(formatField(user.getZip()));
		builder.append(formatField(user.getCountry()));

		LOGGER.info(builder.toString());

		return builder.toString();
	}

	private static String formatField(String field) {
		if (field != null && field.trim().length() > 0) {
			return ", " + field.trim();
		} else {
			return "";
		}
	}
}
