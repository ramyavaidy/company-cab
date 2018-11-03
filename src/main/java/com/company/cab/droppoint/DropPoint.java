package com.company.cab.droppoint;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.company.cab.cabs.Cab;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "droppoint")
public class DropPoint {
	
		
	@Id	
	@Column(name = "droppointname")
  private String dropPointName;

	@JsonIgnore
	@ManyToMany (mappedBy="dropPoints" )
	private List<Cab> cabs;
	

    
    

	public DropPoint( String dropPointName) {
		super();		
		this.dropPointName = dropPointName;
		//this.distance = distance;
	}
	
	DropPoint(){
		
	}
	
	public String getDropPointName() {
		return dropPointName;
	}
	public void setDropPointName(String dropPointName) {
		this.dropPointName = dropPointName;
	}
/*	public String getDistance() {
		return distance;
	}
	public void setDistance( String distance) {
		this.distance = distance;
	}*/
 

	public List<Cab> getCabs() {
		return cabs;
	}

	public void setCabs(List<Cab> cabs) {
		this.cabs = cabs;
	}

	
/*	
	public List<DroppointRel> getDroppointRels() {
		return droppointRels;
	}

	public void setDroppointRels(List<DroppointRel> droppointRels) {
		this.droppointRels = droppointRels;
	}

	
	public List<DroppointRel> getDroppointRelsTo() {
		return droppointRelsTo;
	}

	public void setDroppointRelsTo(List<DroppointRel> droppointRelsTo) {
		this.droppointRelsTo = droppointRelsTo;
	}*/

	@Override
	public String toString() {
		return "DropPoint [dropPointName=" + dropPointName + "]";
	}
	
	
	
	

}
