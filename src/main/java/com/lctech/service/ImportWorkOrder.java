package com.lctech.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.LineNoDao;
import com.dao.ProductDao;
import com.dao.UserDao;
import com.entity.User;
import com.entity.WorkOrder;

@Service
public class ImportWorkOrder {
	private static final Logger logger = Logger.getLogger(ImportWorkOrder.class);

	@Autowired
	private LineNoDao lndao;
	@Autowired
	private ProductDao pdao;
	@Autowired
	private UserDao usdao;
	
	public List<WorkOrder> readExcel(MultipartFile excelfile) {
		List<WorkOrder> listWO = new ArrayList<WorkOrder>();
		
		HSSFWorkbook workbook =null;
		XSSFWorkbook sorkbook= null;
		
		
		try {
			int i = 1;
			 String fileFrags = excelfile.getOriginalFilename();
			 Sheet worksheet =null;
			    Boolean xlsx =  fileFrags.contains("xlsx");
		    if(xlsx){
		    	sorkbook = new XSSFWorkbook(excelfile.getInputStream());
			    worksheet = sorkbook.getSheetAt(0);
		    }else {
		    	workbook = new HSSFWorkbook(excelfile.getInputStream());
			    worksheet = workbook.getSheetAt(0);
		    }
			while (i <= worksheet.getLastRowNum()) {
				WorkOrder f = new WorkOrder();
				Row row = worksheet.getRow(i++);
				if (row.getCell(1) != null) {
					String id = UUID.randomUUID().toString();
					f.setId(id);
					
				  //  int woid = dedao.getQty(new Date());
				//	f.setNameDefault(woid);
					DataFormatter formatter = new DataFormatter();
					 String woname = formatter.formatCellValue(row.getCell(0));
					f.setName(woname);
					 User us = usdao.getUserByName(getPrincipal());
					 f.setUs(us);
				    f.setStatus(1);
				    f.setCreatedate(new Date(new java.util.Date().getTime()));
				    String linename = formatter.formatCellValue(row.getCell(1));
					f.setLine(lndao.getLineNoName(linename));
					  String ptname = formatter.formatCellValue(row.getCell(2));
					f.setModel(pdao.getProductByDesc1(ptname));
					 
					f.setQty(row.getCell(3).getNumericCellValue());
					listWO.add(f);

				}

			}
			   if(xlsx){
			    	sorkbook.close();
			    }else {
			    	workbook.close();
			    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info(e);
			e.printStackTrace();
		}
		return listWO;
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
	  public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	  {
	      File convFile = new File( multipart.getOriginalFilename());
	      multipart.transferTo(convFile);
	      return convFile;
	  }
}
