package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fg")
public class FG {

	
	private Integer id;
	private String vtepartname;
	private String fgcode;
	private String customercode;
	private String customer;
	
	
	public FG() {

	}
	public FG(String vtepartname, String fgcode, String customercode, String customer) {
		super();
		this.vtepartname = vtepartname;
		this.fgcode = fgcode;
		this.customercode = customercode;
		this.customer = customer;
	}
	public FG(Integer id, String vtepartname, String fgcode, String customercode, String customer) {
		super();
		this.id = id;
		this.vtepartname = vtepartname;
		this.fgcode = fgcode;
		this.customercode = customercode;
		this.customer = customer;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="vtepartname")
	public String getVtepartname() {
		return vtepartname;
	}
	public void setVtepartname(String vtepartname) {
		this.vtepartname = vtepartname;
	}
	@Column(name="fgcode")
	public String getFgcode() {
		return fgcode;
	}
	public void setFgcode(String fgcode) {
		this.fgcode = fgcode;
	}
	@Column(name="customercode")
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	@Column(name="customer")
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
}
