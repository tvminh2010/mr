package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DBPosgressConnection {
	private static final Logger logger = Logger.getLogger(DBPosgressConnection.class);
	   @Autowired
	   private  Config config;
public  Connection getConnectPostgres() {
	  Connection conn = null;
 
          try {
   
               Class.forName(config.getDriverClassName());
               try {
				conn = DriverManager.getConnection("jdbc:postgresql://" + config.getHost() +":" + config.getPort() + "/"+ config.getDb() +"",config.getUser(),config.getPas());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				 logger.info(e.toString());
			}
              } catch (ClassNotFoundException e) {
                  logger.info("Please include Classpath Where your MySQL Driver is located");
                
                  logger.info(e.toString());
              }  
   
          
 
  
      return conn;
}
public HashMap<String,String> getItemCode(String serial) {
	String itemcode = "";
	HashMap<String,String> l = new HashMap<String,String>();
	Connection conn= getConnectPostgres();
	Statement stmt;
	try {
		stmt = conn.createStatement();
	
		ResultSet rs = stmt.executeQuery("SELECT DISTINCT st.product_no as ma_sp, p.product_name as ten_sp ,pi.lot_no as lot_no "
				+" FROM stock_out_serial as st"
                +" INNER JOIN product_instance AS pi ON st.serial_no = pi.serial_no"
                +" INNER JOIN m_product_master AS p ON p.product_no = pi.product_no"
				+ " WHERE st.serial_no like '"+ serial +"' limit 1");
		
	/*		ResultSet rs = stmt.executeQuery("SELECT DISTINCT ma_sp as ma_sp, ten_sp as ten_sp ,lot_no as lot_no "
					+" FROM v_test2"
					+ " WHERE serial_no like '"+ serial +"' limit 1");*/
  
  
    boolean k = false;
    while (rs.next()) {
 	   k=true;
 	   l.put("masp",rs.getString("ma_sp"));
 	  l.put("tensp",rs.getString("ten_sp"));
 	 l.put("lotno",rs.getString("lot_no"));
 	   logger.info("itemcode" + "\n" +rs.getString("lot_no") );
 	 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		 logger.info(e.toString());
	}
    return l;
}
public String getLotno(String serial) {
	String lot_no = "";
	Connection conn= getConnectPostgres();
	Statement stmt;
	try {
		stmt = conn.createStatement();
	
		ResultSet rs = stmt.executeQuery("SELECT DISTINCT pi.lot_no as lot_no "
				+" FROM stock_out_serial as st"
                +" INNER JOIN product_instance AS pi ON st.serial_no = pi.serial_no"
                +" INNER JOIN m_product_master AS p ON p.product_no = pi.product_no"
				+ " WHERE st.serial_no like '"+ serial +"' limit 1");
	/*	ResultSet rs = stmt.executeQuery("SELECT lot_no as lot_no "
				+" FROM v_test2"
				+ " WHERE serial_no like '"+ serial +"' limit 1");*/
  
  
    boolean k = false;
    while (rs.next()) {
 	   k=true;
 	  lot_no = rs.getString("lot_no");
 	   logger.info("itemcode" + "\n" +lot_no );
 	 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		 logger.info(e.toString());
	}
    return lot_no;
}   
}
