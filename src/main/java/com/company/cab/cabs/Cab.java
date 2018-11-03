package com.company.cab.cabs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.company.cab.droppoint.DropPoint;
import com.company.cab.teammember.TeamMember;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name="cab")
public class Cab {
	
	@Id
	@GeneratedValue
	@Column(name = "cab_id")
	private int id;
	
	@NotNull
	@Column(name = "cost")
	private double cost;
	
	@NotNull
	@Column(name = "capacity")
	private int capacity;
	
	@NotNull
	@ManyToMany()
	@JoinTable(name = "cab_droppoint",
    joinColumns = @JoinColumn(name = "cab_id"),
    inverseJoinColumns = @JoinColumn(name = "droppoint_id")
	)
	private List<DropPoint> dropPoints = new ArrayList<DropPoint>();
	
	@ManyToOne
	@JoinColumn(name="lastdroppoint")
	private DropPoint lastDropPoint;
	
	@OneToMany(mappedBy = "cab")
	private List<TeamMember> teamMembers;
	

	 public Cab(){
		 
	 }
	 

	public Cab(int id, double cost, int capacity) {
		super();
		this.id = id;
		this.cost = cost;
		this.capacity = capacity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public List<DropPoint> getDropPoints() {
		return dropPoints;
	}
	public void setDropPoint(List<DropPoint> dropPoints) {
		this.dropPoints = dropPoints;
	}
		
	public DropPoint getLastDropPoint() {
		return lastDropPoint;
	}


	public void setLastDropPoint(DropPoint lastDropPoint) {
		this.lastDropPoint = lastDropPoint;
	}


	@Override
	public String toString() {
		return "Cab [id=" + id + ", cost=" + cost + ", capacity=" + capacity + "]";
	}
	
	
	

}
