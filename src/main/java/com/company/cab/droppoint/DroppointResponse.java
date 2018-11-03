package com.company.cab.droppoint;

public class DroppointResponse {
	
	public String droppoint_from;
	public String droppoint_to;
	public double distance;
	
	public DroppointResponse() {
		
	}
	
	public DroppointResponse(String droppoint_from, String droppoint_to, double distance) {
		super();
		this.droppoint_from = droppoint_from;
		this.droppoint_to = droppoint_to;
		this.distance = distance;
	}

	public String getDroppoint_from() {
		return droppoint_from;
	}

	public void setDroppoint_from(String droppoint_from) {
		this.droppoint_from = droppoint_from;
	}

	public String getDroppoint_to() {
		return droppoint_to;
	}

	public void setDroppoint_to(String droppoint_to) {
		this.droppoint_to = droppoint_to;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
	
	

}
