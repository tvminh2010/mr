package com.entity;

public class CoreWeightInfo {

	private CoreWeight cw;
	private Product p;
	public CoreWeightInfo(CoreWeight cw, Product p) {
		super();
		this.cw = cw;
		this.p = p;
	}
	public CoreWeight getCw() {
		return cw;
	}
	public void setCw(CoreWeight cw) {
		this.cw = cw;
	}
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public CoreWeightInfo() {
		
	}
	
	
}
