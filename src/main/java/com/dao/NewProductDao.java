package com.dao;

import java.util.List;

import com.entity.NewProduct;
import com.entity.Product;

public interface NewProductDao {

	List<NewProduct> listNewProduct();

	List<NewProduct> listNewProductV_CNC();

	List<NewProduct> listNewProduct2();

	List<NewProduct> listPST();

	void insert(NewProduct p);

	void delete(String id);

	void delete(NewProduct model);

	

	NewProduct getNewProductByName(String name);

	NewProduct getNewProductById(String id);

	void update(NewProduct model);

	List<NewProduct> listNewProductAll();

	
}
