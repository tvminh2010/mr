package com.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coreweightlog")
public class ReturnWeightLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(name="wo")
	private String wo;
	@Column(name="ptpart")
	private String  model;
	@Column(name="weight")
	private Double weight;
	@Column(name="weightnet")
	private Double weightnet;
	@Column(name="qty")
	private Double qty;
	@Column(name="createdate")
	private Timestamp createdate;
	@Column(name="remark")
	private String remark;
	@Column(name="lotno")
	private String lotno;
	@Column(name="serialold")
	private String serialold;
	@Column(name="serialnew")
	private String serialnew;
	@Column(name="ptdesc1")
	private String ptdesc1;
	@Column(name="ptum")
	private String ptum;
	@Column(name="turndate")
	private Timestamp turndate;
	@Column(name="status")
	private Boolean status;
	@Column(name="woname")
	private String woname;
	@Column(name="ptdesc2")
	private String ptdesc2;
	@Column(name="vendor")
	private String vendor;
	@Column(name="receivingdate")
	private String receivingdate;
	public ReturnWeightLog(Long id, String wo, String model, Double qty, Timestamp createdate) {
		super();
		this.id = id;
		this.wo = wo;
		this.model = model;
		this.qty = qty;
		this.createdate = createdate;
	}
	public ReturnWeightLog() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWo() {
		return wo;
	}
	public void setWo(String wo) {
		this.wo = wo;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public String getSerialold() {
		return serialold;
	}
	public void setSerialold(String serialold) {
		this.serialold = serialold;
	}
	public String getSerialnew() {
		return serialnew;
	}
	public void setSerialnew(String serialnew) {
		this.serialnew = serialnew;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getWeightnet() {
		return weightnet;
	}
	public void setWeightnet(Double weightnet) {
		this.weightnet = weightnet;
	}
	public String getPtdesc1() {
		return ptdesc1;
	}
	public void setPtdesc1(String ptdesc1) {
		this.ptdesc1 = ptdesc1;
	}
	public String getPtum() {
		return ptum;
	}
	public void setPtum(String ptum) {
		this.ptum = ptum;
	}
	public Timestamp getTurndate() {
		return turndate;
	}
	public void setTurndate(Timestamp turndate) {
		this.turndate = turndate;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getWoname() {
		return woname;
	}
	public void setWoname(String woname) {
		this.woname = woname;
	}
	public String getPtdesc2() {
		return ptdesc2;
	}
	public void setPtdesc2(String ptdesc2) {
		this.ptdesc2 = ptdesc2;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}


	public String getReceivingdate() {
		return receivingdate;
	}
	public void setReceivingdate(String receivingdate) {
		this.receivingdate = receivingdate;
	}
	@Override
	public String toString() {
		return "ReturnWeightLog [id=" + id + ", wo=" + wo + ", model=" + model + ", weight=" + weight + ", weightnet="
				+ weightnet + ", qty=" + qty + ", createdate=" + createdate + ", remark=" + remark + ", lotno=" + lotno
				+ ", serialold=" + serialold + ", serialnew=" + serialnew + ", ptdesc1=" + ptdesc1 + ", ptum=" + ptum
				+ ", turndate=" + turndate + ", status=" + status + ", woname=" + woname + ", ptdesc2=" + ptdesc2
				+ ", vendor=" + vendor + "]";

	}

	
	
}
