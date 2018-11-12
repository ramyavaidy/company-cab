package com.company.cab.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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
	private DroppointServiceImpl mock;
	
	@Mock
	private DropPointRepository repository;

	@Mock
	private DroppointDistanceRepository dpdrepo;

	@Test
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
		
		verify(repository, times(1)).saveAll(any());
		verify(dpdrepo, times(1)).deleteAllInBatch();
		verify(dpdrepo, times(1)).saveAll(any());

		 
	}
	
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
	
	/*@Test
	public void testDeleteDropPoints() throws Exception{
		
		LinkedHashMap<String,String> dpMap = new LinkedHashMap<String,String>();
		dpMap.put("hq", "0,1,2");
		dpMap.put("pointA", "1,0,1");
		dpMap.put("pointB", "2,1,0");
		
		DropPoint dp1 = new DropPoint("pointA");
		DropPoint dp2 = new DropPoint("pointB");
		DropPoint dp3 = new DropPoint("pointC");
		DropPoint dp4 = new DropPoint("hq");
		List<DropPoint> existingdpList = new ArrayList<DropPoint>();
		existingdpList.add(dp4);
		existingdpList.add(dp1);
		existingdpList.add(dp2);
		existingdpList.add(dp3);
		
		List<String> dpToDelete = new ArrayList<String>();
		dpToDelete.add("pointC");
				
		List<DropPoint> newdpList = new ArrayList<DropPoint>();
		newdpList.add(dp4);
		newdpList.add(dp1);
		newdpList.add(dp2);
		
		List<String> dps = new ArrayList<String>();
		dps.add("hq");
		dps.add("pointA");
		dps.add("pointB");
		
		
		List<DroppointDistance> dpdList = new ArrayList<DroppointDistance>();
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("hq"),new DropPoint("hq")),new Double(0)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("hq"),new DropPoint("pointA")),new Double(1)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("hq"),new DropPoint("pointB")),new Double(2)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("pointA"),new DropPoint("hq")),new Double(1)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("pointA"),new DropPoint("pointA")),new Double(0)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("pointA"),new DropPoint("pointB")),new Double(1)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("pointB"),new DropPoint("hq")),new Double(2)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("pointB"),new DropPoint("pointA")),new Double(1)));
		dpdList.add(new DroppointDistance(new DroppointRel(new DropPoint("pointB"),new DropPoint("pointB")),new Double(0)));
		
		doNothing().when(dpdrepo).deleteAllInBatch();
		doNothing().when(repository).deleteAll(any());
		when(repository.saveAll(newdpList)).thenReturn(newdpList);
		when(dpdrepo.saveAll(dpdList)).thenReturn(dpdList);
		doNothing().when(mock).deletecabDropPoint(dpToDelete);
		
		when(em.getTransaction()).thenReturn(any());
		doNothing().when(em).getTransaction().begin();
		doNothing().when(em).createNativeQuery(any());
		when(em.createNativeQuery(any())).thenReturn(any());
		when(q.setParameter(0,any())).thenReturn(any());
		when(q.executeUpdate()).thenReturn(any());
		//doNothing().when(q.executeUpdate());
		
		doNothing().when(em).getTransaction().commit();
		doNothing().when(em).close();
		
		
		dpServiceImpl.deleteDropPoint(dpMap);
 
		//deletecabDropPoint(dpCabListToDelete);
		
		ArgumentCaptor<List> dpListCaptor = ArgumentCaptor.forClass(List.class);
		verify(dpServiceImpl).deletecabDropPoint(dpListCaptor.capture());
		assertEquals("pointC", ((String)(dpListCaptor.getValue().get(0))));
		
		ArgumentCaptor<List> dpListCaptor1 = ArgumentCaptor.forClass(List.class);
		verify(repository).deleteAll(dpListCaptor1.capture());
		assertEquals("pointC", ((DropPoint)(dpListCaptor1.getValue().get(0))).getDropPointName());
		
		ArgumentCaptor<List> dpListCaptor2 = ArgumentCaptor.forClass(List.class);
		verify(repository).saveAll(dpListCaptor2.capture());
		assertEquals("hq", ((DropPoint)(dpListCaptor2.getValue().get(0))).getDropPointName());
		assertEquals("pointA", ((DropPoint)(dpListCaptor2.getValue().get(1))).getDropPointName());
		assertEquals("pointB", ((DropPoint)(dpListCaptor2.getValue().get(2))).getDropPointName());
		
		
	}*/
	
}
