package com.webapplication.service.neotech;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import com.webapplication.service.neotech.repository.ProductStub;
import com.webapplication.service.neotech.model.Product;
import com.webapplication.service.neotech.repository.ProductRepository;

@Path("productservices")
public class ProductResource {
	private ProductRepository productRepository = new ProductStub();
	/*
	 * by going to url http://localhost:8080/neotech/rest/productservices/
	 * we get to "NeoTech Products" in the pagere\\
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "NeoTech Products";
	}
	/*
	 * by going to url http://localhost:8080/neotech/rest/productservices/products
	 * we get all products in json format
	 */
	@GET
	@Path("products")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Product> getProducts() {
		List<Product> products =  productRepository.getAllProducts();
		return products;
	}
	
	/*
	 * by going to url http://localhost:8080/neotech/rest/productservices/products/{productId}
	 * we get see the product linked with productId in json format if it is present or else
	 * It will return NOT FOUND response if product is not present in list or else
	 * it will return BAD REQUEST response if product id <1
	 */
	@GET
	@Path("products/{productId}")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response getProduct(@PathParam("productId") int productId ) {
		if(productId<1) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Product product = productRepository.getProduct(productId);
		if(product==null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().entity(product).build();
	}
	/*
	 * by going to POST method url http://localhost:8080/neotech/rest/productservices/
	 * We provide json data of product so that the data goes to products list
	 * 
	 * 
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) throws URISyntaxException {	
		int newProductId = productRepository.addProduct(product);
		URI uri = new URI("/productServices/"+newProductId);
		return Response.created(uri).build();
	}
	
	/*
	 * by going PUT METHOD to url http://localhost:8080/neotech/rest/productservices/products/{productId}
	 * we  provide json data of product so that the data replace the product linked with given product id
	 * It will return ok response if product is updated
	 * it will return not modified response if product if not updated 
	 */
	@PUT
	@Path("products/{productId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduct(@PathParam("productId") int productId, Product product) {
		product.setProductId(productId);
		if (productRepository.updateProduct(productId, product)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}
	/*
	 * by going DELETE METHOD to url http://localhost:8080/neotech/rest/productservices/products/{productId}
	 * The product linked with given product id will be deleted
	 * It will return ok response if product is deleted
	 * it will return not modified response if product if not deleted
	 */
	@DELETE
	@Path("products/{productId}")
	public Response deleteProduct(@PathParam("productId") int productId) {
		if (productRepository.deleteProduct(productId)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}
}
