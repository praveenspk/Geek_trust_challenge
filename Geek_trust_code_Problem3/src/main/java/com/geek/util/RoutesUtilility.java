package com.geek.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geek.domain.Result;
import com.geek.domain.Route;
import com.geek.domain.Vehicle;
import com.geek.domain.Weather;

/**
 * Geek_trust_code
 * 
 * @author Praveen
 * @version 1.0 TODO
 * @since Aug 26, 2019
 */
public class RoutesUtilility {

	public static final Weather sunnyWeather = new Weather(Weather.sunny, -10);
	public static final Weather rainyWeather = new Weather(Weather.rainy, 20);
	public static final Weather windyWeather = new Weather(Weather.windy, 0);

	public static final Vehicle superCar = new Vehicle(new Weather[] { sunnyWeather, rainyWeather, windyWeather },
			"car", 20, 3);
	public static final Vehicle tuktuk = new Vehicle(new Weather[] { sunnyWeather, rainyWeather }, "tuktuk", 12, 1);
	public static final Vehicle bike = new Vehicle(new Weather[] { sunnyWeather, windyWeather }, "bike", 10, 2);

	public final static Vehicle[] vehicles = new Vehicle[] { superCar, bike, tuktuk };

	// For Silk Dorb to Hallitharam travel distance
	private static Weather caluateWeatherForVehicles(String todayWeather, Vehicle vehicle) {
		Weather currentweather = null;
		for (Weather weather : vehicle.getWeather()) {
			if (weather.getWeatherName().trim().equalsIgnoreCase(todayWeather)) {
				currentweather = weather;
				break;
			}
		}

		return currentweather;
	}

	public static List<Result> calculateBestRoute(Vehicle[] vehicles, Route[] routes, String todaysWeather,
			String source, String destination) {

		List<Result> resultList = new ArrayList<Result>();

		for (Route route : routes) {

			if (!(route.getSource().equalsIgnoreCase(source) && route.getDestination().equalsIgnoreCase(destination)))
				continue;

			int maxPermissibleSpeed = route.getMaxPermissibleSpeed();
			long distance = route.getPathDistance();
			long pothHolesCount = route.getPathHoles();
			int minTravelTime = -1;
			String bestVehicleName = "";
			String currentRoute = "";

			for (Vehicle vehicle : vehicles) {
				Weather currentweather = null;

				for (Weather weather : vehicle.getWeather()) {
					if (weather.getWeatherName().trim().equalsIgnoreCase(todaysWeather)) {
						currentweather = weather;
						break;
					}
				}
				if (currentweather != null) {
					int changeInPothHoles = currentweather.getPercentageChangeInNumberOfPathHoles();
					int vehicleSpeed = vehicle.getVechileSpeed() > maxPermissibleSpeed ? maxPermissibleSpeed
							: vehicle.getVechileSpeed();
					String vehicleName = vehicle.getVechileType();
					int timeTakenToTravelPothhole = vehicle.getVechilePathHoleTime();

					int totalTimeInMinutes = (int) (((float) distance / (float) vehicleSpeed) * 60
							+ (pothHolesCount + (pothHolesCount * changeInPothHoles / 100))
									* timeTakenToTravelPothhole);

					if (minTravelTime == -1 || minTravelTime > totalTimeInMinutes) {
						minTravelTime = totalTimeInMinutes;
						bestVehicleName = vehicleName;
						currentRoute = route.getPathName();
					}
				}
			}

			Result resultRoute = new Result(minTravelTime, bestVehicleName, currentRoute);
			resultList.add(resultRoute);
		}

		Collections.sort(resultList);
		return resultList;
	}

	// For multiple Destinations
	// Silk Dorb to Hallitharam and RK Puram
	public static final List<Result> calculateBestRouteForMultipleDestination(Vehicle[] vehicles, Route[] routes,
			String todaysWeather) {

		List<Result> resultList = new ArrayList<Result>();
		Map<String, Result> orbitX = new HashMap<>();
		Map<String, Result> orbit3 = new HashMap<>();
		Map<String, Result> orbit4 = new HashMap<>();

		Result finalResult1 = null;
		Result finalResult2 = null;
		for (Vehicle vehicle : vehicles) {

			Weather currentweather = evaluateCurrentWeatherForVehicle(todaysWeather, vehicle);

			if (currentweather == null)
				continue;

			int changeInPothHoles = currentweather.getPercentageChangeInNumberOfPathHoles();
			String vehicleName = vehicle.getVechileType();
			int timeTakenToTravelPothhole = vehicle.getVechilePathHoleTime();
			int minTravelTime = -1;
			String bestVehicleName = "";
			String currentRoute = "";
			String src = "";
			String dest = "";
			for (int i = 0; i < 2; i++) {
				Route route = routes[i];
				src = route.getSource();
				dest = route.getDestination();
				int maxPermissibleSpeed = route.getMaxPermissibleSpeed();
				long distance = route.getPathDistance();
				int vehicleSpeed = vehicle.getVechileSpeed() > maxPermissibleSpeed ? maxPermissibleSpeed
						: vehicle.getVechileSpeed();
				long pothHolesCount = route.getPathHoles();

				int totalTimeInMinutes = (int) (((float) distance / (float) vehicleSpeed) * 60
						+ (pothHolesCount + (pothHolesCount * changeInPothHoles / 100)) * timeTakenToTravelPothhole);

				if (minTravelTime == -1 || minTravelTime > totalTimeInMinutes) {
					minTravelTime = totalTimeInMinutes;
					bestVehicleName = vehicleName;
					currentRoute = route.getPathName();
				}
			}
			Result result = new Result(minTravelTime, bestVehicleName, currentRoute);
			result.setSrc(src);
			result.setDest(dest);
			orbitX.put(vehicleName, result);

			Route route = routes[2];
			src = route.getSource();
			dest = route.getDestination();
			int maxPermissibleSpeed = route.getMaxPermissibleSpeed();
			long distance = route.getPathDistance();
			int vehicleSpeed = vehicle.getVechileSpeed() > maxPermissibleSpeed ? maxPermissibleSpeed
					: vehicle.getVechileSpeed();
			long pothHolesCount = route.getPathHoles();

			int totalTimeInMinutes = (int) (((float) distance / (float) vehicleSpeed) * 60
					+ (pothHolesCount + (pothHolesCount * changeInPothHoles / 100)) * timeTakenToTravelPothhole);
			src = route.getSource();
			dest = route.getDestination();
			result = new Result(totalTimeInMinutes, vehicle.getVechileType(), route.getPathName());
			result.setSrc(src);
			result.setDest(dest);
			orbit3.put(vehicleName, result);

			route = routes[3];
			src = route.getSource();
			dest = route.getDestination();
			maxPermissibleSpeed = route.getMaxPermissibleSpeed();
			distance = route.getPathDistance();
			vehicleSpeed = vehicle.getVechileSpeed() > maxPermissibleSpeed ? maxPermissibleSpeed
					: vehicle.getVechileSpeed();
			pothHolesCount = route.getPathHoles();

			totalTimeInMinutes = (int) (((float) distance / (float) vehicleSpeed) * 60
					+ (pothHolesCount + (pothHolesCount * changeInPothHoles / 100)) * timeTakenToTravelPothhole);
			src = route.getSource();
			dest = route.getDestination();
			result = new Result(totalTimeInMinutes, vehicle.getVechileType(), route.getPathName());
			result.setSrc(src);
			result.setDest(dest);
			orbit4.put(vehicleName, result);

			// Orbit 3 + Orbit 4

			Result result3 = orbit3.get(vehicle.getVechileType());
			Result result4 = orbit4.get(vehicle.getVechileType());
			Result resultX = orbitX.get(vehicle.getVechileType());
			if (result3.getTotalTimeTakenToTravel()
					+ result4.getTotalTimeTakenToTravel() < resultX.getTotalTimeTakenToTravel()
							+ result4.getTotalTimeTakenToTravel()) {
				if (finalResult1 == null && finalResult2 == null || result3.getTotalTimeTakenToTravel()
						+ result4.getTotalTimeTakenToTravel() < finalResult1.getTotalTimeTakenToTravel()
								+ finalResult2.getTotalTimeTakenToTravel()) {
					finalResult1 = result3;
					finalResult2 = result4;
				}
			} else if (finalResult1 == null && finalResult2 == null || resultX.getTotalTimeTakenToTravel()
					+ result4.getTotalTimeTakenToTravel() < finalResult1.getTotalTimeTakenToTravel()
							+ finalResult2.getTotalTimeTakenToTravel()) {
				finalResult1 = resultX;
				finalResult2 = result4;
			}
		}

		resultList.add(finalResult1);
		resultList.add(finalResult2);
		return resultList;
	}

	private static Weather evaluateCurrentWeatherForVehicle(String todaysWeather, Vehicle vehicle) {
		Weather currentweather = null;
		for (Weather weather : vehicle.getWeather()) {
			if (weather.getWeatherName().trim().equalsIgnoreCase(todaysWeather)) {
				currentweather = weather;
				break;
			}
		}
		return currentweather;
	}

}
