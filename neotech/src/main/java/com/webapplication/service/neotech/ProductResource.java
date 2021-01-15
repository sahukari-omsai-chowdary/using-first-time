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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
	private Client client;
	
	public ProductResource(){
		client = ClientBuilder.newClient();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "NeoTech Products";
	}
	
	@GET
	@Path("products")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Product> getProducts() {
		List<Product> products =  productRepository.getAllProducts();
		return products;
	}
	
	
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) throws URISyntaxException {	
		int newProductId = productRepository.addProduct(product);
		URI uri = new URI("/productServices/"+newProductId);
		return Response.created(uri).build();
	}
	
	
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
