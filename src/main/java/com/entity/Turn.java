package com.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="turn")
public class Turn {
	@Id
	
	@Column(name="id")
	private String id;
	@Column(name = "d")
	private Timestamp d;

	@Column(name = "linkdownloadbs")
	private String linkdownloadbs;
	@Column(name = "linkdownloadsetup")
	private String linkdownloadsetup;
	@Column(name = "downloadedbs")
private Boolean downloadedbs;
	@Column(name = "downloadedsetup")
private Boolean downloadedsetup;
	
	

	@Column(name = "show")
	private Boolean show;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Turn(String id, Timestamp d, String linkdownloadbs,
			String linkdownloadsetup) {
		super();
		this.id = id;
		this.d = d;
		this.linkdownloadbs = linkdownloadbs;
		this.linkdownloadsetup = linkdownloadsetup;
	}

	public Turn(String id) {
		super();
		this.id = id;
	}

	public Timestamp getD() {
		return d;
	}

	public void setD(Timestamp d) {
		this.d = d;
	}

	public String getLinkdownloadbs() {
		return linkdownloadbs;
	}

	public void setLinkdownloadbs(String linkdownloadbs) {
		this.linkdownloadbs = linkdownloadbs;
	}

	public String getLinkdownloadsetup() {
		return linkdownloadsetup;
	}

	public void setLinkdownloadsetup(String linkdownloadsetup) {
		this.linkdownloadsetup = linkdownloadsetup;
	}



	public Boolean getDownloadedbs() {
		return downloadedbs == null || downloadedbs == false?false:true;
	}

	public void setDownloadedbs(Boolean downloadedbs) {
		this.downloadedbs = downloadedbs;
	}

	public Boolean getDownloadedsetup() {
		return downloadedsetup ==null || downloadedsetup==false?false:true;
	}

	public void setDownloadedsetup(Boolean downloadedsetup) {
		this.downloadedsetup = downloadedsetup;
	}

	public Turn() {
		super();
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return "Turn [id=" + id + ", d=" + d + ", linkdownloadbs=" + linkdownloadbs + ", linkdownloadsetup="
				+ linkdownloadsetup + ", downloadedbs=" + downloadedbs + ", downloadedsetup=" + downloadedsetup
				+ ", show=" + show + "]";
	}



	
	
	
	
	
	
}
