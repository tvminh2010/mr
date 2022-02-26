package com.dao;

import java.util.List;

import com.entity.Turn;

public interface TurnDao {
   public void save(Turn t);
   Turn getByid(String id);
   List<Turn> getlinkdownload(int page);
   Long getSize();
   void delete(Turn t);
   void update(Turn t);
}
