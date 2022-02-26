package com.entity;

import java.text.SimpleDateFormat;
import java.sql.Date;

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
@Table(name="workorders")
public class WorkOrder {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="name")
	private String name;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="line")
	private LineNo line;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="model")
	private Product model;
	@Column(name="qty")
	private Double qty;
	@Column(name="status")
	private Integer status;
	@Column(name="createdate")
	private Date createdate;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="us")
	private User us;
	public WorkOrder(String id, String name, LineNo line, Product model,
			Double qty, int status, Date createdate) {
		super();
		this.id = id;
		this.name = name;
		this.line = line;
		this.model = model;
		this.qty = qty;
		this.status = status;
		this.createdate = createdate;
	}
	public WorkOrder() {
		super();
	}
	public WorkOrder(String id, String name, LineNo line, Product model,
			Double qty, int status) {
		super();
		this.id = id;
		this.name = name;
		this.line = line;
		this.model = model;
		this.qty = qty;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LineNo getLine() {
		return line;
	}
	public void setLine(LineNo line) {
		this.line = line;
	}
	public Product getModel() {
		return model;
	}
	public void setModel(Product model) {
		this.model = model;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	public void setNameDefault(int iTicketId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String receiptTail = "0000" + new Integer(iTicketId).toString();
        String m_iTicketReceipt = "WO" + simpleDateFormat.format(new java.util.Date()) + receiptTail.substring(receiptTail.length()-4);
        this.name = m_iTicketReceipt;
	}
	public User getUs() {
		return us;
	}
	public void setUs(User us) {
		this.us = us;
	}

	
}
