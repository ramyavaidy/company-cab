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
	
	Cab cab1;
	Cab cab ;
	Cab cab2;
	DropPoint dp1;
	DropPoint dp2;
	DropPoint dp3;	
	List<DropPoint> dpList;
	 TeamMember tm ;
	 TeamMember tm1 ;
	 TeamMember tm2;
	 TeamMember tm3;
	 List<TeamMember> tmList;
	 List<TeamMember> tmList1;
	 List<TeamMember> tmList2;
	 List<Cab> cabs;
	 List<Cab> cabs1;
	 

	
	@Before
	public void setUp() {
	
		//pointA,pintB
	cab = new Cab();
	cab.setId(1);
	cab.setCapacity(3);
	cab.setCost(2.0);
	
	dp1 = new DropPoint("target_headquarter");
	dp2 = new DropPoint("pointA");
	dp3 = new DropPoint("pointB");
	dpList = new ArrayList<DropPoint>();
	dpList.add(dp1);
	dpList.add(dp2);
	dpList.add(dp3);
	
	cab.setDropPoint(dpList);
	cab.setLastDropPoint(dp3);
	
	//pointA,pointB 
	cab2 = new Cab();
	cab2.setId(1);
	cab2.setCapacity(3);
	cab2.setCost(2.0);
	
	dp1 = new DropPoint("target_headquarter");
	dp2 = new DropPoint("pointA");
	dp3 = new DropPoint("pointB");
	dpList = new ArrayList<DropPoint>();
	dpList.add(dp1);
	dpList.add(dp2);
	dpList.add(dp3);
	
	cab2.setDropPoint(dpList);
	cab2.setLastDropPoint(dp3);
	
	//pointA,pointC
	cab1 = new Cab();
	cab1.setId(2);
	cab1.setCapacity(3);
	cab1.setCost(3.0);
	
	dp1 = new DropPoint("target_headquarter");
	dp2 = new DropPoint("pointA");
	dp3 = new DropPoint("pointC");
	dpList = new ArrayList<DropPoint>();
	dpList.add(dp1);
	dpList.add(dp2);
	dpList.add(dp3);
	
	cab1.setDropPoint(dpList);
	cab1.setLastDropPoint(dp3);
	
	cabs= new ArrayList<Cab>();
	cabs.add(cab);
	cabs.add(cab1);
	
	cabs1= new ArrayList<Cab>();
	cabs.add(cab2);
	cabs.add(cab1);
	
	 tm = new TeamMember(100,"F","pointA");
	 tm1 = new TeamMember(100,"M","pointA");
	 tm2 = new TeamMember(100,"M","pointB");
	 
	   
	 tm3= new TeamMember(101,"F","pointB");
	  
	  
	  tmList = new ArrayList<TeamMember>();
	  tmList.add(tm1);
	  tmList.add(tm2);
	  
	  tmList1 = new ArrayList<TeamMember>();
	  tmList1.add(tm1);
	  tmList1.add(tm3);
	  
	  tmList2 = new ArrayList<TeamMember>();
	  tmList2.add(tm1);
	  tmList2.add(tm2);	
	  tmList2.add(tm);
	}
	
	@Test
	public void verifyIfFalseWhenWomenEmployeeIsNotInLastDropPoint() throws Exception {		
			  
		Mockito.when(tmRepo.findByCab(Mockito.anyInt())).thenReturn(tmList);
		boolean isWomenEmployeeLast = teamMemberServiceImpl.isWomenEmployeeInLastDropPoint(cab, tm);
		
		Assert.assertEquals(false, isWomenEmployeeLast);
	
	}
	
	@Test
	public void verifyIfTrueWhenWomenEmployeeIsInLastDropPoint() throws Exception {		
			  
		Mockito.when(tmRepo.findByCab(Mockito.anyInt())).thenReturn(tmList1);
		boolean isWomenEmployeeLast = teamMemberServiceImpl.isWomenEmployeeInLastDropPoint(cab, tm);
		
		Assert.assertEquals(true, isWomenEmployeeLast);
	
	}
	

	@Test
	public void verifyIfCabisAllocatedWhenCapacityisThere() throws Exception {
		
		 Mockito.when(cabRepo.findByDropPoint(Mockito.anyString())).thenReturn(cabs);
		 Mockito.when(tmRepo.findByCab(1)).thenReturn(tmList1);
		 Mockito.when(tmRepo.findByCab(2)).thenReturn(tmList);
			  
		Mockito.when(tmRepo.findByCab(Mockito.anyInt())).thenReturn(tmList1);
		Cab resp = teamMemberServiceImpl.allocateCab(tm);
		
		Assert.assertNotNull(resp);
	
	}
	
	@Test
	public void verifyIfCabisNotAllocatedWhenCapacityisNotThere() throws Exception {
		
		 Mockito.when(cabRepo.findByDropPoint(Mockito.anyString())).thenReturn(cabs1);
		 Mockito.when(tmRepo.findByCab(1)).thenReturn(tmList1);
		 Mockito.when(tmRepo.findByCab(2)).thenReturn(tmList2);
			  
		Mockito.when(tmRepo.findByCab(Mockito.anyInt())).thenReturn(tmList1);
		Cab resp = teamMemberServiceImpl.allocateCab(tm);
		
		Assert.assertNull(resp);
	
	}
	
	
	
	
	
	

}
