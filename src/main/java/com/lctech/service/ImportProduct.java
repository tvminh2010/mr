package com.lctech.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.ProductDao;
import com.entity.Product;

@Service
public class ImportProduct {
	private static final Logger logger = Logger.getLogger(ImportProduct.class);

	@Autowired
	private ProductDao pdao;
	

	
	
	public List<Product> readExcel(MultipartFile excelfile){
		List<Product> listP = new ArrayList<Product>();
		HSSFWorkbook workbook =null;
		XSSFWorkbook sorkbook= null;
		try{
			
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
			int i = 0;
			while (i <= worksheet.getLastRowNum()) {
				DataFormatter formatter = new DataFormatter();
				Product f = new Product();
				Row row = worksheet.getRow(i++);
				if (row.getCell(1) != null
						) {
					 String ptpart = formatter.formatCellValue(row.getCell(0));
					Product f12 = new Product();
					f12 = pdao.getProductById(ptpart);
					if(f12!=null){
						continue;
					}else{
					
					 String ptum = formatter.formatCellValue(row.getCell(1));
					 
					 String ptdesc1 = formatter.formatCellValue(row.getCell(2));
					 
					 String ptdesc2 = formatter.formatCellValue(row.getCell(3));
					
					 
					f.setPt_part(ptpart);
					f.setPt_desc1(ptdesc1);
					f.setPt_desc2(ptdesc2);
					
					f.setPt_um(ptum);
					
					
				
					
					
					listP.add(f);
					}
				}

			}
			  if(xlsx){
			    	sorkbook.close();
			    }else {
			    	workbook.close();
			    }
		//	workbook.close();
			return listP;
		}catch(IOException e)
		{
			logger.info(e);
			e.printStackTrace();
			return null;
		}
	}
	public List<Product> readExcelPackage(MultipartFile excelfile){
		List<Product> listP = new ArrayList<Product>();
		HSSFWorkbook workbook =null;
		XSSFWorkbook sorkbook= null;
		try{

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
			int i = 1;
			while (i <= worksheet.getLastRowNum()) {
				DataFormatter formatter = new DataFormatter();
				Row row = worksheet.getRow(i++);
				if (row.getCell(2) != null
						&& row.getCell(2).getNumericCellValue()>0) {
				
					 String desc1 = formatter.formatCellValue(row.getCell(1));
					Product p = pdao.getProductByDesc1(desc1);
					if(p==null || p.getPt_part()==""){
						continue;
					}
					//-p.setPt_package(row.getCell(2).getNumericCellValue());
					listP.add(p);

				}

			}
		//	workbook.close();
			  if(xlsx){
			    	sorkbook.close();
			    }else {
			    	workbook.close();
			    }
			return listP;
		}catch(IOException e)
		{
			logger.info(e);
			e.printStackTrace();
			return null;
		}
	}
}
