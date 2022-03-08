package com.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="coreweightserialno")
public class ReturnWeightNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="d")
	@Temporal(TemporalType.DATE)
	private Date d;
	@Column(name="serialno")
	private int number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public ReturnWeightNumber(int id, Date d, int number) {
		super();
		this.id = id;
		this.d = d;
		this.number = number;
	}
	public ReturnWeightNumber() {
		super();
	}
	
	
	
}
