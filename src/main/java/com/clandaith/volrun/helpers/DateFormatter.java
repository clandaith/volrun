package com.clandaith.volrun.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date> {
	private static final String defaultDateFormat = "yyyy-MM-dd HH:mm";

	@Override
	public String print(Date arg0, Locale arg1) {

		return new SimpleDateFormat(defaultDateFormat).format(arg0.getTime());
	}

	@Override
	public Date parse(String arg0, Locale arg1) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormat);
		Date date = sdf.parse(arg0);
		return date;
	}

}
