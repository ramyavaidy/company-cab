package com.company.cab.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.company.cab.cabs.Cab;
import com.company.cab.cabs.CabRepository;
import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointRepository;
import com.company.cab.teammember.TeamMember;
import com.company.cab.teammember.TeamMemberRepository;
import com.company.cab.teammember.TeamMemberServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TeamMemberServiceTest {
	
		
	@InjectMocks
	TeamMemberServiceImpl teamMemberServiceImpl;
	
	
	@Mock
	TeamMemberRepository tmRepo;
	
	@Mock
	DropPointRepository dpRepo;
	
	@Mock
	CabRepository cabRepo;
	
	Cab cabAC;
	Cab cabAB;
	Cab cabAB2;
	DropPoint dp1;
	DropPoint dp2;
	DropPoint dp3;	
	DropPoint dp4;
	DropPoint dp5;
	DropPoint dp6;	
	List<DropPoint> dpList;
	 TeamMember tmFemalePointA ;
	 TeamMember tmMalePointA ;
	 TeamMember tmMalePointB;
	 TeamMember tmFemalePointB;
	 List<TeamMember> tmListTwoMaleAB;
	 List<TeamMember> tmListOneMaleAOneFemaleB;
	 List<TeamMember> tmListTwoMaleABOneFemaleA;
	 List<Cab> cabs;
	 List<Cab> cabs1;
	 

	
	@Before
	public void setUp() {
	
		//pointA,pintB
	cabAB = new Cab();
	cabAB.setId(1);
	cabAB.setCapacity(3);
	cabAB.setCost(2.0);
	
	dp1 = new DropPoint("target_headquarter");
	dp2 = new DropPoint("pointA");
	dp3 = new DropPoint("pointB");
	dp4 = new DropPoint("pointC");
	
	dpList = new ArrayList<DropPoint>();
	dpList.add(dp1);
	dpList.add(dp2);
	dpList.add(dp3);
	
	cabAB.setDropPoint(dpList);
	cabAB.setLastDropPoint(dp3);
	
	//pointA,pointC
		cabAC = new Cab();
		cabAC.setId(2);
		cabAC.setCapacity(3);
		cabAC.setCost(3.0);
		
		
		dpList = new ArrayList<DropPoint>();
		dpList.add(dp1);
		dpList.add(dp2);
		dpList.add(dp4);
		
		cabAC.setDropPoint(dpList);
		cabAC.setLastDropPoint(dp4);
		
		cabs= new ArrayList<Cab>();
		cabs.add(cabAB);
		cabs.add(cabAC);
		
	
	//pointA,pointB 
	cabAB2 = new Cab();
	cabAB2.setId(1);
	cabAB2.setCapacity(3);
	cabAB2.setCost(2.0);
	
	dpList = new ArrayList<DropPoint>();
	dpList.add(dp1);
	dpList.add(dp2);
	dpList.add(dp3);
	
	cabAB2.setDropPoint(dpList);
	cabAB2.setLastDropPoint(dp3);
	
	
	
	cabs1= new ArrayList<Cab>();
	cabs1.add(cabAB2);
	cabs1.add(cabAC);
	
	 tmFemalePointA = new TeamMember(100,"F","pointA");
	 tmMalePointA = new TeamMember(100,"M","pointA");
	 tmMalePointB = new TeamMember(100,"M","pointB");
	 
	   
	 tmFemalePointB= new TeamMember(101,"F","pointB");
	  
	  
	  tmListTwoMaleAB = new ArrayList<TeamMember>();
	  tmListTwoMaleAB.add(tmMalePointA);
	  tmListTwoMaleAB.add(tmMalePointB);
	  
	  tmListOneMaleAOneFemaleB = new ArrayList<TeamMember>();
	  tmListOneMaleAOneFemaleB.add(tmMalePointA);
	  tmListOneMaleAOneFemaleB.add(tmFemalePointB);
	  
	  tmListTwoMaleABOneFemaleA = new ArrayList<TeamMember>();
	  tmListTwoMaleABOneFemaleA.add(tmMalePointA);
	  tmListTwoMaleABOneFemaleA.add(tmMalePointB);	
	  tmListTwoMaleABOneFemaleA.add(tmFemalePointA);
	}
	
	@Test
	public void testIfFalseWhenWomenEmployeeIsNotInLastDropPoint() throws Exception {		
			  
		Mockito.when(tmRepo.findByCab(Mockito.anyInt())).thenReturn(tmListTwoMaleAB);
		boolean isWomenEmployeeLast = teamMemberServiceImpl.isWomenEmployeeInLastDropPoint(cabAB, tmFemalePointA);
		
		Assert.assertEquals(false, isWomenEmployeeLast);
	
	}
	
	@Test
	public void testIfTrueWhenWomenEmployeeIsInLastDropPoint() throws Exception {		
			  
		Mockito.when(tmRepo.findByCab(Mockito.anyInt())).thenReturn(tmListOneMaleAOneFemaleB);
		boolean isWomenEmployeeLast = teamMemberServiceImpl.isWomenEmployeeInLastDropPoint(cabAB, tmFemalePointA);
		
		Assert.assertEquals(true, isWomenEmployeeLast);
	
	}
	

	@Test
	public void testIfCabisAllocatedWhenCapacityisThere() throws Exception {
		
		 Mockito.when(cabRepo.findByDropPoint(Mockito.anyString())).thenReturn(cabs);
		 Mockito.when(tmRepo.findByCab(1)).thenReturn(tmListOneMaleAOneFemaleB);
		 Mockito.when(tmRepo.findByCab(2)).thenReturn(tmListTwoMaleAB);
			  
		Cab resp = teamMemberServiceImpl.allocateCab(tmFemalePointA);
		
		Assert.assertNotNull(resp);
	
	}
	
	@Test
	public void testIfCabisNotAllocatedWhenCapacityisNotThere() throws Exception {
		
		 Mockito.when(cabRepo.findByDropPoint(Mockito.anyString())).thenReturn(cabs1);
		 Mockito.when(tmRepo.findByCab(1)).thenReturn(tmListOneMaleAOneFemaleB);
		 Mockito.when(tmRepo.findByCab(2)).thenReturn(tmListTwoMaleABOneFemaleA);
			  
		Cab resp = teamMemberServiceImpl.allocateCab(tmFemalePointA);
		
		Assert.assertNull(resp);
	
	}
	
	
	
	
	
	

}
