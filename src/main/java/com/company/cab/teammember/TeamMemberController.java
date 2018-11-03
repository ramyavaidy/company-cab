package com.company.cab.teammember;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.cab.cabs.Cab;
import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointNotFoundException;
import com.company.cab.droppoint.DropPointRepository;

@RestController
public class TeamMemberController {

	@Autowired
	TeamMemberService teamMemberService;
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerTeamMembers(@RequestBody TeamMember teammember){
		
		teamMemberService.registerTeamMembers(teammember);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	@GetMapping(path="/team_members")
	public List<TeamMember> getTeamMembers(){
		return teamMemberService.getTeamMembers();
	}

}
