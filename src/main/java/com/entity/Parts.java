package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name ="m_parts" ,uniqueConstraints={ @UniqueConstraint( columnNames = {"Code"})} )
public class Parts {

	private int id;
	private String part;
	private String partE;
	
	public Parts(){		
	}
	public Parts(int id, String part, String partE) {
		super();
		this.id = id;
		this.part = part;
		this.partE = partE;
	}
	public Parts( String part, String partE) {
		super();
		this.part = part;
		this.partE = partE;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Code")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "Parts")
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	@Column(name = "parts_e")
	public String getPartE() {
		return partE;
	}
	public void setPartE(String partE) {
		this.partE = partE;
	}
	
	
}
