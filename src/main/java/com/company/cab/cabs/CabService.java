package com.company.cab.cabs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CabService {

	public Cab getCabById(Integer id);
	public List<Cab> getAllCabs();
	public Cab createCab(@RequestBody CabRequest cabRequest);
}
