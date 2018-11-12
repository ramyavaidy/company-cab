package com.company.cab.droppoint;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface DroppointService {
	
	public void  saveDropPoint(LinkedHashMap<String,String> dropPoints);
	public List<DropPoint> retrieveAllDropPoints();
	public List<DroppointResponse> retrieveAllDropPointsDistance();
	public DropPoint retrieveOneDropPoint(String name);
	public void deleteDropPoint(LinkedHashMap<String,String> dropPoints);

}
