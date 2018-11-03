package com.company.cab.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.company.cab.droppoint.DroppointDistance;
import com.company.cab.droppoint.DroppointDistanceRepository;
import com.company.cab.droppoint.DroppointRel;
import com.company.cab.routeplan.RoutePlan;
import com.company.cab.routeplan.RoutePlanServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RoutePlanServiceTest {

	@InjectMocks
	RoutePlanServiceImpl rpServiceImpl;
	
	@Mock
	CabRepository cabRepo;
	
	@Mock	
	DroppointDistanceRepository dpdRepo;
	
	Cab cab ;
	Cab cab1;
	List<Cab> cabs;
	
	DropPoint dp1;
	DropPoint dp2;
	DropPoint dp3;
	DropPoint dp4;
	DropPoint dp5;
	DropPoint dp6;
	List<DropPoint> dpList;
	List<DropPoint> dpList1;
	DroppointRel dpRel1;
	DroppointRel dpRel2;
	DroppointRel dpRel3;
	DroppointRel dpRel4;
	DroppointDistance dpd1;
	DroppointDistance dpd2;
	DroppointDistance dpd3;
	DroppointDistance dpd4;
	
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
	
	dpRel1 = new DroppointRel();
	dpRel1.setDroppoint_from(dp1);
	dpRel1.setDroppoint_to(dp2);
	
	dpRel2 = new DroppointRel();
	dpRel2.setDroppoint_from(dp2);
	dpRel2.setDroppoint_to(dp3);
	
	dpd1 = new DroppointDistance(dpRel1, 2.0);
	dpd2 = new DroppointDistance(dpRel2, 3.0);
		
	
	cab1 = new Cab();
	cab1.setId(1);
	cab1.setCapacity(3);
	cab1.setCost(4.0);
	
	dp4 = new DropPoint("pointC");
	dpList1 = new ArrayList<DropPoint>();
	dpList1.add(dp1);
	dpList1.add(dp2);
	dpList1.add(dp4);
	
	cab1.setDropPoint(dpList1);
	cab1.setLastDropPoint(dp4);
	
	dpRel3= new DroppointRel();
	dpRel3.setDroppoint_from(dp1);
	dpRel3.setDroppoint_to(dp2);
	
	dpRel4 = new DroppointRel();
	dpRel4.setDroppoint_from(dp2);
	dpRel4.setDroppoint_to(dp4);
	
	dpd3 = new DroppointDistance(dpRel3, 3.0);
	dpd4 = new DroppointDistance(dpRel4, 4.0);
	
	cabs = new ArrayList<Cab>();
	cabs.add(cab);
	cabs.add(cab1);
	
	}
	
	@Test
	public void verifyRouteDistance() throws Exception {
		
		Mockito.when( dpdRepo.findById(Mockito.any())).thenReturn(Optional.of(dpd1)).thenReturn(Optional.of(dpd2));
		//Mockito.when( dpdRepo.findById(dpRel2)).thenReturn(Optional.of(dpd2));
		
		Assert.assertEquals(5.0, rpServiceImpl.calculateRouteDistance(cab),0);		
	}
	
	@Test
	public void verifyTotalCost() throws Exception {
		
		Mockito.when( cabRepo.findAll()).thenReturn(cabs);
		Mockito.when( dpdRepo.findById(Mockito.any()))
		              .thenReturn(Optional.of(dpd1))
		              .thenReturn(Optional.of(dpd2))
		              .thenReturn(Optional.of(dpd3))
		              .thenReturn(Optional.of(dpd4));
		//Mockito.when( dpdRepo.findById(dpRel2)).thenReturn(Optional.of(dpd2));
		RoutePlan routePlan = rpServiceImpl.getRoutePlan();
		
		Assert.assertEquals(38.0, routePlan.getTotalcost(),0);		
	}
}
