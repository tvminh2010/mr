package com.dao;

import java.util.List;


import com.entity.Shift;

public interface ShiftDao {


	   public List<Shift> listShift();
		
		public Integer getShiftId();
		
		public void createShift(String name);
}
