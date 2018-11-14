package com.company.cab.cabs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CabRequest {
	
	private Integer id;
	
	@NotNull(message="Please provide cost")
	@Positive(message="cost should be greater than 0")
	private Double cost;
	
	@NotNull(message="Please provide capacity")
	@Positive(message="capacity should be greater than 0")
	private Integer capacity;
	
	@NotNull(message="Please provide drop points")
	private String droppoints;
	
	@NotNull(message="Please provide last drop point")
	private String lastDroppoint;
	
	CabRequest(){
		
	}
	
	public CabRequest(double cost, int capacity, String droppoints) {
		super();
		this.cost = cost;
		this.capacity = capacity;
		this.droppoints = droppoints;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getDroppoints() {
		return droppoints;
	}
	public void setDroppoints(String droppoints) {
		this.droppoints = droppoints;
	}

	public String getLastDroppoint() {
		return lastDroppoint;
	}

	public void setLastDroppoint(String lastDroppoint) {
		this.lastDroppoint = lastDroppoint;
	}

}
