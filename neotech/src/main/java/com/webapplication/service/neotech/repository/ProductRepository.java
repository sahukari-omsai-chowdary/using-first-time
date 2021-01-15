package com.webapplication.service.neotech.repository;

import java.util.List;

import com.webapplication.service.neotech.model.*;

public interface ProductRepository {
	
	List<Product> getAllProducts();
	
	Product getProduct(int productId);
	
	int addProduct(Product product);
	
	boolean updateProduct(int productId, Product product);
	
	boolean deleteProduct(int productId);

}
