package com.company.cab.teammember;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.cab.cabs.Cab;
import com.company.cab.cabs.CabRepository;
import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointNotFoundException;
import com.company.cab.droppoint.DropPointRepository;

@Service
public class TeamMemberServiceImpl implements TeamMemberService{
	
	@Autowired
	CabRepository cabRepository;
	
	@Autowired
	TeamMemberRepository tmRepo;
	
	@Autowired
	DropPointRepository dpRepo;
	
	public List<TeamMember> getTeamMembers(){
		return tmRepo.findAll();
	}
	
	public TeamMember registerTeamMembers(TeamMember teammember) {
		
		Optional<DropPoint> droppointOptional = dpRepo.findById(teammember.getDroppointname());
		
		if(!droppointOptional.isPresent())
			throw new DropPointNotFoundException("Name -"+teammember.getDroppointname());
	    
		Cab cab =  allocateCab(teammember);
		
		if(cab == null){
			throw new CabCapacityNotEnoughException("There is no enough cab capacity for droppoint "+teammember.getDroppointname());
		}
		
		teammember.setCab(cab);
		
		return tmRepo.save(teammember);
		
	}
	
	
	public Cab allocateCab(TeamMember teammember) {
		
		List<Cab> cabs = cabRepository.findByDropPoint(teammember.getDroppointname());
		
		if(!cabs.isEmpty()) {
			for(Cab cab : cabs) {
				List<TeamMember> tms = tmRepo.findByCab(cab.getId());
				if(cab.getCapacity() == tms.size()) 
					continue;
				else {
					if(tms.size() > 0) {
						if(isWomenEmployeeInLastDropPoint(cab, teammember)) {
							if(tms.size() < cab.getCapacity() - 1)
							return cab;
						}else {
							if(tms.size() < cab.getCapacity()){
								return cab;
							}
						}
					}else {
						return cab;
					}
				}
		}
		
		
		}
		
		return null;
		//cabRepository.findBy
	}
	
	public boolean isWomenEmployeeInLastDropPoint(Cab cab,TeamMember teammember) {		 
			
		boolean isWomenEmployeeInLastDropPoint = false;
		List<TeamMember> tms = tmRepo.findByCab(cab.getId());
		int noOfMaleEmp = 0;
		int noOfFemaleEmp = 0;
		
		for(TeamMember tm: tms) {
			if(tm.getGender().equals("M") && tm.getDroppointname().equals(cab.getLastDropPoint().getDropPointName())) {	
				noOfMaleEmp++;				
			}
			if(tm.getGender().equals("F") && tm.getDroppointname().equals(cab.getLastDropPoint().getDropPointName()) ) {				
				noOfFemaleEmp++;
			}
			
		}
		if(teammember.getGender().equals("F") && teammember.getDroppointname().equals(cab.getLastDropPoint().getDropPointName()) ) {
			noOfFemaleEmp++;
		}
		
		if(noOfFemaleEmp > 0 && noOfMaleEmp == 0) {
			isWomenEmployeeInLastDropPoint = true;
		}
		
		return isWomenEmployeeInLastDropPoint;
		
		
	}

}
