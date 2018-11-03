package com.company.cab.routeplan;

public class Route {

	private Integer cabId;
	private Integer[] teammember_id;
	private String route;
	private double cost;
	
	Route(){
		
	}
	
	public Route(Integer cabId, Integer[] teammember_id, String route, double cost) {
		super();
		this.cabId = cabId;
		this.teammember_id = teammember_id;
		this.route = route;
		this.cost = cost;
	}
	public Integer getCabId() {
		return cabId;
	}
	public void setCabId(Integer cabId) {
		this.cabId = cabId;
	}
	public Integer[] getTeammember_id() {
		return teammember_id;
	}
	public void setTeammember_id(Integer[] teammember_id) {
		this.teammember_id = teammember_id;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	
	
	
}
