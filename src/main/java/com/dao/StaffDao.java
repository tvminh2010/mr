package com.dao;

import java.util.List;

import com.entity.Staff;

public interface StaffDao {
	
	public List<Staff> getList();
	public Staff getByCode(String code);
	void insert(Staff staff);
	void update(Staff staff);
	
	

	public Staff getByID(String id);
	void delete(Staff st);

	void delete(String id);
	List<Staff> getList(String part);
}
