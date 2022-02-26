package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="m_staff",uniqueConstraints={ @UniqueConstraint( columnNames = {"id"})} )
public class Staff {
	private String id;
	private String firstName;
	private String lastName;
	private String position;
	private String part;	
	
	private String code;
	
	
	public Staff() {
		
	}

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    @Column(name="firstname")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    @Column(name="lastname")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="position")
	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name="part")
	public String getPart() {
		return part;
	}


	public void setPart(String part) {
		this.part = part;
	}



	public Staff(String id, String firstName, String lastName, String position, String part) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.part = part;
	}
    @Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
