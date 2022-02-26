package com.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="closetime")
public class CloseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name = "closetime")
//	@DateTimeFormat(pattern = "hh:mm:ss")
	private Time closetime;
	@Column(name = "name")
	private String name;
	
	
	
	
	public CloseTime(Integer id, Time closetime, String name) {
		super();
		this.id = id;
		this.closetime = closetime;
		this.name = name;
		
	}



	public CloseTime(Time closetime, String name) {
		super();
		this.closetime = closetime;
		this.name = name;
	}



	public CloseTime() {
		super();
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Time getClosetime() {
		return closetime;
	}



	public void setClosetime(Time closetime) {
		this.closetime = closetime;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}






	@Override
	public String toString() {
		return "CloseTime [id=" + id + ", closetime=" + closetime + ", name="
				+ name  + "]";
	}

	
	
}
