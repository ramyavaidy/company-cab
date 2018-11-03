package com.company.cab.droppoint;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="droppoint_distance")
public class DroppointDistance {
	
	@EmbeddedId
	private DroppointRel droppointRel;
	
	private double distance;
	
	public DroppointDistance(){
		
	}

	public DroppointDistance(DroppointRel droppointRel, double distance) {
		super();
		this.droppointRel = droppointRel;
		this.distance = distance;
	}

	public DroppointRel getDroppointRel() {
		return droppointRel;
	}

	public void setDroppointRel(DroppointRel droppointRel) {
		this.droppointRel = droppointRel;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	

}
