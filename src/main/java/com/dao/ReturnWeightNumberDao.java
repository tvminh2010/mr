package com.dao;


import java.util.Date;

import com.entity.ReturnWeightNumber;

public interface ReturnWeightNumberDao {
   public void save(ReturnWeightNumber ct);
   public Integer getNumberbyDate(Date d);
}
