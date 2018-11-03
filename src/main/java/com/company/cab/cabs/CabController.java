package com.company.cab.cabs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointNotFoundException;
import com.company.cab.droppoint.DropPointRepository;

@RestController
public class CabController {
	
	@Autowired 
	CabService cabService;
	
	@Autowired
	CabRepository repository;
	
	@Autowired
	DropPointRepository droppointrepo;
	
	@GetMapping("/cabs")
	public List<Cab> getAllCabs(){		
		return repository.findAll();
		
	}
	
	@GetMapping("/cabs/{id}")
	public Cab getCabById(@PathVariable Integer id){		
		return cabService.getCabById(id);
		
	}
	
	@PostMapping("/cabs")
	public ResponseEntity<Void> createCab(@RequestBody CabRequest cabRequest){		
		cabService.createCab(cabRequest);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping("/cabs/{id}")
	public ResponseEntity<Void> updateCab(@RequestBody CabRequest cabRequest){		
		
		 Optional<DropPoint> droppointOpt = null;
		 
		Cab cab = new Cab();
		cab.setId(cabRequest.getId());
		cab.setCapacity(cabRequest.getCapacity());
		cab.setCost(cabRequest.getCost());
		String dps[] = cabRequest.getDroppoints().split(",");
		
		
		for(String dp:dps) {
		  droppointOpt = droppointrepo.findById(dp);
		 
		 if(!droppointOpt.isPresent())
			 throw new DropPointNotFoundException("Drop point not found - "+dp);
		 
		 cab.getDropPoints().add(droppointOpt.get());
		}
		
       droppointOpt = droppointrepo.findById(cabRequest.getLastDroppoint());
       
       if(!droppointOpt.isPresent())
			 throw new DropPointNotFoundException("Last Drop point not found - "+cabRequest.getLastDroppoint());
		 
       cab.setLastDropPoint(droppointOpt.get());
		 	
		repository.save(cab);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/cabs/{id}")
	public ResponseEntity<Void> deleteCab(@PathVariable Integer id) {
		
		repository.deleteById(id);
		return  new ResponseEntity<Void> (HttpStatus.OK);
		
	}

	

}
