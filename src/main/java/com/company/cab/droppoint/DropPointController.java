package com.company.cab.droppoint;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DropPointController {
	
	@Autowired
	DroppointService dpService;
	
	@PostMapping(path="/drop_points")
	public ResponseEntity<Void> createDropPoints(@RequestBody LinkedHashMap<String,String> dropPoints) throws Exception {
		
		dpService.saveDropPoint(dropPoints);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	@GetMapping(path="/drop_points/distance")
	public List<DroppointResponse> retrieveAllDropPointsDistance(){
		return dpService.retrieveAllDropPointsDistance();
	}
	
	@GetMapping(path="/drop_points")
	public List<DropPoint> retrieveAllDropPoints(){
		return dpService.retrieveAllDropPoints();
	}
	
	@GetMapping(path="/drop_points/{name}")
	public DropPoint retrieveOneDropPoint(@PathVariable String name){		
		return dpService.retrieveOneDropPoint(name);
		
	}
	
	@DeleteMapping(path="/drop_points")
	public ResponseEntity<Void> deleteDropPoints(@RequestBody LinkedHashMap<String,String> dropPoints) throws Exception {
	
		dpService.deleteDropPoint(dropPoints);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
		
	//TODO - Delete droppoint
	//TODO - update droppoint

}
