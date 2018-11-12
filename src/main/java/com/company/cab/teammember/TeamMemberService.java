package com.company.cab.teammember;

import java.util.List;


import com.company.cab.cabs.Cab;

public interface TeamMemberService {

	public Cab allocateCab(TeamMember teammember);
	
	public List<TeamMember> getTeamMembers();
	
	public TeamMember registerTeamMembers(TeamMember teammember);
	
	public boolean isWomenEmployeeInLastDropPoint(Cab cab,TeamMember teammember);
}
