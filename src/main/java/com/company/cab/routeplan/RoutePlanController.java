package com.company.cab.routeplan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.cab.cabs.Cab;
import com.company.cab.cabs.CabRepository;
import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointNotFoundException;
import com.company.cab.droppoint.DroppointDistance;
import com.company.cab.droppoint.DroppointDistanceRepository;
import com.company.cab.droppoint.DroppointRel;

@RestController
public class RoutePlanController {
	
	@Autowired
	RoutePlanService routePlanService;
	
	@GetMapping("/route_plan")
	public RoutePlan getRoutePlan() {
		
		return routePlanService.getRoutePlan();
	}
	
	
}
