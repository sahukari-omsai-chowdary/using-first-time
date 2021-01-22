package com.webapplication.service.neotech.repository;

import java.util.ArrayList;
import java.util.List;

import com.webapplication.service.neotech.model.Product;

public class ProductStub implements ProductRepository {
	static List<Product> products = new ArrayList<Product>();

	
	  static{ 
		  products.add(new Product(products.size()+1, 15000f, "description 1", "Name 1"));
		  products.add(new Product(products.size()+1, 10000f, "description 2", "Name 2"));
	  }
		/*
		 * Will need to return a List of all Product in List<Product> format
		 */
	@Override
	public List<Product> getAllProducts() {
		return products;
	}
	/*
	 * Will take an productId and try to return the product Object
	 * If they is no such product ID Null is being returned
	 */
	@Override
	public Product getProduct(int productId) {
		for(Product product : products) {
			if(product.getProductId()==productId) {
				return product;
			}
		}
		return null;
	}
	/*
	 * By the given product object we will add productId and send it to 
	 * List<Product> and the returned value is productId attached to it
	 */
	@Override
	public int addProduct(Product product) {
		int newProductId = products.size()+1;
		product.setProductId(newProductId);
		products.add(product);
		return newProductId;
	}
	
	public static void printall() {
		for(Product product : products) {
			System.out.println(product.getProductId()+" "+product.getProductName());
		}
	}

	public Product findProduct(int productId) {
		Product foundedProduct = null;
		for(Product product : products) {
			if(product.getProductId()==productId) {
				foundedProduct= product;
				break;
			}
		}
		return foundedProduct;
	}
	/*
	 * By the given productID and Product
	 * we are replacing the product attached to productId with given product
	 * IF update is success full return true else false
	 */
	@Override
	public boolean updateProduct(int productId, Product product) {
		Product toUpdate = new ProductStub().findProduct(productId);
		int index = products.indexOf(toUpdate);
		if (index >= 0) {
			product.setProductId(productId);
			products.set(index, product);
			return true;
		}
		return false;
	}
	/*
	 * By the given productID we will find the product in list
	 * and remove it from list along with product id
	 * if deletion success return true else false
	 */
	@Override
	public boolean deleteProduct(int productId) {
		Product toDelete = new ProductStub().findProduct(productId);
		int index = products.indexOf(toDelete);
		if (index >= 0) {
			products.remove(index);
			return true;
		}
		return false;
	}

}
