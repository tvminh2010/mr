package com.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:config.properties" })
public class Config {
	 @Value("${pageSize}")
	 private Integer pageSize;
	 @Value("${dataExcel}")
	 private String dataExcel;
     @Value("${dataExcelreponse}")
	 private String dataExcelreponse;
     @Value("${dataExcelreturn}")
   	 private String dataExcelreturn;
     //postgres
 	@Value("${host}")
 	private String host;
 	@Value("${db}")
 	private String db;
 	@Value("${post}")
 	private String port;
 	@Value("${user}")
 	private String user;
 	@Value("${pas}")
 	private String pas;
	@Value("${returnJasper}")
	private String returnJasper;
	@Value("${printerName}")
	private String printerName;
	@Value("${driverClassName}")
	private String driverClassName;
	@Value("${jdbc.driverClassName}")
	private String jdbcdriverClassName;
	@Value("${jdbc.url}")
	private String jdbcurl;
	@Value("${jdbc.post}")
	private String jdbcpost;
	@Value("${jdbc.username}")
	private String jdbcusername;
	@Value("${jdbc.password}")
	private String jdbcpassword;

	@Value("${jdbc.db}")
	private String jdbcdb;
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getDataExcel() {
		return dataExcel;
	}

	public void setDataExcel(String dataExcel) {
		this.dataExcel = dataExcel;
	}


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPas() {
		return pas;
	}

	public void setPas(String pas) {
		this.pas = pas;
	}

	public String getReturnJasper() {
		return returnJasper;
	}

	public void setReturnJasper(String returnJasper) {
		this.returnJasper = returnJasper;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getJdbcdriverClassName() {
		return jdbcdriverClassName;
	}

	public void setJdbcdriverClassName(String jdbcdriverClassName) {
		this.jdbcdriverClassName = jdbcdriverClassName;
	}

	public String getJdbcurl() {
		return jdbcurl;
	}

	public void setJdbcurl(String jdbcurl) {
		this.jdbcurl = jdbcurl;
	}

	public String getJdbcpost() {
		return jdbcpost;
	}

	public void setJdbcpost(String jdbcpost) {
		this.jdbcpost = jdbcpost;
	}

	public String getJdbcusername() {
		return jdbcusername;
	}

	public void setJdbcusername(String jdbcusername) {
		this.jdbcusername = jdbcusername;
	}

	public String getJdbcpassword() {
		return jdbcpassword;
	}

	public void setJdbcpassword(String jdbcpassword) {
		this.jdbcpassword = jdbcpassword;
	}

	public String getJdbcdb() {
		return jdbcdb;
	}

	public void setJdbcdb(String jdbcdb) {
		this.jdbcdb = jdbcdb;
	}

	public String getDataExcelreponse() {
		return dataExcelreponse;
	}

	public void setDataExcelreponse(String dataExcelreponse) {
		this.dataExcelreponse = dataExcelreponse;
	}

	public String getDataExcelreturn() {
		return dataExcelreturn;
	}

	public void setDataExcelreturn(String dataExcelreturn) {
		this.dataExcelreturn = dataExcelreturn;
	}

	@Override
	public String toString() {
		return "Config [pageSize=" + pageSize + ", dataExcel=" + dataExcel + ", dataExcelreponse=" + dataExcelreponse
				+ ", dataExcelreturn=" + dataExcelreturn + ", host=" + host + ", db=" + db + ", port=" + port
				+ ", user=" + user + ", pas=" + pas + ", returnJasper=" + returnJasper + ", printerName=" + printerName
				+ ", driverClassName=" + driverClassName + ", jdbcdriverClassName=" + jdbcdriverClassName + ", jdbcurl="
				+ jdbcurl + ", jdbcpost=" + jdbcpost + ", jdbcusername=" + jdbcusername + ", jdbcpassword="
				+ jdbcpassword + ", jdbcdb=" + jdbcdb + "]";
	}




	 
}
