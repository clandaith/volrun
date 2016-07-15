package com.clandaith.volrun.helpers;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;

public class DistanceDeterminer {

	public static void main(String[] args) {
		GeoApiContext context = new GeoApiContext().setApiKey("");
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, "84010").await();
			for (int i = 0; i < results.length; i++) {

				Geometry geometry = results[i].geometry;

				System.out.println(geometry.location.lat + " :: " + geometry.location.lng);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Calculate distance between two points in latitude and longitude taking into account height difference. If you are not
	 * interested in height difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters el2 End altitude in meters
	 * 
	 * @returns Distance in Meters
	 */
	public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
		final int R = 6371; // Radius of the earth

		Double latDistance = Math.toRadians(lat2 - lat1);
		Double lonDistance = Math.toRadians(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
						* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);
		distance = distance * 1.609344 * 1000;
		return Math.sqrt(distance);
	}
}
