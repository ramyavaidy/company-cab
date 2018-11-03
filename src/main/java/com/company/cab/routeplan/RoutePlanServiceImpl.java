package com.company.cab.routeplan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.cab.cabs.Cab;
import com.company.cab.cabs.CabRepository;
import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointNotFoundException;
import com.company.cab.droppoint.DroppointDistance;
import com.company.cab.droppoint.DroppointDistanceRepository;
import com.company.cab.droppoint.DroppointRel;

@Service
public class RoutePlanServiceImpl implements RoutePlanService {
	
	@Autowired
	CabRepository cabRepo;
	
	@Autowired
	DroppointDistanceRepository dpdRepo;
	
	public RoutePlan getRoutePlan() {
		RoutePlan rp = new RoutePlan();
		int count = 0;
		Route route = null;
		List<Route> routes = new ArrayList<Route>();
		double routeCost = 0;
		double totalCost = 0;
		StringBuilder cabRoute = new StringBuilder();
		List<Cab> cabs = cabRepo.findAll();
		
		for(Cab cab:cabs) {
			
			route= new Route();
			cabRoute = new StringBuilder();
			count = 0;
			route.setCabId(cab.getId());
			route.setCost(cab.getCost());	
			for(DropPoint dp: cab.getDropPoints()) {
				count++;				
				cabRoute.append(dp.getDropPointName());
				if(count < cab.getDropPoints().size())
					cabRoute.append(",");
			}
			route.setRoute(cabRoute.toString());
			routeCost = getRouteCost(cab);
			totalCost+=routeCost;
			route.setCost(routeCost);
			routes.add(route);
			
		}
		
		rp.setRoutes(routes);
		rp.setTotalcost(totalCost);
		
		return rp;
	}
	
	public double calculateRouteDistance(Cab cab) {
		int count = 0;
		double distance = 0;
		DroppointRel dpRel = null;
		
		for(DropPoint dp: cab.getDropPoints()) {
			
			if(count < cab.getDropPoints().size()-1) {
			
			count++;
			dpRel = new DroppointRel();
			dpRel.setDroppoint_from(dp);
			dpRel.setDroppoint_to(cab.getDropPoints().get(count));
			Optional<DroppointDistance> dpd =  dpdRepo.findById(dpRel);
			
			if(!dpd.isPresent())
				throw new DropPointNotFoundException("Distance not nound found for "+dp.getDropPointName() +" and "+ cab.getDropPoints().get(count).getDropPointName());
			
			distance += dpd.get().getDistance();
			}
		}
		
		return distance;
		
	}
	
	public double getRouteCost(Cab cab) {
		 return calculateRouteDistance(cab)*cab.getCost();
	}

}
