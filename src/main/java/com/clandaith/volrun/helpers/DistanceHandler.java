package com.clandaith.volrun.helpers;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.Unit;

public class DistanceHandler {
	private static final Logger LOGGER = Logger.getLogger(DistanceHandler.class);

	public static void main1(String[] args) {
		DistanceHandler.getLatLng("1874 s 900 e, BOUNTIFUL, UT, 84010");

		Double lat1 = 40.8894d;
		Double lat2 = 40.7608d;
		Double long1 = -111.862676d;
		Double long2 = -111.8910d;

		LOGGER.info("BD Distance: " + DistanceHandler.getDistanceViaGoogleAPI(lat1, lat2, long1, long2));
		LOGGER.info(DistanceHandler.getDistanceViaMath(lat1, long1, lat2, long2));
	}

	public static BigDecimal getDistanceViaGoogleAPI(Double latitude1, Double latitude2, Double longitude1, Double longitude2) {
		BigDecimal distance = BigDecimal.ZERO;
		try {
			String[] origins = { latitude1.toString() + "," + longitude1.toString() };
			String[] destinations = { latitude2.toString() + "," + longitude2.toString() };

			DistanceMatrixApiRequest req = DistanceMatrixApi.getDistanceMatrix(
							new GeoApiContext().setApiKey(System.getenv("GOOGLE_API_KEY")), origins, destinations).units(Unit.IMPERIAL);
			DistanceMatrix dm = req.await();

			LOGGER.info("Dest: " + dm.destinationAddresses[0]);
			LOGGER.info("Orgs: " + dm.originAddresses[0]);

			for (DistanceMatrixRow row : dm.rows) {
				for (DistanceMatrixElement e : row.elements) {
					LOGGER.info(e.distance);
					LOGGER.info((e.distance.inMeters / 1000) * .62137119);

					distance = new BigDecimal(((e.distance.inMeters / 1000) * .62137119)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error getting distance via Google API", e);
		}

		return distance;
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

	public static BigDecimal getDistanceViaMath(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
						* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		return new BigDecimal(dist).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
