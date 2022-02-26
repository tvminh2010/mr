package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "pt_mstr", uniqueConstraints={ @UniqueConstraint( columnNames = {"pt_part"})})
public class Product {

	public Product() {
		super();
	}

	private String pt_part;
	private String pt_desc1;
	private String pt_desc2;
	private String pt_um;
	private String pt_prod_line;
	private String pt_vend;
	
	@Transient
	private Double pt_package;
	@Id
	@Column(name = "pt_part")
	public String getPt_part() {
		return pt_part;
	}
	
	public void setPt_part(String pt_part) {
		this.pt_part = pt_part;
	}
	@Column(name = "pt_desc1")
	public String getPt_desc1() {
		return pt_desc1;
	}
	public void setPt_desc1(String pt_desc1) {
		this.pt_desc1 = pt_desc1;
	}
	@Column(name = "pt_desc2")
	public String getPt_desc2() {
		return pt_desc2;
	}
	public void setPt_desc2(String pt_desc2) {
		this.pt_desc2 = pt_desc2;
	}
	@Column(name = "pt_um")
	public String getPt_um() {
		return pt_um;
	}
	public void setPt_um(String pt_um) {
		this.pt_um = pt_um;
	}
	@Column(name = "pt_prod_line")
	public String getPt_prod_line() {
		return pt_prod_line;
	}
	public void setPt_prod_line(String pt_prod_line) {
		this.pt_prod_line = pt_prod_line;
	}
	@Column(name = "pt_vend")
	public String getPt_vend() {
		return pt_vend;
	}
	public void setPt_vend(String pt_vend) {
		this.pt_vend = pt_vend;
	}

	@Transient
	public Double getPt_package() {
		return pt_package;
	}

	public void setPt_package(Double pt_package) {
		this.pt_package = pt_package;
	}

	@Override
	public String toString() {
		return "Product [pt_part=" + pt_part + ", pt_desc1=" + pt_desc1
				+ ", pt_desc2=" + pt_desc2 + ", pt_um=" + pt_um
				+ ", pt_prod_line=" + pt_prod_line + ", pt_vend=" + pt_vend
				+ ", pt_package=" +"]";
	}

	public Product(String pt_part) {
		super();
		this.pt_part = pt_part;
	}
	
	
}
