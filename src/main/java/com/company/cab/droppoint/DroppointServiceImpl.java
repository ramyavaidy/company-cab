package com.company.cab.droppoint;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroppointServiceImpl implements DroppointService {

	@Autowired
	private DropPointRepository repository;
	
	@Autowired
	private DroppointDistanceRepository dpdrepo;
	
	
	public void saveDropPoint(LinkedHashMap<String,String> dropPoints) {
		
		List<String> dps = new ArrayList<String>();
		List<DropPoint> droppointList = new ArrayList<DropPoint>();
		
		for(Map.Entry<String, String> entry:dropPoints.entrySet()) {
			dps.add(entry.getKey());
			droppointList.add(new DropPoint(entry.getKey()));		
			
		}	
		
		repository.saveAll(droppointList);
		
		saveDroppointDistance( dps, dropPoints);
		
	}
	
	public List<DropPoint> retrieveAllDropPoints()
	{
		List<DropPoint> dropPoints = repository.findAll();
		
		return dropPoints;
	}
	
	public List<DroppointResponse> retrieveAllDropPointsDistance(){
		
			List<DroppointDistance> dropPoints = dpdrepo.findAll();
			List<DroppointResponse> dpRespList = new ArrayList<DroppointResponse>();
			
			for(DroppointDistance dpd: dropPoints) {							
				dpRespList.add(this.convertDroppointDistanceToResponse(dpd));
			}
			return dpRespList;
	}
	
	public DropPoint retrieveOneDropPoint(String name) {
		Optional<DropPoint> droppoint = repository.findById(name);
		
		if(!droppoint.isPresent()) {
			throw new DropPointNotFoundException("Name-" + name);
		}
		
		return droppoint.get();
		
	}
	private void saveDroppointDistance(List<String> dps,LinkedHashMap<String,String> dropPoints) {
		List<DroppointDistance> dpdList = new ArrayList<DroppointDistance>();
		int count = 0;
		for(String dp:dps) {
			count = 0;
			for(String distance: dropPoints.get(dp).split(",")) {
				dpdList.add(createDroppointDistanceFromDroppoints(new DropPoint(dp),new DropPoint(dps.get(count)),Double.valueOf(distance)));
				count++;
				
		}
		dpdrepo.saveAll(dpdList);
		
		}
	}
	
	private DroppointResponse convertDroppointDistanceToResponse(DroppointDistance dpd) {
		
		DroppointResponse dpResp = new DroppointResponse();
		dpResp.setDroppoint_from(dpd.getDroppointRel().getDroppoint_from().getDropPointName());
		dpResp.setDroppoint_to(dpd.getDroppointRel().getDroppoint_to().getDropPointName());
		dpResp.setDistance(dpResp.getDistance());
		
		return dpResp;
		
	}
	
	private DroppointDistance createDroppointDistanceFromDroppoints(DropPoint dp1,DropPoint dp2,double distance) {
		
		DroppointDistance dpd = new DroppointDistance();
		DroppointRel droppointRel = new DroppointRel();
		droppointRel.setDroppoint_from(dp1);
		droppointRel.setDroppoint_to(dp2);
		dpd.setDroppointRel(droppointRel);
		dpd.setDistance(distance);
		
		return dpd;
		
	}
}
