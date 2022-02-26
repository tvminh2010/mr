package com.dao;

import java.util.HashMap;
import java.util.List;

import com.entity.CoreWeight;


public interface CoreWeightDao {
   public void save(CoreWeight t);
   CoreWeight getByid(String id);
   CoreWeight getByItemcode(String itemcode);
   List<CoreWeight> getListAll();
   public void deleteAll();
   public  List<Object[]> getTypeCore();
}
