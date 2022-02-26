package com.entity;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="receiptscomp")
public class ReceiptComp {
	@Id
	@Column(name = "id")
	private String id;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="workorder")
	private WorkOrder wo;
	@Column(name="date")
	private Timestamp date;
	@Column(name="note")
	private String note;

	@OneToMany(mappedBy="receipt",fetch = FetchType.EAGER)

	private List<DetailComp> lDetailComp;
	
	@Column(name="type")
	private TypeComp type;
	//
	@Column(name="iswait")
	private Boolean isWait;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="turn")
	private Turn turn;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="us")
	private User us;
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name="closetime")
	private CloseTime closetime;
	@Column(name="ispending")
	private Boolean isPending;
	@Column(name="deleteddate")
	private Timestamp deleteddate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public WorkOrder getWo() {
		return wo;
	}
	public void setWo(WorkOrder wo) {
		this.wo = wo;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public ReceiptComp(String id, WorkOrder wo, Timestamp date, String note) {
		super();
		this.id = id;
		this.wo = wo;
		this.date = date;
		this.note = note;
	}
	public ReceiptComp() {
		super();
	}

	public List<DetailComp> getlDetailComp() {
		return lDetailComp;
	}
	public void setlDetailComp(List<DetailComp> lDetailComp) {
		this.lDetailComp = lDetailComp;
	}
	public TypeComp getType() {
		return type;
	}
	public void setType(TypeComp type) {
		this.type = type;
	}


	public Boolean getIsWait() {
		return isWait;
	}
	public void setIsWait(Boolean isWait) {
		this.isWait = isWait;
	}
	public Turn getTurn() {
		return turn;
	}
	public void setTurn(Turn  turn) {
		this.turn = turn;
	}
	public User getUs() {
		return us;
	}
	public void setUs(User us) {
		this.us = us;
	}
	public ReceiptComp(String id) {
		super();
		this.id = id;
	}
	@Override
	public String toString() {
		return "ReceiptComp [id=" + id + ", wo=" + wo + ", date=" + date
				+ ", note=" + note + ", lDetailComp=" + lDetailComp + ", type="
				+ type + ", isWait=" + isWait + ", turn=" + turn + ", us=" + us
				+ "]";
	}
	public CloseTime getClosetime() {
		return closetime;
	}
	public void setClosetime(CloseTime closetime) {
		this.closetime = closetime;
	}
	public Boolean getIsPending() {
		return isPending;
	}
	public void setIsPending(Boolean isPending) {
		this.isPending = isPending;
	}
	public Timestamp getDeleteddate() {
		return deleteddate;
	}
	public void setDeleteddate(Timestamp deleteddate) {
		this.deleteddate = deleteddate;
	}
	
	
}
