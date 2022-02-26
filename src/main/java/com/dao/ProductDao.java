package com.dao;

import java.util.List;

import com.entity.Product;

public interface ProductDao {

	public List<Product> listProduct();
	
	public Integer getProductId();
	public Product getProductById(String string);
	
	public void createProduct(String name);
	
	public void insert(Product p);
	public void delete(String id);

	public void delete(Product model);
	public void deleteAll();
	void saveAll(List<Product> lp);
	void save(Product p);
	Product getProductByDesc1(String desc1);
	Long getSize();
	List<Product> listProductV_CNC();
	List<String> listpt_part();
	public void deleteAllId(List<String> lid);
}
