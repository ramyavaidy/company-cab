package com.company.cab.teammember;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamMemberController {

	@Autowired
	TeamMemberService teamMemberService;
	
	@PostMapping("/register")
	public ResponseEntity<TeamMember> registerTeamMembers(@Valid @RequestBody TeamMember teammember){
		
		TeamMember tm = teamMemberService.registerTeamMembers(teammember);
		return new ResponseEntity<TeamMember>(tm,HttpStatus.CREATED);
		
	}
	
	@GetMapping(path="/team_members")
	public List<TeamMember> getTeamMembers(){
		return teamMemberService.getTeamMembers();
	}

}
