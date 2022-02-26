package com.dao;

import java.util.List;

import com.entity.WeightElectricQueue;

public interface WeightElectricQueueDao {

	public void save(WeightElectricQueue d);
	public void delete(WeightElectricQueue d);
	public List<WeightElectricQueue> getAll();
	public void deleteAll();
}
