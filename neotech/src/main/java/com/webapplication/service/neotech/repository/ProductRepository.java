package com.webapplication.service.neotech.repository;

import java.util.List;

import com.webapplication.service.neotech.model.*;
/*
 * 
 */
public interface ProductRepository {
	/*
	 * Will need to return a List of all Product in List<Product> format
	 */
	List<Product> getAllProducts();
	/*
	 * Will take an productId and try to return the product Object
	 * If they is no such product ID Null is being returned
	 */
	Product getProduct(int productId);
	/*
	 * By the given product object we will add productId and send it to 
	 * List<Product> and the returned value is productId attached to it
	 */
	int addProduct(Product product);
	/*
	 * By the given productID and Product
	 * we are replacing the product attached to productId with given product
	 * IF update is success full return true else false
	 */
	boolean updateProduct(int productId, Product product);
	/*
	 * By the given productID we will find the product in list
	 * and remove it from list along with product id
	 * if deletion success return true else false
	 */
	boolean deleteProduct(int productId);

}
