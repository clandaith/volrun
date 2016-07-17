package com.clandaith.volrun.helpers;

import org.apache.log4j.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class DistanceHandler {
	private static final Logger LOGGER = Logger.getLogger(DistanceHandler.class);

	public static void main1(String[] args) {
		DistanceHandler.getLatLng("1874 s 900 e, BOUNTIFUL, UT, 84010");
		DistanceHandler.getLatLng("1874 South 900 East, BOUNTIFUL, UT, 84010");
		DistanceHandler.getLatLng("1874 s 900 e, BOUNTIFUL, UTah, 84010");
		DistanceHandler.getLatLng("1874 south 900 e, UafdasdT");
	}

	public static LatLng getLatLng(String addressString) {
		try {
			GeocodingApiRequest gar = GeocodingApi.geocode(new GeoApiContext().setApiKey(System.getenv("GOOGLE_API_KEY")),
							addressString);
			GeocodingResult[] results = gar.await();

			if (results.length > 0) {
				LOGGER.info(results[0].geometry.location.lat + " :: " + results[0].geometry.location.lng);

				return results[0].geometry.location;
			}
		} catch (Exception e) {
			LOGGER.error("Error", e);
		}

		return null;
	}

	public static Double distance(Double latitude1, Double latitude2, Double longitude1, Double longitude2) {
		final int R = 6371; // Radius of the earth

		Double latDistance = Math.toRadians(latitude2 - latitude1);
		Double lonDistance = Math.toRadians(longitude2 - longitude1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(latitude1))
						* Math.cos(Math.toRadians(latitude2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		distance = Math.pow(distance, 2);
		distance = distance * 1.609344 * 1000; // convert to miles

		return Math.sqrt(distance);
	}
}
