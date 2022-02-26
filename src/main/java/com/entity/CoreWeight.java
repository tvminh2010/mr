package com.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="coreweight")
public class CoreWeight {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	
	@Column(name="itemcode")
	private String pt_part;
	@Column(name = "typetape")
	private String typecore;
	@Column(name = "coreweight")
	private Double coreweight;
	@Column(name = "rate")
	private Double rate;
	@Column(name = "vendor")
	private String vendor;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public Double getCoreweight() {
		return coreweight;
	}
	public void setCoreweight(Double coreweight) {
		this.coreweight = coreweight;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getTypecore() {
		return typecore;
	}
	public void setTypecore(String typecore) {
		this.typecore = typecore;
	}



	public CoreWeight() {
		super();
	}
	public String getPt_part() {
		return pt_part;
	}
	public void setPt_part(String pt_part) {
		this.pt_part = pt_part;
	}
	public CoreWeight(Integer id, String pt_part, String typecore, Double coreweight, Double rate) {
		super();
		this.id = id;
		this.pt_part = pt_part;
		this.typecore = typecore;
		this.coreweight = coreweight;
		this.rate = rate;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	@Override
	public String toString() {
		return "CoreWeight [id=" + id + ", pt_part=" + pt_part + ", typecore=" + typecore + ", coreweight=" + coreweight
				+ ", rate=" + rate + ", vendor=" + vendor + "]";
	}
	
	
	
	




	
}
