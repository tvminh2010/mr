package com.entity;





//import javax.persistence.CascadeType;
import java.math.BigDecimal;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="detailcomp")
public class DetailComp {
	@Id
	
	@Column(name="id")
	private String id;
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name="receiptid")
	@JsonIgnore 
	private ReceiptComp receipt;
	@Override
	public String toString() {
		return "DetailComp [id=" + id + ", receipt=" + receipt + ", model="
				+ model + ", qty=" + qty + ", ps_qty_per=" + ps_qty_per
				+ ", totalRequestBS=" + totalRequestBS + ", totalRequestSetup="
				+ totalRequestSetup + ", totalResponse=" + totalResponse
				+ ", totalReturn=" + totalReturn + ", plan=" + plan + "]";
	}





	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="pt_id")
	private Product model;
	@Column(name="qty")
	private Double qty;
	
	

	
	
	public DetailComp(String id, ReceiptComp receipt, Product model,
			Double qty) {
		super();
		this.id = id;
		this.receipt = receipt;
		this.model = model;
		this.qty = qty;
	}
	public DetailComp() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public ReceiptComp getReceipt() {
		return receipt;
	}
	public void setReceipt(ReceiptComp receipt) {
		this.receipt = receipt;
	}



		
	
	@Transient
		private  Double ps_qty_per;
	@Transient
		private Double totalRequestBS;
	@Transient
		private Double totalRequestSetup;
	@Transient
		private Double totalResponse;
	@Transient
		private Double totalReturn;
	@Transient
		private Double plan;
		
	@Transient
	private Double qtyEarly;

	
	
    

	public Double getQtyEarly() {
		return qtyEarly;
	}
	public void setQtyEarly(Double qtyEarly) {
		this.qtyEarly = qtyEarly;
	}
	public Double getPs_qty_per() {
		return ps_qty_per;
	}

	public void setPs_qty_per(Double ps_qty_per) {
		this.ps_qty_per = ps_qty_per;
	}

	public Double getTotalRequestBS() {
		return totalRequestBS;
	}

	public void setTotalRequestBS(Double totalRequestBS) {
		this.totalRequestBS = totalRequestBS;
	}

	public Double getTotalRequestSetup() {
		return totalRequestSetup;
	}

	public void setTotalRequestSetup(Double totalRequestSetup) {
		this.totalRequestSetup = totalRequestSetup;
	}

	public Double getTotalResponse() {
		return totalResponse;
	}

	public void setTotalResponse(Double totalResponse) {
		this.totalResponse = totalResponse;
	}

	public Double getTotalReturn() {
		return totalReturn;
	}
	

	public void setTotalReturn(Double totalReturn) {
		this.totalReturn = totalReturn;
	}

	public Double getPlan() {
		return plan;
	}

	public void setPlan(Double plan) {
		this.plan = plan;
	}


	

	
	
	
	



	
	
}
