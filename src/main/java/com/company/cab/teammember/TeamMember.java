package com.company.cab.teammember;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.company.cab.cabs.Cab;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "teammember")
public class TeamMember {
	
	@Id
	@Column(name = "teammember_id")
	@NotNull(message="Please provide team member id")
	private Integer id;
	
	@Column(name = "gender")
	@NotNull(message="Please provide gender")
	private String gender;
	
	@Column(name = "droppointname")
	@NotNull(message="Please provide droppoint	")
	private String droppointname; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cab_id")
	private Cab cab;
	
	public TeamMember(){
		
	}
	
	public TeamMember(Integer id, String gender, String dropPointName) {
		super();
		this.id = id;
		this.gender = gender;
		this.droppointname = dropPointName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDroppointname() {
		return droppointname;
	}

	public void setDroppointname(String droppointname) {
		this.droppointname = droppointname;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}
	
	
	

}
