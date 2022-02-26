package com.dao;

import java.util.List;

import com.entity.PSetting;

public interface PSettingDao {
	public List<PSetting> getList();
	public void insert(PSetting ps);

	public void delete(String id);
	void update(PSetting psetting);
	public float getValue(String name);
	PSetting getListById(Integer id);
	float getValue(boolean shift);
}
