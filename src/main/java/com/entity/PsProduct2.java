package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "ps_mstr")
public class PsProduct2{

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Id
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	@JoinColumn(name="ps_par")
	@NotFound(action = NotFoundAction.IGNORE)
	private Product ps_par;
	@Id
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	@JoinColumn(name="ps_comp")
	@NotFound(action = NotFoundAction.IGNORE)
	private Product ps_comp;
	@Column(name = "ps_qty_per")
	private Double ps_qty_per;
	
	
	
	public PsProduct2(Product ps_par, Product ps_comp, Double ps_qty_per) {
		super();
		this.ps_par = ps_par;
		this.ps_comp = ps_comp;
		this.ps_qty_per = ps_qty_per;
	}
	public PsProduct2() {
		super();
	}
	public Product getPs_par() {
		return ps_par;
	}
	public void setPs_par(Product ps_par) {
		this.ps_par = ps_par;
	}
	public Product getPs_comp() {
		return ps_comp;
	}
	public void setPs_comp(Product ps_comp) {
		this.ps_comp = ps_comp;
	}
	public Double getPs_qty_per() {
		return ps_qty_per;
	}
	public void setPs_qty_per(Double ps_qty_per) {
		this.ps_qty_per = ps_qty_per;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PsProduct2(Integer id, Product ps_par, Product ps_comp, Double ps_qty_per) {
		super();
		this.id = id;
		this.ps_par = ps_par;
		this.ps_comp = ps_comp;
		this.ps_qty_per = ps_qty_per;
	}
	
	
}
