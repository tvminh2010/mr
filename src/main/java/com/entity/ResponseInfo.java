package com.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


public class ResponseInfo {
 
	
  private String wo;
  private Integer stt;
  private String line;
  private String model;
  private String itemName;
  private String itemNumber;
  private String itemCode;
  private String unit;
  private String timeRequired;
  private Double qtyplan;
  private String note;
  private Double qtyreal;

public String getWo() {
	return wo;
}
public void setWo(String wo) {
	this.wo = wo;
}
public Integer getStt() {
	return stt;
}
public void setStt(Integer stt) {
	this.stt = stt;
}
public String getLine() {
	return line;
}
public void setLine(String line) {
	this.line = line;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getItemCode() {
	return itemCode;
}
public void setItemCode(String itemCode) {
	this.itemCode = itemCode;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public String getTimeRequired() {
	return timeRequired;
}
public void setTimeRequired(String timeRequired) {
	this.timeRequired = timeRequired;
}
public Double getQtyplan() {
	return qtyplan;
}
public void setQtyplan(Double qtyplan) {
	this.qtyplan = qtyplan;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public Double getQtyreal() {
	return qtyreal;
}
public void setQtyreal(Double qtyreal) {
	this.qtyreal = qtyreal;
}

public ResponseInfo() {
	super();
}
@Override
public String toString() {
	return "ResponseInfo [wo=" + wo + ", stt=" + stt + ", line=" + line + ", model=" + model + ", itemName=" + itemName
			+ ", itemCode=" + itemCode + ", unit=" + unit + ", timeRequired=" + timeRequired + ", qtyplan=" + qtyplan
			+ ", note=" + note + ", qtyreal=" + qtyreal + "]";
}
public String getItemNumber() {
	return itemNumber;
}
public void setItemNumber(String itemNumber) {
	this.itemNumber = itemNumber;
}


  
  
  
}
