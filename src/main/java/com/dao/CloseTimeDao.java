package com.dao;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.entity.CloseTime;

public interface CloseTimeDao {
   public void save(CloseTime ct);
   List<CloseTime> getList();
   CloseTime getNextTime();
   CloseTime getCurrentTime();
   CloseTime getCurrentTime(Time d  );
   CloseTime getById(Integer id);
   void delete(CloseTime ct);
   void deletelist(String lct);
   Boolean checkDouble(CloseTime lct);
CloseTime getNextTime(Time d);

}
