package com.geek.domain;

/**Geek_trust_code
 * @author Praveen
 * @version 1.0 
 * @since Aug 26, 2019
 */
public final class Vehicle {

	private final Weather[] weather;
	private final String vechileType;
	private final Integer vechileSpeed;
	private final Integer vechilePathHoleTime;

	public Vehicle(Weather[] weather, String vechileType, Integer vechileSpeed, Integer vechilePathHoleTime) {
		super();
		this.weather = weather;
		this.vechileType = vechileType;
		this.vechileSpeed = vechileSpeed;
		this.vechilePathHoleTime = vechilePathHoleTime;
	}

	public Weather[] getWeather() {
		return weather;
	}

	public String getVechileType() {
		return vechileType;
	}

	public Integer getVechileSpeed() {
		return vechileSpeed;
	}

	public Integer getVechilePathHoleTime() {
		return vechilePathHoleTime;
	}

}
