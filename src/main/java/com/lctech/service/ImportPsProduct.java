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
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dao.CoreWeightDao;
import com.dao.ProductDao;
import com.dao.PsProductDao;
import com.entity.CoreWeight;
import com.entity.Product;
import com.entity.PsProduct;
@Service
public class ImportPsProduct {
	private static final Logger logger = Logger.getLogger(ImportPsProduct.class);

	@Autowired
	private ProductDao pdao;
	
	@Autowired
	private PsProductDao psdao;
	
	@Autowired
	private CoreWeightDao cwdao;
	public List<PsProduct> readExcel(MultipartFile excelfile){
		List<PsProduct> listP = new ArrayList<PsProduct>();
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
				PsProduct f = new PsProduct();
				Row row = worksheet.getRow(i++);
				 String pscomp = formatter.formatCellValue(row.getCell(1));
				if (row.getCell(1) != null && pscomp != "") {
					
				    f.setPs_comp(new Product(pscomp));
				    String pspar = formatter.formatCellValue(row.getCell(0));
					f.setPs_par(new Product(pspar));
					f.setPs_qty_per(row.getCell(5).getNumericCellValue());
					listP.add(f);
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
	public List<CoreWeight> readExcelReturnBom(MultipartFile excelfile){
		List<CoreWeight> listP = new ArrayList<CoreWeight>();
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
				CoreWeight f = new CoreWeight();
				Row row = worksheet.getRow(i++);
				
				if (row.getCell(1) != null ) {
					 String ptpart = formatter.formatCellValue(row.getCell(1));
					 //logger.info(row.getCell(5));
					 String typecore = row.getCell(5)!=null?row.getCell(5).getStringCellValue():"";
					 Double qty = Double.valueOf(row.getCell(6)!=null?row.getCell(6).getNumericCellValue():0.0);
					 Double rate = Double.valueOf(row.getCell(7)!=null?row.getCell(7).getNumericCellValue():1.0);
					 String vendor = row.getCell(9)!=null?row.getCell(9).getStringCellValue():"";
				      f.setRate(rate);
				      f.setCoreweight(qty);
				      f.setPt_part(ptpart);
				      f.setTypecore(typecore);
				      f.setVendor(vendor);
				      listP.add(f);
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
			logger.debug(e);
			e.printStackTrace();
			return null;
		}
	}
}
