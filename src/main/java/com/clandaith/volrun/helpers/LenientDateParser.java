package com.clandaith.volrun.helpers;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LenientDateParser extends PropertyEditorSupport {
	private static final Logger LOGGER = Logger.getLogger(LenientDateParser.class);
	private static final List<String> formats = new ArrayList<String>();

	private final String outputFormat;

	static {
		formats.add("yyyy-MM-dd");
		formats.add("hh:mmaa");
	}

	public LenientDateParser(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text)) return;

		DateTime dt = null;
		for (String format : formats) {
			try {
				dt = DateTime.parse(text, DateTimeFormat.forPattern(format));

				break;
			} catch (Exception e) {
			}
		}
		if (dt != null) {
			setValue(dt.toDate());
		} else {
			LOGGER.error("Error finding date/time format with text: '" + text + "'");
		}
	}

	@Override
	public String getAsText() {
		Date date = (Date)getValue();

		if (date == null) return "";

		DateTimeFormatter f = DateTimeFormat.forPattern(outputFormat);

		return f.print(date.getTime());
	}
}
