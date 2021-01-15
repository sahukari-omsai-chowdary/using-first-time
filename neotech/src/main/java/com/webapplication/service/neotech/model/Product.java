package com.webapplication.service.neotech.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private int productId;
	private String productName;
	private float productPrice;
	private String productDescription;
	
	public Product(int productId, float productPrice, String productDescription, String productName) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
	}
	
	public Product(int productId) {
		this.productId = productId;
	}
	
	public Product() {
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getProductId() {
		return this.productId;
	}
	
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	
	public float getProductPrice() {
		return this.productPrice;
	}
	
	public String getProductDescription() {
		return this.productDescription;
	}
	
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductName() {
		return this.productName;
	}
	
	

}
