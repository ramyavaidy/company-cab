package com.company.cab.droppoint;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DroppointRel implements Serializable {
	
	@ManyToOne
	@JoinColumn(name="droppoint_from")
	private DropPoint droppoint_from;
		
	@ManyToOne
	@JoinColumn(name="droppoint_to")
	private DropPoint droppoint_to;
	
	
	public DroppointRel(){
		
	}

	public DroppointRel(DropPoint droppoint_from, DropPoint droppoint_to) {
		super();
		this.droppoint_from = droppoint_from;
		this.droppoint_to = droppoint_to;

	}

	public DropPoint getDroppoint_from() {
		return droppoint_from;
	}

	public void setDroppoint_from(DropPoint droppoint_from) {
		this.droppoint_from = droppoint_from;
	}

	public DropPoint getDroppoint_to() {
		return droppoint_to;
	}

	public void setDroppoint_to(DropPoint droppoint_to) {
		this.droppoint_to = droppoint_to;
	}


	
	

}
