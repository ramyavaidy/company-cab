package com.company.cab.cabs;

public class CabRequest {
	
	private Integer id;
	private double cost;
	private int capacity;
	private String droppoints;
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
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
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
