package com.geek.domain;

/**
 * Geek_trust_code
 * 
 * @author Praveen
 * @version 1.0
 * @since Aug 26, 2019
 * 
 * 
 */
public class Result implements Comparable<Result> {

	private final Integer totalTimeTakenToTravel;
	private final String vehicleName;
	private final String routeName;
	private String src;
	private String dest;

	public static final String getFormattedOutput(final Result[] results) {
		StringBuilder result = new StringBuilder();
		result.append(String.format("Vehicle %s", results[0].getVehicleName()));
		result.append(String.format(" to %s via %s ", results[0].getDest(), results[0].getRouteName()));
		for (int i = 1; i < results.length; i++) {
			result.append(String.format("and %s via %s",
					results[i].getDest().equalsIgnoreCase(results[i - 1].getDest()) ? results[i].getSrc()
							: results[i].getDest(),
					results[i].getRouteName()));
		}
		return result.toString();

	}

	public Result(Integer totalTimeTakenToTravel, String vehicleName, String routeName) {
		super();
		this.totalTimeTakenToTravel = totalTimeTakenToTravel;
		this.vehicleName = vehicleName;
		this.routeName = routeName;
		this.src = src;
		this.dest = dest;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public Integer getTotalTimeTakenToTravel() {
		return totalTimeTakenToTravel;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public String getRouteName() {
		return routeName;
	}

	public int compareTo(Result res) {

		return this.getTotalTimeTakenToTravel() - res.getTotalTimeTakenToTravel();
	}

	@Override
	public String toString() {

		return new StringBuilder("King Shan and Queen Anga should use a ").append(vehicleName).append(" and take the ")
				.append(routeName).append(" Route").toString();
	}

}
