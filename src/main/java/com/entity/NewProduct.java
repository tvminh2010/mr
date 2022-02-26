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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "newproduct",  uniqueConstraints={ @UniqueConstraint( columnNames = {"id"})} )
public class NewProduct {
	
	private String id;
	private String name;
	
	
	private String code;
	private String part;
	private Boolean disa;
	private String unit;

	
	public NewProduct(){
		
	}
	


	@Id

	@Column(name="id")
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}



	@Column(name="name")
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}



	@Column(name="code")
	public String getCode() {
		return code;
	}



   
	public void setCode(String code) {
		this.code = code;
	}



	@Column(name="part")
	public String getPart() {
		return part;
	}




	public void setPart(String part) {
		this.part = part;
	}



	@Column(name="disa")
	public Boolean getDisa() {
		return disa;
	}




	public void setDisa(Boolean disa) {
		this.disa = disa;
	}



	@Column(name="unit")
	public String getUnit() {
		return unit;
	}




	public void setUnit(String unit) {
		this.unit = unit;
	}






	
	
	

	
}
