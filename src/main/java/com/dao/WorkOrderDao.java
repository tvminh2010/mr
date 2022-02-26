package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.CloseTime;
import com.entity.Product;
import com.entity.TypeComp;
import com.entity.WorkOrder;



public interface WorkOrderDao {
  List<WorkOrder> getListWorkOrderByStatus(Integer status);
  List<WorkOrder> getListWorkOrderByLine(Integer lineid);
  List<WorkOrder> getListWorkOrderByLineStatus(Integer lineid);
  List<WorkOrder> getListWorkOrderByLineStatusIsSetup(Integer lineid);
  List<WorkOrder> getListWorkOrderByLineStatusIsBx(Integer lineid);
  List<WorkOrder> getListPage(Date startdate, Date enddate,Integer page,String workorder,String linename,String item);
  Long getSize();
  WorkOrder saveList(List<WorkOrder> listWO);
  WorkOrder save(WorkOrder wo);
  Product getProduct(String woid);
  WorkOrder getWObyLineProduct(String productid, Integer lineid,Integer type);
  WorkOrder getWObyId(String woid);
  WorkOrder getWObyName(String name);
  WorkOrder checkWObyName(String name,String woid);
  List<Object[][]> getDetailTotal(String woid);
  List<String> getCowo(boolean co);
  void delete(WorkOrder ct);
  void deletelist(String lct);
  
}
