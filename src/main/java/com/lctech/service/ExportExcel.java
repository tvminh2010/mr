package com.lctech.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dao.CoreWeightDao;
import com.dao.DetailCompDao;
import com.dao.ProductDao;
import com.dao.ReceiptCompDao;
import com.dao.ReturnWeightLogDao;
import com.dao.TurnDao;
import com.dao.WorkOrderDao;
import com.entity.CloseTime;
import com.entity.CoreWeight;
import com.config.Config;
import com.entity.DetailComp;
import com.entity.Product;
import com.entity.ReceiptComp;
import com.entity.ReturnExcel;
import com.entity.ReturnWeightLog;
import com.entity.Turn;
import com.entity.TypeComp;
import com.entity.WorkOrder;

@Service
public class ExportExcel {
	private static final Logger logger = Logger.getLogger(ExportExcel.class);
	@Autowired
	private DetailCompDao dcdao;
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private TurnDao tdao;
	@Autowired
	private Config config;
	@Autowired
	private ReportsServive reportsServive;
	@Autowired
	private ReturnWeightLogDao rwldao;
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	ServletContext servletContext;
	  @Autowired
		 private CoreWeightDao cwdao;
	  @Autowired
		 private ProductDao pdao;
	public XSSFWorkbook   exportReturn(WorkOrder  wo,HttpServletRequest req) {
		logger.info("wo " + wo.toString());
		List<ReturnWeightLog> lrw = rwldao.getReturnWeightLogBywoid(wo.getId());
		String userDir = System.getProperty("user.dir");
		//ServletContext context = req.getServletContext();
	        String appPath = servletContext.getRealPath("");

		String filePathToBeServed = appPath +"/data/Formlabel.xlsx" ;
		logger.info("filePathToBeServed " + filePathToBeServed);
        File file = new File(filePathToBeServed);
		 //File file = new File(fileName);
         FileInputStream fin = null;  
         XSSFWorkbook workbook = new XSSFWorkbook();  
         FileOutputStream fout = null;
         String dat = "";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm");
	        dat = simpleDateFormat.format(new Date());
         if (file.exists()) {
        	 try{
                 fout = new FileOutputStream(filePathToBeServed, true);
                 fin = new FileInputStream(filePathToBeServed);
                 workbook = new XSSFWorkbook(fin);
                 for (int i=0; i<workbook.getNumberOfSheets(); i++) {
                     System.out.println( workbook.getSheetName(i) );                                    
                 }
                 XSSFSheet sheet = workbook.getSheetAt(0);
                 sheet.getRow(2).getCell(1).setCellValue(wo.getModel().getPt_desc1());
                 sheet.getRow(1).getCell(4).setCellValue(wo.getLine().getName());
                 sheet.getRow(2).getCell(4).setCellValue("");
                 sheet.getRow(2).getCell(5).setCellValue(dat);
                 sheet.getRow(1).createCell(1).setCellValue(""+wo.getName());
                 XSSFCellStyle style=workbook.createCellStyle();
                 style.setBorderBottom(BorderStyle.THIN);
                 style.setBorderTop(BorderStyle.THIN);
                 style.setBorderRight(BorderStyle.THIN);
                 style.setBorderLeft(BorderStyle.THIN);
             	int rowCount = 4;
             	int i = 1;
    	    	for(ReturnWeightLog rwl : lrw) {
    	    		if(rwl.getQty()>0) {
    	    			XSSFCell cell0 =  sheet.getRow(rowCount).createCell(0);
    	    			cell0.setCellValue(i);
    	    			cell0.setCellStyle(style);
    	    			XSSFCell cell1 =  sheet.getRow(rowCount).createCell(1);
    	    			cell1.setCellValue(rwl.getModel());
    	    			cell1.setCellStyle(style);
    	    			XSSFCell cell2 =  sheet.getRow(rowCount).createCell(2);
    	    			cell2.setCellValue(rwl.getPtdesc1());
    	    			cell2.setCellStyle(style);
    	    			XSSFCell cell3 =  sheet.getRow(rowCount).createCell(3);
    	    			cell3.setCellValue(rwl.getLotno());
    	    			cell3.setCellStyle(style);
    	    			XSSFCell cell4 =  sheet.getRow(rowCount).createCell(4);
    	    			cell4.setCellValue(rwl.getQty());
    	    			cell4.setCellStyle(style);
    	    			XSSFCell cell5 =  sheet.getRow(rowCount).createCell(5);
    	    			cell5.setCellValue(rwl.getSerialnew());
    	    			cell5.setCellStyle(style);
    	    			XSSFCell cell6 =  sheet.getRow(rowCount).createCell(6);
    	    			cell6.setCellValue(rwl.getPtum());
    	    			cell6.setCellStyle(style);
    	    			XSSFCell cell7 =  sheet.getRow(rowCount).createCell(7);
    	    			cell7.setCellValue(rwl.getReceivingdate());
    	    			cell7.setCellStyle(style);
    	    			
    	                // sheet.getRow(rowCount).createCell(2).setCellValue(rwl.getPtdesc1());
    	                 //sheet.getRow(rowCount).createCell(3).setCellValue(rwl.getLotno());
    	                // sheet.getRow(rowCount).createCell(4).setCellValue(rwl.getQty());
    	                 //sheet.getRow(rowCount).createCell(5).setCellValue(rwl.getSerialnew());
    	                 //sheet.getRow(rowCount).createCell(6).setCellValue(rwl.getPtum());
    	    		}
    	    		rowCount++;
    	    		i++;
    	    	}
		
                XSSFSheet sheet1 = workbook.getSheetAt(1);
                sheet1.getRow(1).getCell(0).setCellValue("WO: " + wo.getName());
                sheet1.getRow(1).getCell(3).setCellValue("Line: " +wo.getLine().getName());
                sheet1.getRow(2).getCell(3).setCellValue("Model: " +wo.getModel().getPt_desc1());
                sheet1.getRow(1).getCell(7).setCellValue("");
                sheet1.getRow(2).getCell(7).setCellValue(dat);
				List<Object[][]> lrw1 = rwldao.getReturnWeightLogBywoidOrderbyNVL(wo.getId());

            	int rowCount1 =5;
   	    	for(Object[] rwl : lrw1) {
   	    		Double qty = (Double)rwl[2];
	    		String model = (String)rwl[1];
	    		Long count = (Long)rwl[3];
	    		 CoreWeight cw =  cwdao.getByItemcode(model);
	    		 Product p = pdao.getProductById(model);
   	    		if(qty>0) {
   	    			sheet1.getRow(rowCount1).getCell(1).setCellValue(p.getPt_part());
   	    			sheet1.getRow(rowCount1).getCell(2).setCellValue(p.getPt_desc1());
   	    			sheet1.getRow(rowCount1).getCell(3).setCellValue(p.getPt_desc2());
   	    			sheet1.getRow(rowCount1).getCell(4).setCellValue(cw.getVendor());
   	                sheet1.getRow(rowCount1).getCell(5).setCellValue(p.getPt_um());
   	                sheet1.getRow(rowCount1).getCell(6).setCellValue(qty);
   	              if(model.startsWith("DA")) {
   	            	 sheet1.getRow(rowCount1).getCell(7).setCellValue(count);
   	              }
   	               
   	           
   	    		}
   	    		rowCount1++;
   	    	}
     
    					 }catch(Exception e) {
    						 e.printStackTrace();
    		        		 logger.info("loi"+ e);
    		        	 }
    	    		}
		    	for(ReturnWeightLog rwl : lrw) {
		    		      rwldao.updateStatus(rwl.getId());
		    	}
			    String filename =  "RETURN"+"_"+wo.getName() +"_"+simpleDateFormat.format(new Date());
		    	
		    	try {
		        	FileOutputStream fos = new FileOutputStream("D:/"+filename+".xlsx");
					workbook.write(fos);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.info(e);
					e.printStackTrace();
				}
         
	    	return workbook;
	}
	
	public XSSFWorkbook   exportReturn(WorkOrder  wo,HttpServletRequest req, String nameexcel) {
		logger.info("wo " + wo.toString());
		List<ReturnWeightLog> lrw = rwldao.getReturnWeightLogBywoid0(wo.getId());
		String userDir = System.getProperty("user.dir");
		//ServletContext context = req.getServletContext();
	        String appPath = servletContext.getRealPath("");

		String filePathToBeServed = appPath +"/data/Formlabel.xlsx" ;
		logger.info("filePathToBeServed " + filePathToBeServed);
        File file = new File(filePathToBeServed);
		 //File file = new File(fileName);
         FileInputStream fin = null;  
         XSSFWorkbook workbook = new XSSFWorkbook();  
         FileOutputStream fout = null;
         String dat = "";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm");
	        dat = simpleDateFormat.format(new Date());
         if (file.exists()) {
        	 try{
                 fout = new FileOutputStream(filePathToBeServed, true);
                 fin = new FileInputStream(filePathToBeServed);
                 workbook = new XSSFWorkbook(fin);
                 for (int i=0; i<workbook.getNumberOfSheets(); i++) {
                     System.out.println( workbook.getSheetName(i) );                                    
                 }
                 XSSFSheet sheet = workbook.getSheetAt(0);
                 sheet.getRow(2).getCell(1).setCellValue(wo.getModel().getPt_desc1());
                 sheet.getRow(1).getCell(4).setCellValue(wo.getLine().getName());
                 sheet.getRow(2).getCell(4).setCellValue("");
                 sheet.getRow(2).getCell(5).setCellValue(dat);
                 sheet.getRow(1).createCell(1).setCellValue(""+wo.getName());
                 XSSFCellStyle style=workbook.createCellStyle();
                 style.setBorderBottom(BorderStyle.THIN);
                 style.setBorderTop(BorderStyle.THIN);
                 style.setBorderRight(BorderStyle.THIN);
                 style.setBorderLeft(BorderStyle.THIN);
             	int rowCount = 4;
             	int i = 1;
    	    	for(ReturnWeightLog rwl : lrw) {
    	    		if(rwl.getQty()>0) {
    	    			XSSFCell cell0 =  sheet.getRow(rowCount).createCell(0);
    	    			cell0.setCellValue(i);
    	    			cell0.setCellStyle(style);
    	    			XSSFCell cell1 =  sheet.getRow(rowCount).createCell(1);
    	    			cell1.setCellValue(rwl.getModel());
    	    			cell1.setCellStyle(style);
    	    			XSSFCell cell2 =  sheet.getRow(rowCount).createCell(2);
    	    			cell2.setCellValue(rwl.getPtdesc1());
    	    			cell2.setCellStyle(style);
    	    			XSSFCell cell3 =  sheet.getRow(rowCount).createCell(3);
    	    			cell3.setCellValue(rwl.getLotno());
    	    			cell3.setCellStyle(style);
    	    			XSSFCell cell4 =  sheet.getRow(rowCount).createCell(4);
    	    			cell4.setCellValue(rwl.getQty());
    	    			cell4.setCellStyle(style);
    	    			XSSFCell cell5 =  sheet.getRow(rowCount).createCell(5);
    	    			cell5.setCellValue(rwl.getSerialnew());
    	    			cell5.setCellStyle(style);
    	    			XSSFCell cell6 =  sheet.getRow(rowCount).createCell(6);
    	    			cell6.setCellValue(rwl.getPtum());
    	    			cell6.setCellStyle(style);
    	    			XSSFCell cell7 =  sheet.getRow(rowCount).createCell(7);
    	    			cell7.setCellValue(rwl.getReceivingdate());
    	    			cell7.setCellStyle(style);
    	    			
    	                // sheet.getRow(rowCount).createCell(2).setCellValue(rwl.getPtdesc1());
    	                 //sheet.getRow(rowCount).createCell(3).setCellValue(rwl.getLotno());
    	                // sheet.getRow(rowCount).createCell(4).setCellValue(rwl.getQty());
    	                 //sheet.getRow(rowCount).createCell(5).setCellValue(rwl.getSerialnew());
    	                 //sheet.getRow(rowCount).createCell(6).setCellValue(rwl.getPtum());
    	    		}
    	    		rowCount++;
    	    		i++;
    	    	}
		
                XSSFSheet sheet1 = workbook.getSheetAt(1);
                sheet1.getRow(1).getCell(0).setCellValue("WO: " + wo.getName());
                sheet1.getRow(1).getCell(3).setCellValue("Line: " +wo.getLine().getName());
                sheet1.getRow(2).getCell(3).setCellValue("Model: " +wo.getModel().getPt_desc1());
                sheet1.getRow(1).getCell(7).setCellValue("");
                sheet1.getRow(2).getCell(7).setCellValue(dat);
				List<Object[][]> lrw1 = rwldao.getReturnWeightLogBywoidOrderbyNVL0(wo.getId());

            	int rowCount1 =5;
   	    	for(Object[] rwl : lrw1) {
   	    		Double qty = (Double)rwl[2];
	    		String model = (String)rwl[1];
	    		Long count = (Long)rwl[3];
	    		 CoreWeight cw =  cwdao.getByItemcode(model);
	    		 Product p = pdao.getProductById(model);
   	    		if(qty>0) {
   	    			sheet1.getRow(rowCount1).getCell(1).setCellValue(p.getPt_part());
   	    			sheet1.getRow(rowCount1).getCell(2).setCellValue(p.getPt_desc1());
   	    			sheet1.getRow(rowCount1).getCell(3).setCellValue(p.getPt_desc2());
   	    			sheet1.getRow(rowCount1).getCell(4).setCellValue(cw.getVendor());
   	                sheet1.getRow(rowCount1).getCell(5).setCellValue(p.getPt_um());
   	                sheet1.getRow(rowCount1).getCell(6).setCellValue(qty);
   	              if(model.startsWith("DA")) {
   	            	 sheet1.getRow(rowCount1).getCell(7).setCellValue(count);
   	              }
   	               
   	           
   	    		}
   	    		rowCount1++;
   	    	}
     
    					 }catch(Exception e) {
    						 e.printStackTrace();
    		        		 logger.info("loi"+ e);
    		        	 }
    	    		}
		    	for(ReturnWeightLog rwl : lrw) {
		    		      rwldao.updateStatus(rwl.getId());
		    	}
			    String filename =  "RETURN"+"_"+wo.getName() +"_"+simpleDateFormat.format(new Date());
		    	
		    	try {
		        	FileOutputStream fos = new FileOutputStream("D:/"+nameexcel+".xlsx");
					workbook.write(fos);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.info(e);
					e.printStackTrace();
				}
         
	    	return workbook;
	}
	public void exportTurnBS(String turn,CloseTime lnt){
		logger.info("exportTurn "+ "RequestBS");
		logger.info(config.getDataExcel());
		try {
		TypeComp type = TypeComp.RequestBS;
		List<String> listDesc2 = dcdao.getListDesc2(type, lnt);
		   String dat = "";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm");
	        dat = simpleDateFormat.format(new Date());
		if(!listDesc2.isEmpty()){
			logger.info("listDesc2 "+ " khac null");
		HSSFWorkbook workbook =  new HSSFWorkbook();
		
		
		for(String desc2: listDesc2){
			HSSFSheet sheet = workbook.createSheet(desc2);
		  //  sheet.setDefaultColumnWidth(30);
			logger.info("create sheet "+ desc2);
	        // create style for header cells
	        CellStyle style = workbook.createCellStyle();
	        Font font = workbook.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE.index);
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	         
	        
	        HSSFRow title = sheet.createRow(0);
	        title.createCell(3).setCellValue("DELIVERY ORDER");
	        
	        HSSFRow title2 = sheet.createRow(1);
	        title2.createCell(0).setCellValue("DO No:DO:");
	     
	        HSSFRow title3 = sheet.createRow(2);
	        title3.createCell(0).setCellValue("Issued date:");
	        title3.createCell(1).setCellValue(dat);
	        title3.createCell(5).setCellValue("Issued by:");
	        
	        // create header row
	        HSSFRow header = sheet.createRow(4);
	        
	        header.createCell(0).setCellValue("STT");
	        header.getCell(0).setCellStyle(style);
	         
	        header.createCell(1).setCellValue("Pro Line");
	        header.getCell(1).setCellStyle(style);
	         
	        header.createCell(2).setCellValue("Model");
	        header.getCell(2).setCellStyle(style);
	         
	        header.createCell(3).setCellValue("Item name");
	        header.getCell(3).setCellStyle(style);
	         
	        header.createCell(4).setCellValue("Item number");
	        header.getCell(4).setCellStyle(style);
	        
	        header.createCell(5).setCellValue("Item Code");
	        header.getCell(5).setCellStyle(style);
	        
	        header.createCell(6).setCellValue("Unit");
	        header.getCell(6).setCellStyle(style);
	        
	        header.createCell(7).setCellValue("Time required");
	        header.getCell(7).setCellStyle(style);
	        
	        header.createCell(8).setCellValue("Q'ty plan");
	        header.getCell(8).setCellStyle(style);
	        header.createCell(9).setCellValue("Note");
	        header.getCell(9).setCellStyle(style);
	        header.createCell(10).setCellValue("Q'ty thuc te");
	        header.getCell(10).setCellStyle(style);
	        header.createCell(11).setCellValue("WorkOrder");
	        header.getCell(11).setCellStyle(style);
	        
			List<Object[]> ldc = dcdao.getListByDesc2(type, desc2,lnt);
			int rowCount = 5;
			int j = 1;
			for(Object[] o : ldc){
				//Product comp = (Product)o[2];
				//DetailComp dc = (DetailComp)o[0];
				//ReceiptComp rc = (ReceiptComp)o[1];
				 HSSFRow aRow = sheet.createRow(rowCount++);
		          /*  aRow.createCell(0).setCellValue(j);
		            aRow.createCell(1).setCellValue(rc.getWo().getLine().getName());
		            aRow.createCell(2).setCellValue(rc.getWo().getModel().getPt_desc1());
		            aRow.createCell(3).setCellValue(comp.getPt_desc2());
		            aRow.createCell(4).setCellValue(comp.getPt_desc1());
		            aRow.createCell(5).setCellValue(comp.getPt_part());
		            aRow.createCell(6).setCellValue(comp.getPt_um());
		            aRow.createCell(7).setCellValue(1);
		            aRow.createCell(8).setCellValue(dc.getQty());
		            
		            aRow.createCell(11).setCellValue(rc.getWo().getName());*/
				    aRow.createCell(0).setCellValue(j);
		            aRow.createCell(1).setCellValue((String)o[0]);
		            aRow.createCell(2).setCellValue((String)o[1]);
		            aRow.createCell(3).setCellValue((String)o[2]);
		            aRow.createCell(4).setCellValue((String)o[3]);
		            aRow.createCell(5).setCellValue((String)o[4]);
		            aRow.createCell(6).setCellValue((String)o[5]);
		            aRow.createCell(7).setCellValue(1);
		            aRow.createCell(8).setCellValue((Double)o[6]);
		            
		            aRow.createCell(11).setCellValue((String)o[7]);
		       j++;
			}
		}
		
		String filename =  turn +"_"+ type.toString() ;
		logger.info(filename);
		Turn  t =new Turn();
		  t= tdao.getByid(turn);
		logger.info(t.toString());
		if(t == null || t.getId() ==null || t.getId().isEmpty()){
		
			t.setId(turn);
		    t.setD(new Timestamp(new Date().getTime()));
		    
			      t.setLinkdownloadbs(filename);
			      logger.info(t.toString());
					logger.info(config.getDataExcel() + filename +".xls");
				    tdao.save(t);
			   
		}else{
			
			      t.setLinkdownloadbs(filename);
			      tdao.update(t);
		}
		
        try {
        	FileOutputStream fos = new FileOutputStream(config.getDataExcel() + filename +".xls");
			workbook.write(fos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
	}
	
	public void exportTurnSetup(String turn,CloseTime lnt){
		logger.info("exportTurn ");
		TypeComp type = TypeComp.RequestSetup;
		List<Object[]> ldc = dcdao.getListByNoDesc2(type,lnt);
		 String dat = "";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm");
	        dat = simpleDateFormat.format(new Date());
		if(!ldc.isEmpty()){
		HSSFWorkbook workbook =  new HSSFWorkbook();
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
			HSSFSheet sheet = workbook.createSheet("Setup");
		  //  sheet.setDefaultColumnWidth(30);
	         
	        // create style for header cells
	        CellStyle style = workbook.createCellStyle();
	        Font font = workbook.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE.index);
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);

	        HSSFRow title = sheet.createRow(0);
	        title.createCell(3).setCellValue("DELIVERY ORDER");
	        
	        HSSFRow title2 = sheet.createRow(1);
	        title2.createCell(0).setCellValue("DO No:DO:");
	        
	        HSSFRow title3 = sheet.createRow(2);
	        title3.createCell(0).setCellValue("Issued date:");
	       
	        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
	        short df = workbook.createDataFormat().getFormat("yyyy/mm/dd hh:mm");
	        dateCellStyle.setDataFormat(df);
	        title3.createCell(1).setCellValue(dat);
	        title3.createCell(1).setCellStyle(dateCellStyle);
	        title3.createCell(5).setCellValue("Issued by:");
	        // create header row
	        HSSFRow header = sheet.createRow(4);
	         
	        header.createCell(0).setCellValue("STT");
	        header.getCell(0).setCellStyle(style);
	         
	        header.createCell(1).setCellValue("Pro Line");
	        header.getCell(1).setCellStyle(style);
	         
	        header.createCell(2).setCellValue("Model");
	        header.getCell(2).setCellStyle(style);
	         
	        header.createCell(3).setCellValue("Item name");
	        header.getCell(3).setCellStyle(style);
	         
	        header.createCell(4).setCellValue("Item number");
	        header.getCell(4).setCellStyle(style);
	        
	        header.createCell(5).setCellValue("Item Code");
	        header.getCell(5).setCellStyle(style);
	        
	        header.createCell(6).setCellValue("Unit");
	        header.getCell(6).setCellStyle(style);
	        
	        header.createCell(7).setCellValue("Time required");
	        header.getCell(7).setCellStyle(style);
	        
	        header.createCell(8).setCellValue("Q'ty plan");
	        header.getCell(8).setCellStyle(style);
	        header.createCell(9).setCellValue("Note");
	        header.getCell(9).setCellStyle(style);
	        header.createCell(10).setCellValue("Q'ty thuc te");
	        header.getCell(10).setCellStyle(style);
	        header.createCell(11).setCellValue("WorkOrder");
	        header.getCell(11).setCellStyle(style);
			int j=1;
			int rowCount = 5;
			for(Object[] o : ldc){
				//Product comp = (Product)o[2];
				//DetailComp dc = (DetailComp)o[0];
				//ReceiptComp rc = (ReceiptComp)o[1];
				 HSSFRow aRow = sheet.createRow(rowCount++);
		         /*   aRow.createCell(0).setCellValue(j);
		            aRow.createCell(1).setCellValue(rc.getWo().getLine().getName());
		            aRow.createCell(2).setCellValue(rc.getWo().getModel().getPt_desc1());
		            aRow.createCell(3).setCellValue(comp.getPt_desc2());
		            aRow.createCell(4).setCellValue(comp.getPt_desc1());
		            aRow.createCell(5).setCellValue(comp.getPt_part());
		            aRow.createCell(6).setCellValue(comp.getPt_um());
		            aRow.createCell(7).setCellValue(1);
		            aRow.createCell(8).setCellValue(dc.getQty());
		            aRow.createCell(11).setCellValue(rc.getWo().getName());*/
		            
		            aRow.createCell(0).setCellValue(j);
		            aRow.createCell(1).setCellValue((String)o[0]);
		            aRow.createCell(2).setCellValue((String)o[1]);
		            aRow.createCell(3).setCellValue((String)o[2]);
		            aRow.createCell(4).setCellValue((String)o[3]);
		            aRow.createCell(5).setCellValue((String)o[4]);
		            aRow.createCell(6).setCellValue((String)o[5]);
		            aRow.createCell(7).setCellValue(1);
		            aRow.createCell(8).setCellValue((Double)o[6]);
		            
		            aRow.createCell(11).setCellValue((String)o[7]);
		            
		            
		            j++;
		       
			}
			String filename =  turn +"_"+ type.toString() ;
			logger.info(config.getDataExcel() );
			logger.info(config.getDataExcel() + filename +".xls");
			Turn  t =new Turn();
			  t= tdao.getByid(turn);
			logger.info(t.toString());
			if(t == null || t.getId() ==null || t.getId().isEmpty()){
			
				t = new Turn();
				t.setId(turn);
			    t.setD(new Timestamp(new Date().getTime()));
				    	 t.setLinkdownloadsetup(filename);
				    		logger.info(t.toString());
						    tdao.save(t);
			}else{
				    	 t.setLinkdownloadsetup(filename);
				    		logger.info(t.toString());
						    tdao.update(t);
			}
		
	        try {
	        	FileOutputStream fos = new FileOutputStream(config.getDataExcel() + filename +".xls");
				workbook.write(fos);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.info(e);
				e.printStackTrace();
			
			}
		}
		
		
	}
	public HSSFWorkbook exportWOByDesc2( List<String> listPt_desc2,  List<Object[]> lrc ){
		try{
		HSSFWorkbook workbook =  new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("WorkOrder");
		 HSSFRow title = sheet.createRow(0);
	        title.createCell(0).setCellValue("WorkOrder");
	        title.createCell(1).setCellValue("Date");
	        title.createCell(2).setCellValue("Line");
	        title.createCell(3).setCellValue("Model");
	        title.createCell(4).setCellValue("Plan");
	        title.createCell(5).setCellValue("Status");
	        int i=6;
	        for(String desc2:listPt_desc2){
	        	title.createCell(i).setCellValue(desc2);
	        	i++;
	        }
	        int rowCount=1;
	    /*    CellStyle cellStyle = workbook.createCellStyle();
      	  CreationHelper createHelper = workbook.getCreationHelper();
      	  short dateFormat = createHelper.createDataFormat().getFormat("MM-dd-yyyy");
    	      cellStyle.setDataFormat(dateFormat);*/
	        for(Object[] wo:lrc){
	        	 HSSFRow aRow = sheet.createRow(rowCount++);
	        	 aRow.createCell(0).setCellValue((String)wo[0]);
	        	 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        	 //aRow.createCell(1).setCellValue(new Date());
	        	 aRow.createCell(1).setCellValue(dateFormat.format((Date)wo[1]));
	        	// aRow.createCell(1).setCellStyle(cellStyle);
	        	 aRow.createCell(2).setCellValue((String)wo[2]);
	        	 aRow.createCell(3).setCellValue((String)wo[3]);
	        	 aRow.createCell(4).setCellValue((Double)wo[4]);
	        	 aRow.createCell(5).setCellValue((Integer)wo[5]);
	        	 i=6;
	        	   for(String desc2:listPt_desc2){
	        		   aRow.createCell(i).setCellValue((Double)wo[i]);
	   	        	   i++;
	   	        }
	        }
	        workbook.close();
	        return workbook;
		}catch(Exception e){
			logger.info(e);
			e.printStackTrace();
			return null;
		}
		
	}
	public HSSFWorkbook exportWOByDesc21( List<Object[]> lrc ){
		try{
		HSSFWorkbook workbook =  new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("BC3");
		 HSSFRow title1 = sheet.createRow(0);
		   title1.createCell(0).setCellValue("BÃ¡o cÃ¡o Tá»•ng há»£p cÃ¡c Work Order");
		 HSSFRow title = sheet.createRow(1);
	        title.createCell(0).setCellValue("WorkOrder");
	        title.createCell(1).setCellValue("Date");
	        title.createCell(2).setCellValue("Line");
	        title.createCell(3).setCellValue("Model");
	        title.createCell(4).setCellValue("Plan");
	        title.createCell(5).setCellValue("Status");
	        title.createCell(6).setCellValue("Item Code");
	        title.createCell(7).setCellValue("Item Name");
	        title.createCell(8).setCellValue("Item Number");
	        title.createCell(9).setCellValue("SL nháº­n");
	        title.createCell(10).setCellValue("SL hoÃ n tráº£");
	        title.createCell(11).setCellValue("SL thá»±c táº¿");
	     
	        int rowCount=2;
	    /*    CellStyle cellStyle = workbook.createCellStyle();
      	  CreationHelper createHelper = workbook.getCreationHelper();
      	  short dateFormat = createHelper.createDataFormat().getFormat("MM-dd-yyyy");
    	      cellStyle.setDataFormat(dateFormat);*/
	        for(Object[] wo:lrc){
	        	 HSSFRow aRow = sheet.createRow(rowCount++);
	        	 aRow.createCell(0).setCellValue((String)wo[0]);
	        	 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        	 //aRow.createCell(1).setCellValue(new Date());
	        	 aRow.createCell(1).setCellValue(dateFormat.format((Date)wo[1]));
	        	// aRow.createCell(1).setCellStyle(cellStyle);
	        	 aRow.createCell(2).setCellValue((String)wo[2]);
	        	 aRow.createCell(3).setCellValue((String)wo[3]);
	        	 aRow.createCell(4).setCellValue((Double)wo[4]);
	        	 aRow.createCell(5).setCellValue((Integer)wo[5]);
	        	 aRow.createCell(6).setCellValue((String)wo[6]);
	        	 aRow.createCell(7).setCellValue((String)wo[7]);
	        	 aRow.createCell(8).setCellValue((String)wo[8]);
	        	 aRow.createCell(9).setCellValue((Double)wo[9]);
	        	 aRow.createCell(10).setCellValue((Double)wo[10]);
	        	 aRow.createCell(11).setCellValue((Double)wo[9]-(Double)wo[10]);
	        	 
	        }
	        workbook.close();
	        return workbook;
		}catch(Exception e){
			logger.info(e);
			e.printStackTrace();
			return null;
		}
		
	}
	public HSSFWorkbook bywo1excel( String woname ){
		try{
		     WorkOrder wo = wodao.getWObyName(woname);
			   String woid = wo.getId();
			   if(wo != null && wo.getId() != null){
		HSSFWorkbook workbook =  new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("BC1");
		HSSFSheet sheet1 = workbook.createSheet("BC2");
		HSSFSheet sheet3= workbook.createSheet("BC3");
		
		 HSSFRow title = sheet.createRow(0);
		 title.createCell(0).setCellValue("1. BÃ¡o cÃ¡o chi tiáº¿t theo WorkOrder cÃ³ tá»•ng há»£p thá»±c nháº­n");
		 HSSFRow cellwo2 = sheet.createRow(2);
		 cellwo2.createCell(0).setCellValue("WorkOrder");
		 cellwo2.createCell(1).setCellValue("Date");
		 cellwo2.createCell(2).setCellValue("Line");
		 cellwo2.createCell(3).setCellValue("Model");
		 cellwo2.createCell(4).setCellValue("Plan");
		 cellwo2.createCell(5).setCellValue("Status");
		 
		 HSSFRow cellwo3 = sheet.createRow(3);
		 cellwo3.createCell(0).setCellValue(wo.getName());
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 cellwo3.createCell(1).setCellValue(dateFormat.format(wo.getCreatedate()));
		 cellwo3.createCell(2).setCellValue( wo.getLine().getName());
		 cellwo3.createCell(3).setCellValue(wo.getModel().getPt_desc1());
		 cellwo3.createCell(4).setCellValue(wo.getQty());
		 cellwo3.createCell(5).setCellValue( wo.getStatus());
		 
		 HSSFRow cellwo5 = sheet.createRow(5);
		 cellwo5.createCell(0).setCellValue("Item Code");
		 cellwo5.createCell(1).setCellValue("Item Name");
		 cellwo5.createCell(2).setCellValue("Item Number");
		 cellwo5.createCell(3).setCellValue("SL Nháº­n");
		 cellwo5.createCell(4).setCellValue("SL hoÃ n tráº£");
		 cellwo5.createCell(5).setCellValue("SL thá»±c táº¿");
		 
		 HSSFRow title1 = sheet1.createRow(0);
		 title1.createCell(0).setCellValue("2. BÃ¡o cÃ¡o chi tiáº¿t theo WorkOrder vÃ  tráº¡ng thÃ¡i");
		 HSSFRow cellwo21 = sheet1.createRow(2);
		 cellwo21.createCell(0).setCellValue("WorkOrder");
		 cellwo21.createCell(1).setCellValue("Date");
		 cellwo21.createCell(2).setCellValue("Line");
		 cellwo21.createCell(3).setCellValue("Model");
		 cellwo21.createCell(4).setCellValue("Plan");
		 cellwo21.createCell(5).setCellValue("Status");
		 HSSFRow cellwo51 = sheet1.createRow(5);
		 cellwo51.createCell(0).setCellValue("Item Code");
		 cellwo51.createCell(1).setCellValue("Item Name");
		 cellwo51.createCell(2).setCellValue("Item Number");
		 cellwo51.createCell(3).setCellValue("SL Nháº­n");
		 cellwo51.createCell(4).setCellValue("Tráº¡ng thÃ¡i");
		
		 
		 HSSFRow cellwo31 = sheet1.createRow(3);
		 cellwo31.createCell(0).setCellValue(wo.getName());
		 cellwo31.createCell(1).setCellValue(dateFormat.format(wo.getCreatedate()));
		 cellwo31.createCell(2).setCellValue( wo.getLine().getName());
		 cellwo31.createCell(3).setCellValue(wo.getModel().getPt_desc1());
		 cellwo31.createCell(4).setCellValue(wo.getQty());
		 cellwo31.createCell(5).setCellValue( wo.getStatus());
		 
		 HSSFRow title3 = sheet3.createRow(0);
		 title3.createCell(0).setCellValue("2. BÃ¡o cÃ¡o chi tiáº¿t theo WorkOrder vÃ  tráº¡ng thÃ¡i");
		 HSSFRow cellwo331 = sheet1.createRow(2);
		 cellwo331.createCell(0).setCellValue("WorkOrder");
		 cellwo331.createCell(1).setCellValue("Date");
		 cellwo331.createCell(2).setCellValue("Line");
		 cellwo331.createCell(3).setCellValue("Model");
		 cellwo331.createCell(4).setCellValue("Plan");
		 cellwo331.createCell(5).setCellValue("Status");
		 HSSFRow cellwo332 = sheet3.createRow(5);
		 
	
		 cellwo332.createCell(0).setCellValue("TÃªn phiÃªn");
		 cellwo332.createCell(1).setCellValue("Tráº¡ng thÃ¡i");
		 cellwo332.createCell(2).setCellValue("Giá»� yÃªu cáº§u");
		 cellwo332.createCell(3).setCellValue("Giá»� giao");
		 cellwo332.createCell(4).setCellValue("Item Code");
		 cellwo332.createCell(5).setCellValue("Item Name");
		 cellwo332.createCell(6).setCellValue("Item Number");
		 cellwo332.createCell(7).setCellValue("Sá»‘ LÆ°á»£ng YC");
		 cellwo332.createCell(8).setCellValue("Sá»‘ LÆ°á»£ng giao");
		
		 
		 HSSFRow cellwo333 = sheet3.createRow(3);
		 cellwo333.createCell(0).setCellValue(wo.getName());
		 cellwo333.createCell(1).setCellValue(dateFormat.format(wo.getCreatedate()));
		 cellwo333.createCell(2).setCellValue( wo.getLine().getName());
		 cellwo333.createCell(3).setCellValue(wo.getModel().getPt_desc1());
		 cellwo333.createCell(4).setCellValue(wo.getQty());
		 cellwo333.createCell(5).setCellValue( wo.getStatus());
	        int rowCount=6;
				   List<Object[]> lwo1 = dcdao.bcwo11(wo.getId());
				   Turn t = dcdao.getTurnSetup(woid);
				   List<List<Object>> l2 = reportsServive.getAddListReponse(woname);
				   String turnid = t==null?"":t.getId();
				   List<Object[]> ls21 = dcdao.bcwo12Setup(woid, turnid);
				   List<Object[]> lb22 = dcdao.bcwo12BS(woid, turnid);
				   
				   for(Object[] wo1:lwo1){
			        	 HSSFRow aRow = sheet.createRow(rowCount++);
			        	 aRow.createCell(0).setCellValue((String)wo1[0]);
			        	 aRow.createCell(1).setCellValue((String)wo1[1]);
			        	 aRow.createCell(2).setCellValue((String)wo1[2]);
			        	 aRow.createCell(3).setCellValue((Double)wo1[3]);
			        	 aRow.createCell(4).setCellValue((Double)wo1[4]);
			        	 aRow.createCell(5).setCellValue((Double)wo1[3]-(Double)wo1[4]);
			        } 
				    rowCount=6;  
				  
	         for(Object[] s21:ls21){
	        	 HSSFRow aRow = sheet1.createRow(rowCount++);
	        	 aRow.createCell(0).setCellValue((String)s21[0]);
	        	 aRow.createCell(1).setCellValue((String)s21[1]);
	        	 aRow.createCell(2).setCellValue((String)s21[2]);
	        	 aRow.createCell(3).setCellValue((Double)s21[3]);
	        	 aRow.createCell(4).setCellValue("Setup");
	        	
	        }
	         for(Object[] s22:lb22){
	        	 HSSFRow aRow = sheet1.createRow(rowCount++);
	        	 aRow.createCell(0).setCellValue((String)s22[0]);
	        	 aRow.createCell(1).setCellValue((String)s22[1]);
	        	 aRow.createCell(2).setCellValue((String)s22[2]);
	        	 aRow.createCell(3).setCellValue((Double)s22[3]);
	        	 aRow.createCell(4).setCellValue("BS");
	        }
				   
	          rowCount=6;
	         for(List<Object> s22:l2){
	        	 HSSFRow aRow = sheet3.createRow(rowCount++);
	        	 DetailComp dc = (DetailComp)s22.get(0);
				 ReceiptComp rc = (ReceiptComp)s22.get(1);
				 if(s22.size()>3){
	        	 DetailComp dc1 = (DetailComp)s22.get(2);
				 ReceiptComp rc1 = (ReceiptComp)s22.get(3);
				 DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				 aRow.createCell(3).setCellValue(dateFormat1.format(rc1.getDate()));
	        //	 aRow.createCell(3).setCellValue(rc1.getDate());
				 aRow.createCell(8).setCellValue(dc1.getQty());
				 }
	        	 aRow.createCell(0).setCellValue((String)rc.getTurn().getId());
	        	 aRow.createCell(1).setCellValue(rc.getType().compareTo(TypeComp.RequestBS)==0?"Setup":"BS");
	        	 aRow.createCell(2).setCellValue(rc.getClosetime().getClosetime().toString());
	        	 aRow.createCell(4).setCellValue(dc.getModel().getPt_part());
	        	 aRow.createCell(5).setCellValue(dc.getModel().getPt_desc1());
	        	 aRow.createCell(6).setCellValue(dc.getModel().getPt_desc2());
	        	 aRow.createCell(7).setCellValue(dc.getQty());
	        	 
	        }
	         workbook.close();
		        return workbook;
		}else{
			   return null;
		}
		}catch(Exception e){
			logger.info(e);
			e.printStackTrace();
			return null;
		}
		
	}
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	private static final String FILE_NAME = "D:\\CV\\LCTECH\\MyFirstExcel.xlsx";

    public static void main(String[] args) {
/*
    	XSSFWorkbook workbook = new XSSFWorkbook();
    	XSSFCellStyle cellStyle = workbook.createCellStyle();
    	cellStyle.setBorderTop(BorderStyle.MEDIUM);
    	cellStyle.setBorderBottom(BorderStyle.MEDIUM);
    	cellStyle.setBorderLeft(BorderStyle.MEDIUM);
    	cellStyle.setBorderRight(BorderStyle.MEDIUM);
        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datatype : datatypes) {
        	XSSFRow row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                    cell.setCellStyle(cellStyle);

                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");*/
    }
}
