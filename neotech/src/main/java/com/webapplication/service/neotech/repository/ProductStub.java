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
	 
	@Override
	public List<Product> getAllProducts() {
		return products;
	}

	@Override
	public Product getProduct(int productId) {
		for(Product product : products) {
			if(product.getProductId()==productId) {
				return product;
			}
		}
		return null;
	}

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
