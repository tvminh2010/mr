package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.CloseTime;
import com.entity.ReturnExcel;

public interface ReturnExcelDao {
   public void save(ReturnExcel ct);
   List<ReturnExcel> getList(Integer page);
   Long getSize();
}
