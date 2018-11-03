package com.company.cab.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.cab.cabs.CabRepository;
import com.company.cab.droppoint.DropPoint;
import com.company.cab.droppoint.DropPointRepository;
import com.company.cab.droppoint.DroppointDistanceRepository;
import com.company.cab.droppoint.DroppointResponse;
import com.company.cab.droppoint.DroppointService;
import com.company.cab.teammember.TeamMemberRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DropPointControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DroppointService dpService;
	
	@MockBean
	private CabRepository cabRepo;
	
	@MockBean
	private TeamMemberRepository tmRepo;
	
	@MockBean
	private DropPointRepository dpRepo;
	
	@MockBean
	private DroppointDistanceRepository dpdRepo;
	
	@Before
	public void setUp() {
	
	}
	
	
	@Test
	public void retrieveAllDropPoints() throws Exception {
		
		DropPoint dp = new DropPoint("target_headquarter");
		DropPoint dp1 = new DropPoint("pointA");
		List<DropPoint> dropPointsList = new ArrayList<DropPoint>();
		dropPointsList.add(dp);
		dropPointsList.add(dp1);
		
		Mockito.when( dpService.retrieveAllDropPoints()).thenReturn(dropPointsList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/drop_points").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].dropPointName", is(dp.getDropPointName())))
				.andReturn();
		
	}
	
	@Test
	public void retrieveAllDropPointDistance() throws Exception {
		
		List<DroppointResponse> dpds = new ArrayList<DroppointResponse>();
		DroppointResponse dpResp1 = new DroppointResponse();
		dpResp1.setDroppoint_from("target_headquarter");
		dpResp1.setDroppoint_to("pointA");
		dpResp1.setDistance(2);
		dpds.add(dpResp1);
				
		Mockito.when( dpService.retrieveAllDropPointsDistance()).thenReturn(dpds);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/drop_points/distance").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].droppoint_from", is("target_headquarter")))
				.andExpect(jsonPath("$[0].distance", is(2.0)))
				.andReturn();
		
	}
	
	@Test
	public void createDroppoints() throws Exception{
		
		String droppoints = "{\"target_headquarters\":\"0,1\",\"pointA\":\"1,0\"}";
		
		doNothing().when(dpService).saveDropPoint(Mockito.any());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/drop_points")
				.accept(MediaType.APPLICATION_JSON)
				.content(droppoints)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
				
		//Mockito.when(dpService.saveDropPoint(Mockito.any())).thenReturn(Mockito.any());
		
		
	}
	

}
