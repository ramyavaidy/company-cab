package com.company.cab.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointRepository;
import com.company.cab.droppoint.DroppointDistance;
import com.company.cab.droppoint.DroppointDistanceRepository;
import com.company.cab.droppoint.DroppointRel;
import com.company.cab.droppoint.DroppointResponse;
import com.company.cab.droppoint.DroppointServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DroppointServiceTest {
	
	@InjectMocks
	DroppointServiceImpl dpServiceImpl;
	
	@Mock
	private DropPointRepository repository;

	@Mock
	private DroppointDistanceRepository dpdrepo;

	@Mock
	EntityManagerFactory emf;
	
/*	@Test
	public void testIfAllDropPointsAreSaved() throws Exception{
		
		LinkedHashMap<String,String> dpMap = new LinkedHashMap<String,String>();
		dpMap.put("pointA", "0,1");
		dpMap.put("pointB", "1,0");
		
		List<DropPoint> dpList = new ArrayList<DropPoint>();
		dpList.add(new DropPoint("pointA"));
		dpList.add(new DropPoint("pointB"));
		
		List<DroppointDistance> dpdList = new ArrayList<DroppointDistance>();
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("pointA"),new DropPoint("pointB")),new Double(2)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("pointA"),new DropPoint("pointA")),new Double(0)));
		
		when(repository.saveAll(dpList)).thenReturn(dpList);
		doNothing().when(dpdrepo).deleteAllInBatch();
		when(dpdrepo.saveAll(dpdList)).thenReturn(dpdList);
		 
		dpServiceImpl.saveDropPoint(dpMap);
		
		verify(repository, times(1)).saveAll(dpList);
		verify(dpdrepo, times(1)).deleteAllInBatch();
		verify(dpdrepo, times(1)).saveAll(dpdList);

		 
	}*/
	
	@Test
	public void testIfDropPointDistaceConvertedToDropPointResponse() throws Exception{
		
		DroppointDistance dpd= new DroppointDistance(new DroppointRel(new DropPoint("pointA"),new DropPoint("pointB")),new Double(2));
		
		DroppointResponse dpr = dpServiceImpl.convertDroppointDistanceToResponse(dpd);
		
		assertEquals("pointA", dpr.getDroppoint_from());
		assertEquals("pointB",dpr.getDroppoint_to());
		assertEquals(new Double(2.0),new Double(dpr.getDistance()));
		
		
		
	}

	@Test
	public void testIfDropPointsConvertedToDropPointRel() throws Exception{
		
		DropPoint dp1 = new DropPoint("pointA");
		DropPoint dp2 = new DropPoint("pointB");
		
		DroppointDistance dpd = dpServiceImpl.createDroppointDistanceFromDroppoints(dp1, dp2, new Double(2));
		assertEquals(dp1.getDropPointName(),dpd.getDroppointRel().getDroppoint_from().getDropPointName());
		assertEquals(dp2.getDropPointName(),dpd.getDroppointRel().getDroppoint_to().getDropPointName());
		assertEquals(new Double(2),new Double(dpd.getDistance()));
	}
	
}
