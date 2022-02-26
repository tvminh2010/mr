package com.dao;

import java.util.List;

import com.entity.FG;

public interface FGDao {

	List<FG> getList();



	Boolean insert(FG fg);

	

	String findbyvtepartcode(String vtepartcode, String customer);

	String findbyfgcode(String fgcode, String customer);

	void del(FG fg);

	FG findbyid(String id);

	void update(FG fg);

}
