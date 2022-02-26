package com.controller;
import java.awt.print.PrinterJob;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PrinterName;
//import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.FG;

import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;

@Controller
@RequestMapping(value={"/print","murata"})
@PropertySource("classpath:config.properties")
public class LoadJasperReport{
	private static final Logger logger = Logger.getLogger(LoadJasperReport.class);

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
	@Value("${printerName}")
	private String printerName;
	
	@Value("${murata}")
	private String dirJasper;

	@Value("${canonthai}")
	private String dirJasper1;
	
	@Value("${userpn}")
	private String userpn;
	@Value("${passpn}")
	private String passpn;
	
    @Autowired
	private com.dao.FGDao FGDao;
  
	
	
    @RequestMapping(method = RequestMethod.GET)
	   public String index(ModelMap model)  {
		
		
		 
	      return "index";
	 }
	
	 @RequestMapping(value="customercode",method = RequestMethod.GET)
	   public String customercode(ModelMap model,@ModelAttribute("del") String del,@ModelAttribute("delall") String delall,HttpServletRequest request)  {
		
			 
		 
		 
		 if(del!=null && del.length()>0){
			 FG fg = FGDao.findbyid(del);
			 FGDao.del(fg);
		 }
		 if(delall!=null && delall.length()>0){
			 FG fg = new FG();
				List<String> ids = getLid(delall);
			
				for(int i= 0;i<ids.size();i++){
					
				
				 
				
					 fg = FGDao.findbyid(ids.get(i).toString());
					 if(fg!=null){
				
				
					FGDao.del(fg);
				}
			  
		 }
		 }
		 List<FG> listfg = FGDao.getList();
		 model.addAttribute("listfg", listfg);
		 return "customercode";
	
		 
	      
	 }
	 @RequestMapping(value="loginpn",method = RequestMethod.POST)
	 public String postlogincustomercode(ModelMap model,@ModelAttribute("userpn") String userpn1,
			 @ModelAttribute("passpn") String passpn1){
		
			 List<FG> listfg = FGDao.getList();
			 model.addAttribute("listfg", listfg);
			 return "customercode";
		
		 
	 }
		private List<String> getLid(String lid){
			List<String> ids = new ArrayList<String>();
			//String lid = processlistinspection.getLsta().g().getId();
			if(lid!=null && lid!=""){
				
				int j = lid.length();
			    for(int i=0;i<j;i++){
			    	
			    	String id= "";
					
					if(lid.indexOf(",")==-1){
						id = lid;
						i=j;
					}else{
					id = lid.substring(0, lid.indexOf(","));
					lid=lid.substring(lid.indexOf(",")+1, lid.length());
					i = i + id.length();
					}
					
						
						ids.add(id);
				    }
					
					
			    
			  
			}
			  return ids;
		}
	 @RequestMapping(value="customercode",method = RequestMethod.POST)
	   public String addcustomercode(ModelMap model,@ModelAttribute("id") Integer id,
			   @ModelAttribute("vtepartname") String vtepartname,
			   @ModelAttribute("fgcode") String fgcode,
			   @ModelAttribute("customercode") String customercode,
			   @ModelAttribute("customer") String customer)  {
		Boolean k=null;
		 if(id!=null & id>0)//edit
		 {
			 FG fg = new FG(id,vtepartname,fgcode,customercode,customer);
			 FGDao.update(fg);
		 }
		 else{
			 FG fg = new FG(vtepartname,fgcode,customercode,customer);
			  k = FGDao.insert(fg);
		 }
		 
		 model.addAttribute("k", k);
		 List<FG> listfg = FGDao.getList();
		 model.addAttribute("listfg", listfg);
	
		 
	      return "customercode";
	 }


	   @RequestMapping(value="murata",method = RequestMethod.POST)
	   public String murata(ModelMap model,@ModelAttribute("SerialNo") String serialno
			   )  {
		 
	   
	      Connection conn = null;
	      try {
	          try {
	   
	               Class.forName("org.postgresql.Driver");
	               conn = DriverManager.getConnection("jdbc:postgresql://" + host +":" + port + "/"+ db +"",user,pas);
	              } catch (ClassNotFoundException e) {
	                  System.out.println("Please include Classpath Where your MySQL Driver is located");
	                  model.addAttribute("error", 1);
	      			logger.debug(e);

	                  e.printStackTrace();
	              }  
	   
	          
	   
	       if (conn != null)
	       {
	           System.out.println("Database Connected");
	           
	         
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery("SELECT ma_sp,ten_sp FROM v_test2 WHERE serial_no like '"+ serialno +"' limit 1");
	           String fgcode= "";
	           String vtepartcode="";
	           boolean k = false;
	           while (rs.next()) {
	        	   k=true;
	        	    vtepartcode = rs.getString("ten_sp");
	        	    fgcode = rs.getString("ma_sp");
	        	   System.out.println(vtepartcode + "\n" +fgcode );
	        	 }
	           if(k){
	      HashMap<String,Object> hmParams=new HashMap<String,Object>();
	   
          hmParams.put("SerialNo", serialno);
          
          String namekh="";
          namekh = FGDao.findbyfgcode(fgcode, "MURATA");
         if(namekh==null){
        	 namekh= FGDao.findbyvtepartcode(vtepartcode,  "MURATA");
         }
         if(namekh!=null){
         hmParams.put("NameKHYC", namekh);
	     
	      File reportFile = new File(dirJasper);
	      // If compiled file is not found, then compile XML template
	      if (!reportFile.exists()) {
	    	  System.out.println(" Not found file jrxml ");
	    	  model.addAttribute("error", 3);
	              //   JasperCompileManager.compileReportToFile("D:/java/projectee/jasper1/src/main/webapp/WEB-INF/jasper/murata.jrxml","D:/java/projectee/jasper1/src/main/webapp/WEB-INF/jasper/murata.jasper");
	          }else{
	        	  System.out.println(" 1Not found file jrxml ");
	        //  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	         try{
	        	  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	         
	          JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,hmParams,conn);
	          jasperprint.setOrientation(OrientationEnum.PORTRAIT);
	         // JasperExportManager.exportReportToPdfFile(jasperprint,"D:/java/projectee/jrp/WebContent/WEB-INF/jasper/murata1.pdf");
	          System.out.println("giai doan print");
	      
	          
	         
	          
	          
	          
	          System.out.println("printing ...");
	    
	          
	        
	          

	          JRPrintServiceExporter exporter;
	          
	          PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
	        //  printRequestAttributeSet.add(MediaSizeName.);
	          printRequestAttributeSet.add(new Copies(1));
	      // printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
	         // int width = Math.round(MediaSize.ISO..getX(MediaSize.MM));
	         // int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));
	          printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 100, 50, MediaPrintableArea.MM));
	          //
	      
	       
	          PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
	          
	         /* Java AWT Printing will not find a printer via a path if it is not registered to 
	          the Windows / Active Directory user that is running the printing application. 
	          You must register the printer path through Windows "Devices and Printers" as 
	          a printer for that user for it to be visible. Then you must run lookupPrintServices 
	          to see the list of printers available and retrieve the proper PrintService by the exact name String listed*/
	          
	        
	          
	          javax.print.PrintService[] service = PrinterJob.lookupPrintServices(); 
	      

	          int count = service.length;
	          int i=0;
	          for ( i = 0; i < count; i++) {
	            
	              System.out.println(service[i].getName());
	              if(service[i].getName().equals(printerName))
	              {
	            		  printServiceAttributeSet.add(new PrinterName(printerName, null));
	            		  break;
	              }
	          }
	          if(i==count){
	        	  System.out.println(" Not found printer");
		    	  model.addAttribute("error", 4);
		    	  model.addAttribute("msg",printerName);
	          }else{
	          
	        	  System.out.println("1 Not found printer");
	           exporter = new JRPrintServiceExporter();
	        
	         exporter.setExporterInput(new SimpleExporterInput(jasperprint));
	          SimplePrintServiceExporterConfiguration expConfig = new SimplePrintServiceExporterConfiguration();
	          
	          expConfig.setPrintRequestAttributeSet(printRequestAttributeSet);
	          expConfig.setPrintServiceAttributeSet(printServiceAttributeSet);
	          expConfig.setDisplayPageDialog(Boolean.FALSE);
	          expConfig.setDisplayPrintDialog(Boolean.FALSE);
	          
	          exporter.setConfiguration(expConfig);
	          exporter.exportReport();
	          conn.close();
	          
	          System.out.println(" success ");
	           model.addAttribute("error", 7);
	          
	          }
	         }catch(JRException e){
	        	 System.out.println(" ko load file jrxml ");
		           model.addAttribute("error", 6);
		           model.addAttribute("msg", reportFile.getPath());
		           conn.close();
		   
	         }
	          }
	      
         }else{
        	 System.out.println("ko co customer part name ");
	           model.addAttribute("error", 8);
	           model.addAttribute("msg"," "+vtepartcode + " " +fgcode);
	           conn.close();
         }
	      
	           }else{
		    	   System.out.println("ko co du lieu ");
		           model.addAttribute("error", 5);
		           model.addAttribute("msg", serialno);
		           conn.close();
		       }
	       }
	       else
	       {
	           System.out.println(" connection Failed ");
	           model.addAttribute("error", 2);
	           model.addAttribute("msg", "jdbc:postgresql://" + host +":" + port + "/"+ db );
	          
	       }
	      
	       
	      }catch(Exception e){
	    	  
	      }
	        //  model.addAttribute("namekh", namekh);
	 
	     
	      return "index";
	   }
	   @RequestMapping(value="canonthai",method = RequestMethod.GET)
	   public String getcanonthai(ModelMap model)  {
		   System.out.println("Get Canonthai");
		
		 
	      return "canonthai";
	 }
	
	   @RequestMapping(value="canonthai",method = RequestMethod.POST)
	   public String canonthai(ModelMap model,@ModelAttribute("SerialNo") String serialno
			   )  {
		 
	    System.out.println("Start");
	      Connection conn = null;
	      try {
	          try {
	   
	               Class.forName("org.postgresql.Driver");
	               conn = DriverManager.getConnection("jdbc:postgresql://" + host +":" + port + "/"+ db +"",user,pas);
	              } catch (Exception e) {
	      			logger.debug(e);

	            	  e.printStackTrace();
	                  System.out.println("Please include Classpath Where your MySQL Driver is located");
	                  model.addAttribute("error", 1);
	                 
	              }  
	   
	           
	  
	       if (conn != null)
	       {
	           System.out.println("Database Connected");
	           
	         
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery("SELECT ma_sp,ten_sp FROM v_test2 WHERE serial_no like '"+ serialno +"' limit 1");
	           String fgcode= "";
	           String vtepartcode="";
	           boolean k = false;
	           while (rs.next()) {
	        	   k=true;
	        	    vtepartcode = rs.getString("ten_sp");
	        	    fgcode = rs.getString("ma_sp");
	        	   System.out.println(vtepartcode + "\n" +fgcode );
	        	 }
	           if(k){
	      HashMap<String,Object> hmParams=new HashMap<String,Object>();
	    	  
          hmParams.put("SerialNo", serialno);
          
          String namekh="";
          namekh = FGDao.findbyfgcode(fgcode, "CANONTHAI");
         if(namekh==null){
        	 namekh= FGDao.findbyvtepartcode(vtepartcode,  "CANONTHAI");
         }
         if(namekh!=null){
         hmParams.put("NameKHYC", namekh);
	     
	      File reportFile = new File(dirJasper1);
	      // If compiled file is not found, then compile XML template
	      if (!reportFile.exists()) {
	    	  System.out.println(" Not found file jrxml ");
	    	  model.addAttribute("error", 3);
	              //   JasperCompileManager.compileReportToFile("D:/java/projectee/jasper1/src/main/webapp/WEB-INF/jasper/murata.jrxml","D:/java/projectee/jasper1/src/main/webapp/WEB-INF/jasper/murata.jasper");
	          }else{
	        	  System.out.println(" 1Not found file jrxml ");
	        //  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	         try{
	        	  JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
	         
	          JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport,hmParams,conn);
	          jasperprint.setOrientation(OrientationEnum.PORTRAIT);
	         // JasperExportManager.exportReportToPdfFile(jasperprint,"D:/java/projectee/jrp/WebContent/WEB-INF/jasper/murata1.pdf");
	          System.out.println("giai doan print");
	      
	          
	         
	          
	          
	          
	          System.out.println("printing ...");
	    
	          
	        
	          

	          JRPrintServiceExporter exporter;
	          
	          PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
	        //  printRequestAttributeSet.add(MediaSizeName.);
	          printRequestAttributeSet.add(new Copies(1));
	      // printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
	         // int width = Math.round(MediaSize.ISO..getX(MediaSize.MM));
	         // int height = Math.round(MediaSize.ISO.A4.getY(MediaSize.MM));
	          printRequestAttributeSet.add(new MediaPrintableArea(0, 0, 100, 50, MediaPrintableArea.MM));
	          //
	      
	       
	          PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
	          
	         /* Java AWT Printing will not find a printer via a path if it is not registered to 
	          the Windows / Active Directory user that is running the printing application. 
	          You must register the printer path through Windows "Devices and Printers" as 
	          a printer for that user for it to be visible. Then you must run lookupPrintServices 
	          to see the list of printers available and retrieve the proper PrintService by the exact name String listed*/
	          
	        
	          
	          javax.print.PrintService[] service = PrinterJob.lookupPrintServices(); 
	      

	          int count = service.length;
	          int i=0;
	          for ( i = 0; i < count; i++) {
	            
	              System.out.println(service[i].getName());
	              if(service[i].getName().equals(printerName))
	              {
	            		  printServiceAttributeSet.add(new PrinterName(printerName, null));
	            		  break;
	              }
	          }
	          if(i==count){
	        	  System.out.println(" Not found printer");
		    	  model.addAttribute("error", 4);
		    	  model.addAttribute("msg",printerName);
	          }else{
	          
	        	  System.out.println("1 Not found printer");
	           exporter = new JRPrintServiceExporter();
	        
	         exporter.setExporterInput(new SimpleExporterInput(jasperprint));
	          SimplePrintServiceExporterConfiguration expConfig = new SimplePrintServiceExporterConfiguration();
	          
	          expConfig.setPrintRequestAttributeSet(printRequestAttributeSet);
	          expConfig.setPrintServiceAttributeSet(printServiceAttributeSet);
	          expConfig.setDisplayPageDialog(Boolean.FALSE);
	          expConfig.setDisplayPrintDialog(Boolean.FALSE);
	       
	          exporter.setConfiguration(expConfig);
	          exporter.exportReport();
	          conn.close();
	          System.out.println(" success ");
	           model.addAttribute("error", 7);
	          
	          }
	         }catch(JRException e){
	        	 System.out.println(" ko load file jrxml ");
		           model.addAttribute("error", 6);
		           model.addAttribute("msg", reportFile.getPath());
		   
	         }
	          }
	      //namekh
         }else{
        	 System.out.println("ko co customer part name ");
	           model.addAttribute("error", 8);
	           model.addAttribute("msg"," "+vtepartcode + " " +fgcode);
	           conn.close();
         }
	           }else{
		    	   System.out.println("ko co du lieu ");
		           model.addAttribute("error", 5);
		           model.addAttribute("msg", serialno);
		           conn.close();
		       }
	       }
	       else
	       {
	           System.out.println(" connection Failed ");
	           model.addAttribute("error", 2);
	           model.addAttribute("msg", "jdbc:postgresql://" + host +":" + port + "/"+ db );
	        
	       }
	      
	     
	      }catch(Exception e){
	    	  
	      }
	        //  model.addAttribute("namekh", namekh);
	 
	      
	      return "canonthai";
	   }
	   @RequestMapping(value={"/importcode"}, method = RequestMethod.POST )
	   public String importcode(Model model, @RequestParam("excelfile") MultipartFile excelfile){
	   	
	        List<FG> listpst = new ArrayList<FG>();
	        int t = 0;
            int f=0;
	   	  try {
	               int i = 1;
	            
	               //Creates a workbook object from the uploaded excelfile
	               String fn =excelfile.getOriginalFilename();
	               String extent = fn.substring(fn.indexOf("."), fn.length());
	               if(extent.equalsIgnoreCase(".xlsx")){
	               XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
	               //Creates a worksheet object representing the first sheet
	               XSSFSheet worksheet = workbook.getSheetAt(0);
	               //Reads the data in excel file until last row is encountered
	              
	              
	           /*    Date d = new Date();
	              
	               pst.setdImport(new java.sql.Date(d.getTime()));
	               pst.setInvoiceNo(row1.getCell(3).getStringCellValue());
	               pst.setdInvoice(new java.sql.Date(row1.getCell(10).getDateCellValue().getTime()));*/
	            
	               while (i <= worksheet.getLastRowNum()) {
	                   //Creates an object for the Candidate  Model
	                   FG pst =new FG();
	                   //Creates an object representing a single row in excel
	                   XSSFRow row = worksheet.getRow(i++);
	                   if(row.getCell(1).getStringCellValue().length()>0){
	                   //Sets the Read data to the model class
	                   	DecimalFormat formatter = new DecimalFormat("#########");
	                   pst.setVtepartname(row.getCell(2).getStringCellValue());
	                   pst.setFgcode(row.getCell(1).getStringCellValue());
	                   pst.setCustomercode(row.getCell(3).getStringCellValue());
	                   pst.setCustomer(row.getCell(4).getStringCellValue());
	               
	                  
	                  // Product pro = pdao.getProductById(row.getCell(2).getStringCellValue());
	                 //  pst.setPro(pro);
	                 
	              
	                
	          
	                   listpst.add(pst);
	                  
	               }}
	             //  pstdao.deleteAll();
	         
	               for(FG pst : listpst){
	            	  boolean k =  FGDao.insert(pst);
	            	  if(k){
	            		  t++;
	            	  }else{
	            		  f++;
	            	  }
	            	  
	               }
	               }else{
	               	HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
	                   //Creates a worksheet object representing the first sheet
	                   HSSFSheet worksheet = workbook.getSheetAt(0);
	                   //Reads the data in excel file until last row is encountered
	                  
	                  
	               /*    Date d = new Date();
	                  
	                   pst.setdImport(new java.sql.Date(d.getTime()));
	                   pst.setInvoiceNo(row1.getCell(3).getStringCellValue());
	                   pst.setdInvoice(new java.sql.Date(row1.getCell(10).getDateCellValue().getTime()));*/
	                
	                   while (i <= worksheet.getLastRowNum()) {
	                       //Creates an object for the Candidate  Model
	                       FG pst =new FG();
	                       //Creates an object representing a single row in excel
	                       HSSFRow row = worksheet.getRow(i++);
	                       if(row.getCell(1).getStringCellValue().length()>0){
	                       //Sets the Read data to the model class
	                       	DecimalFormat formatter = new DecimalFormat("#########");
	                        pst.setVtepartname(row.getCell(2).getStringCellValue());
	 	                   pst.setFgcode(row.getCell(1).getStringCellValue());
	 	                   pst.setCustomercode(row.getCell(3).getStringCellValue());
	 	                   pst.setCustomer(row.getCell(4).getStringCellValue());
	                      
	                      // Product pro = pdao.getProductById(row.getCell(2).getStringCellValue());
	                     //  pst.setPro(pro);
	                     
	                  
	                    
	              
	                       listpst.add(pst);
	                      
	                   }}
	               //    pstdao.deleteAll();
	                    t = 0;
		                f=0;
		               for(FG pst : listpst){
		            	  boolean k =  FGDao.insert(pst);
		            	  if(k){
		            		  t++;
		            	  }else{
		            		  f++;
		            	  }
		            	  
		               }
	               }
	           } catch (Exception e) {
	       		model.addAttribute("errorinsert", 1);
				logger.debug(e);

	               e.printStackTrace();
	           }
	   	  List<FG> listfg = FGDao.getList();
	   		model.addAttribute("listfg", listfg);
	   	
	   		model.addAttribute("t", t);
	   		model.addAttribute("f", f);
	   		
	   	
	   		
	   		return "customercode";
	   	
	   }
	
}
