package com.dao;

import java.util.List;

import com.entity.PsProduct;

public interface PsProductDao {

	List<PsProduct>  getLPs(String productid);
	List<PsProduct> getLPs();
	Long getSize();
	void delete(PsProduct p);
	PsProduct saveAll(List<PsProduct> p);
	void  save(PsProduct p);
	void deleteAll();
	PsProduct getByName(String pid,String psid);
} 
