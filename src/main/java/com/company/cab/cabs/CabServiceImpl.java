package com.company.cab.cabs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointNotFoundException;
import com.company.cab.droppoint.DropPointRepository;

@Service
public class CabServiceImpl implements CabService {
	
	@Autowired
	CabRepository repository;
	
	@Autowired
	DropPointRepository droppointrepo;
	
	public List<Cab> getAllCabs(){
	return repository.findAll();
	}
	
	public Cab getCabById(Integer id){		
		Optional<Cab> cab = repository.findById(id);
		
		if(!cab.isPresent()) {
			throw new CabNotFoundException("id-" + id);
		}
		
		return cab.get();
		
	}
	
	public Cab createCab(@RequestBody CabRequest cabRequest) {
		Optional<DropPoint> droppointOpt = null;
		 
		Cab cab = new Cab();
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
		Cab savedCab = repository.save(cab);
		return savedCab;
	}
}
