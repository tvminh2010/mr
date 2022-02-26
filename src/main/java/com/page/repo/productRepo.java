package com.page.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Product;

public interface  productRepo extends JpaRepository<Product, Long> { 

}
