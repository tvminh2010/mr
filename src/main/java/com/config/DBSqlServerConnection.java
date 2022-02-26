package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.entity.WeightElectricQueue;

public class DBSqlServerConnection {
	private static final Logger logger = Logger.getLogger(DBSqlServerConnection.class);

private  ConfigNoSpring configNoSpring =new ConfigNoSpring();
public  Connection getConnectSqlserver() {
	  Connection conn = null;
      Config config = configNoSpring.getconfig();
          try {
   
               Class.forName(config.getJdbcdriverClassName());
               try {
				conn = DriverManager.getConnection("jdbc:jtds:sqlserver://" + config.getJdbcurl() +":" + config.getJdbcpost() 
				+ "/"+ config.getJdbcdb()+"",config.getJdbcusername(),config.getJdbcpassword());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.debug(e);

				e.printStackTrace();
			}
              } catch (ClassNotFoundException e) {
                  logger.info("Please include Classpath Where your Sqlserver Driver is located");
                
                  e.printStackTrace();
              }  
   
          
 
  
      return conn;
}
public void saveWeightElectricQueue(WeightElectricQueue w) {
	
	Connection conn= getConnectSqlserver();
	Statement stmt;
	try {
		stmt = conn.createStatement();
	
    stmt.execute("insert into WeightElectricQueue(transactiondate,qty) values ('"+ w.getTracsactiondate() +"',"+ w.getQty() +") ");
  
  
   
 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		logger.debug(e);

		e.printStackTrace();
	}
   
}
 
}
