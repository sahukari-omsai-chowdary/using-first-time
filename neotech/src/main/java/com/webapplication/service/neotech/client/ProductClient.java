package com.webapplication.service.neotech.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import com.webapplication.service.neotech.model.Product;

public class ProductClient {
	
	private static String targetURI = "http://localhost:8080/neotech/rest/productservices";
	/*
	 * 
	 */
	static WebTarget getWebTarget() {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		return client.target(targetURI);
	}
	
	public Response getProduct(int productId) {
		WebTarget webTarget = getWebTarget();
		Response getResponse = webTarget.path("/products/" + productId)
				.request(MediaType.APPLICATION_JSON)
				.get(Response.class);
		if (getResponse.getStatus() != 200) {
			throw new RuntimeException(getResponse.getStatus() + " -> GET process failed");
		}
		return getResponse;
	}
	
	public List<Product> getAllProducts(){
		WebTarget webTarget = getWebTarget();
		List<Product> getResponse = webTarget.path("/products")
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Product>>() {
				});
		return getResponse;
	}
	
	public Response addProduct(Product product) {
		WebTarget webTarget = getWebTarget();
		Response getResponse = webTarget.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(product, MediaType.APPLICATION_JSON));
		if (getResponse.getStatus() != 201) {
			throw new RuntimeException(getResponse.getStatus() + " -> POST operation failed");
		}
		return getResponse;
	}

	public Response updateProduct(int productId, Product product) {
		WebTarget webTarget = getWebTarget();
		Response getResponse = webTarget.path("/products/" + productId)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(product, MediaType.APPLICATION_JSON));
		if (getResponse.getStatus() != 200) {
			throw new RuntimeException(getResponse.getStatus() + " -> PUT operation failed");
		}
		return getResponse;

	}

	public Response deleteProduct(int productId) {
		WebTarget webTarget = getWebTarget();
		Response getResponse = webTarget.path("/products/" + productId)
				.request(MediaType.APPLICATION_JSON).delete();
		if (getResponse.getStatus() != 200) {
			throw new RuntimeException(getResponse.getStatus() + " -> DELETE operation failed");
		}
		return getResponse;

	}
}
