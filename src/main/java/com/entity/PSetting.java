package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="p_setting")
public class PSetting {

	private int id;
	private String namevn;
	private String nameen;
	private float value;
	private String descript;
	public PSetting(int id, String namevn, String nameen, float value, String descript) {
		super();
		this.id = id;
		this.namevn = namevn;
		this.nameen = nameen;
		this.value = value;
		this.descript = descript;
	}
	
	public PSetting() {
		super();
	}
	
	public PSetting(String namevn, String nameen, float value, String descript) {
		super();
		this.namevn = namevn;
		this.nameen = nameen;
		this.value = value;
		this.descript = descript;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "namevn")
	public String getNamevn() {
		return namevn;
	}

	public void setNamevn(String namevn) {
		this.namevn = namevn;
	}
	@Column(name = "nameen")
	public String getNameen() {
		return nameen;
	}

	public void setNameen(String nameen) {
		this.nameen = nameen;
	}
	@Column(name = "value")
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	@Column(name = "description")
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	@Override
	public String toString() {
		return "PSetting [id=" + id + ", namevn=" + namevn + ", nameen=" + nameen + ", value=" + value + ", descript="
				+ descript + "]";
	}
	
	
	
}
