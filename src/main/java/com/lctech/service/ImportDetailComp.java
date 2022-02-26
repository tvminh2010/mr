package com.lctech.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.controller.MainController;
import com.dao.DetailCompDao;
import com.dao.LineNoDao;
import com.dao.ProductDao;
import com.dao.ReceiptCompDao;
import com.dao.UserDao;
import com.dao.WorkOrderDao;
import com.config.Config;
import com.entity.DetailComp;
import com.entity.LineNo;
import com.entity.ReceiptComp;
import com.entity.ResponseInfo;
import com.entity.TypeComp;
import com.entity.User;
import com.entity.WorkOrder;
@Service
public class ImportDetailComp {
	private static final Logger logger = Logger.getLogger(ImportDetailComp.class);
	@Autowired
	private WorkOrderDao wodao;
	@Autowired
	private ReceiptCompDao rcdao;
	@Autowired
	private ProductDao pdao;
	@Autowired
	private DetailCompDao dcdao;
	@Autowired
	private LineNoDao ldao;
	@Autowired
	private UserDao usdao;
	@Autowired
	private  Config config;
	public List<ReceiptComp> readExcel(MultipartFile excelfile){
		logger.info(" read exxcel giao nhan");
		List<WorkOrder> listwo = new ArrayList<WorkOrder>();
		List<ReceiptComp> listrc = new ArrayList<ReceiptComp>();
	
		HSSFWorkbook workbook;

		try {
			workbook = new HSSFWorkbook(excelfile.getInputStream());
			   try {
					//logger.info(" getOriginalFilename"+  config.getDataExcelreponse() +""+ config.getDataExcel() + excelfile.getOriginalFilename());
                     String fn = excelfile.getOriginalFilename();
					//String fn = "T201807171230_RequestBS-1.xls";
					if(fn.indexOf("-")>0) {
                     String extendfn = fn.substring(fn.indexOf("-"),fn.indexOf(".xls"));
                    // logger.info("extendfn:"+extendfn);
                     fn = fn.replaceAll(extendfn, "");
					}
					logger.info(" getOriginalFilename"+  config.getDataExcelreponse() +""+ config.getDataExcel() +fn);

		        	FileOutputStream fos = new FileOutputStream(config.getDataExcelreponse() + fn );
					workbook.write(fos);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			logger.info(" read exxcel giao nhan" + workbook.getNumberOfSheets());
			int i=0;
			while(i<workbook.getNumberOfSheets()){
				logger.info(i);
			
			
			HSSFSheet worksheet = workbook.getSheetAt(i);
		   int j=5;
			while (j <= worksheet.getLastRowNum()) {
				DetailComp dc = new DetailComp();
				
				HSSFRow row = worksheet.getRow(j++);
				
				if (row.getCell(10) != null && row.getCell(10).getNumericCellValue()>=0) {
					
					String linename = row.getCell(1).getStringCellValue();
					String productid = row.getCell(2).getStringCellValue();
					String woname = row.getCell(11).getStringCellValue();
					LineNo line = ldao.getLineNoName(linename);
					WorkOrder wo = new WorkOrder();
					wo = wodao.getWObyName(woname);
					if(wo==null){
					wo =wodao.getWObyLineProduct(productid, line.getId(),2);
					}
					if(wo==null || wo.getId() == null){
						continue;
						}
					logger.info("wo:"+wo.toString());
					int k=0;
					logger.info("listwo:"+listwo.size());
					while(listwo.size()!=0 && k<listwo.size() ){
						logger.info("listwojjj:"+wo.getId() +"_"+listwo.get(k).getId()) ;
						if(wo.getId().equalsIgnoreCase(listwo.get(k).getId())){
							break;
						}
						k++;
					}
					if(listwo.size()==0 || k==listwo.size()){
						ReceiptComp rc = new ReceiptComp();
						rc.setWo(wo);
						rc.setDate(new Timestamp(new java.util.Date().getTime()));
						String id1 = UUID.randomUUID().toString();
						rc.setId(id1);
						rc.setType(TypeComp.Response);
						User us = usdao.getUserByName(getPrincipal());
						rc.setUs(us);
						String id = UUID.randomUUID().toString();
						dc.setId(id);
						dc.setModel(pdao.getProductById(row.getCell(5).getStringCellValue()));
						dc.setQty((Double)row.getCell(10).getNumericCellValue());
						dc.setReceipt(rc);
						List<DetailComp> ldc = new ArrayList<DetailComp>();
						ldc.add(dc);
						rc.setlDetailComp(ldc);
					    listwo.add(wo);
						listrc.add(rc);
					}else{
						String id = UUID.randomUUID().toString();
						dc.setId(id);
						dc.setModel(pdao.getProductById(row.getCell(5).getStringCellValue()));
						dc.setQty(row.getCell(10).getNumericCellValue());
						dc.setReceipt(listrc.get(k));
						listrc.get(k).getlDetailComp().add(dc);
					}
				}
				
			}
			i++;
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		return listrc;
	}
	public 	HashMap<String,List<ResponseInfo>> readExcelReponse(MultipartFile excelfile){
		logger.info(" read exxcel giao nhan");
		HashMap<String,List<ResponseInfo>> maplist = new HashMap<String,List<ResponseInfo>>();

		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(excelfile.getInputStream());
			   try {
		        	FileOutputStream fos = new FileOutputStream(config.getDataExcel() + excelfile.getOriginalFilename() +".xls");
					workbook.write(fos);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			logger.info(" read exxcel giao nhan" + workbook.getNumberOfSheets());
			int i=0;
			while(i<workbook.getNumberOfSheets()){
				logger.info(i);
				List<ResponseInfo> lsheet = new ArrayList<ResponseInfo>();
			
			HSSFSheet worksheet = workbook.getSheetAt(i);
		   int j=5;
			while (j <= worksheet.getLastRowNum()) {
				DetailComp dc = new DetailComp();
				
				HSSFRow row = worksheet.getRow(j++);
				
				if (row.getCell(10) != null) {
					ResponseInfo res = new ResponseInfo();
			
					Integer stt = new Double(row.getCell(0).getNumericCellValue()).intValue();
					res.setStt(stt);
					String linename = row.getCell(1).getStringCellValue();
					res.setLine(linename);
					String productid = row.getCell(2).getStringCellValue();
					res.setModel(productid);
					String itemName = row.getCell(3).getStringCellValue();
					res.setItemName(itemName);
					String itemNumber = row.getCell(4).getStringCellValue();
					res.setItemNumber(itemNumber);
					String itemCode = row.getCell(5).getStringCellValue();
					res.setItemCode(itemCode);
					String unit = row.getCell(6).getStringCellValue();
					res.setUnit(unit);
					String timeRequired = row.getCell(7).getStringCellValue();
					res.setTimeRequired(timeRequired);
					Double qtyplan = row.getCell(8).getNumericCellValue();

					res.setQtyplan(qtyplan);
					String note = row.getCell(9).getStringCellValue();

					res.setNote(note);
					Double qtyreal = row.getCell(10).getNumericCellValue();

					res.setQtyreal(qtyreal);
					String woname = row.getCell(11).getStringCellValue();
					res.setWo(woname);
				
					lsheet.add(res);
				}
				maplist.put(worksheet.getSheetName(), lsheet);
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maplist;
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
}
