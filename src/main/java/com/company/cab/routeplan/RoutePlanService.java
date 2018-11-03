package com.company.cab.routeplan;

import com.company.cab.cabs.Cab;

public interface RoutePlanService {
	
	public RoutePlan getRoutePlan();
	public double calculateRouteDistance(Cab cab);
	public double getRouteCost(Cab cab);

}
