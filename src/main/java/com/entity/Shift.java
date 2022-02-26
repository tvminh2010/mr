package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "m_shift" ,uniqueConstraints={ @UniqueConstraint( columnNames = {"Code"})} )
public class Shift {

	
	private int id;
    private String shift;
    public Shift(){
    	
    }
    
	public Shift(int id, String shift) {
	
		this.id = id;
		this.shift = shift;
	}
	
	@Id
	@Column(name = "Code")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "Shift")
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
    
    
}
