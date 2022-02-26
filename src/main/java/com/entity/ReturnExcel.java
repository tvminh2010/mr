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
@Table(name="returnexcellog")
public class ReturnExcel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@Column(name = "woid")
	private String woid;
	@Column(name = "woname")
	private String woname;
	@Column(name = "model")
	private String model;
	@Column(name = "line")
	private String line;
	@Column(name = "pathexcel")
	private String pathexcel;
	@Column(name = "createdate")
	private Timestamp createdate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPathexcel() {
		return pathexcel;
	}
	public void setPathexcel(String pathexcel) {
		this.pathexcel = pathexcel;
	}

	public ReturnExcel() {
		super();
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public String getWoid() {
		return woid;
	}
	public void setWoid(String woid) {
		this.woid = woid;
	}
	public String getWoname() {
		return woname;
	}
	public void setWoname(String woname) {
		this.woname = woname;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	
	
	
	

	
	
}
