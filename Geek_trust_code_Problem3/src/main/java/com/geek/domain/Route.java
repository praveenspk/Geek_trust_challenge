package com.geek.domain;

/**Geek_trust_code
 * @author Praveen
 * @version 1.0 
 * @since Aug 26, 2019
 */
public final class Route {

	private final String pathName;
	private final Long pathDistance;
	private final Long pathHoles;
	private final Integer maxPermissibleSpeed;
	private final String source;
	private final String destination;

	public Route(String pathName, Long pathDistance, Long pathHoles, Integer maxPermissibleSpeed, String source,
			String destination) {
		super();
		this.pathName = pathName;
		this.pathDistance = pathDistance;
		this.pathHoles = pathHoles;
		this.maxPermissibleSpeed = maxPermissibleSpeed;
		this.source = source;
		this.destination = destination;
	}

	public String getPathName() {
		return pathName;
	}

	public Long getPathDistance() {
		return pathDistance;
	}

	public Long getPathHoles() {
		return pathHoles;
	}

	public Integer getMaxPermissibleSpeed() {
		return maxPermissibleSpeed;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	@Override
	public String toString() {
		return "Route [pathName=" + pathName + ", pathDistance=" + pathDistance + ", pathHoles=" + pathHoles
				+ ", maxPermissibleSpeed=" + maxPermissibleSpeed + ", source=" + source + ", destination=" + destination
				+ "]";
	}

}
