package com.dao;

import java.util.Date;
import java.util.List;

import com.entity.CloseTime;

public interface CloseTimeDao {
   public void save(CloseTime ct);
   List<CloseTime> getList();
   CloseTime getNextTime();
   CloseTime getCurrentTime();
   CloseTime getById(Integer id);
   void delete(CloseTime ct);
   void deletelist(String lct);
   Boolean checkDouble(CloseTime lct);
}
