package com.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="weightelectricqueue")
public class WeightElectricQueue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name = "transactiondate")
//	@DateTimeFormat(pattern = "hh:mm:ss")
	private Timestamp tracsactiondate;
	@Column(name = "qty")
	private Double qty;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getTracsactiondate() {
		return tracsactiondate;
	}
	public void setTracsactiondate(Timestamp tracsactiondate) {
		this.tracsactiondate = tracsactiondate;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "WeightElectricQueue [id=" + id + ", tracsactiondate=" + tracsactiondate + ", qty=" + qty + "]";
	}
	public WeightElectricQueue(Integer id, Timestamp tracsactiondate, Double qty) {
		super();
		this.id = id;
		this.tracsactiondate = tracsactiondate;
		this.qty = qty;
	}
	public WeightElectricQueue() {
		super();
	}
	
	
	


	
	
}
