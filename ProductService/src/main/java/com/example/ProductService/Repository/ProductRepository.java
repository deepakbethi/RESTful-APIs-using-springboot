package com.example.ProductService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.ProductService.entity.Product;
import com.example.ProductService.model.ProductResponse;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	

}
