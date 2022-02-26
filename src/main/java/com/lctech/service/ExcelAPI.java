package com.lctech.service;

public interface ExcelAPI {
	
	public void readTableToExcel(Object[] o);
	public void readExcelToTable(String path, Object[] o);

}
