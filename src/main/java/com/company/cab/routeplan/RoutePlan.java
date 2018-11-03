package com.company.cab.routeplan;

import java.util.List;

public class RoutePlan {

	private List<Route> routes;
	private double totalcost;
	
	
	RoutePlan(){
		
	}
	
	public RoutePlan(List<Route> routes, double totalcost) {
		super();
		this.routes = routes;
		this.totalcost = totalcost;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	public double getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}
	
	
}
