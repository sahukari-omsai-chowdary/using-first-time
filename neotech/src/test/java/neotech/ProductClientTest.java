package neotech;

import java.util.List;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.webapplication.service.neotech.model.Product;

import baseclasses.UnitTestBaseClass;

import com.webapplication.service.neotech.client.ProductClient;

public class ProductClientTest extends UnitTestBaseClass{
	ProductClient client;
	@BeforeMethod
	public void customSetupMethod() {
		client= new ProductClient();
	}
	@Test(description="verify that product is delivered for given ID")
	public void testGetProduct() {
		// Arrange
		//ProductClient client = new ProductClient();
		// Act
		Response product = client.getProduct(1);
		System.out.println(product.readEntity(Product.class));
		// Assert
		Assert.assertNotNull(product);
	}

	@Test(expectedExceptions = RuntimeException.class,description="verify that add user method returns when successfull")
	public void testGetBadRequest() {
		// Arrange
		//ProductClient client = new ProductClient();
		// Act
		Response response = client.getProduct(-2);
		// Assert
		Assert.assertEquals(Response.status(Status.BAD_REQUEST).build(), response);
	}

	@Test(expectedExceptions = RuntimeException.class,description="verify that add user method returns when successfull")
	public void testGetRequestNotFound() {
		// Arrange
		//ProductClient client = new ProductClient();
		// Act
		Response response = client.getProduct(9999999);
		// Assert
		Assert.assertEquals(Response.status(Status.NOT_FOUND).build(), response);
	}

	@Test(description="verify that add user method returns when successfull")
	public void testGetAllProducts() {
		// Arrange
		//ProductClient client = new ProductClient();
		// Act
		List<Product> products = client.getAllProducts();
		// Assert
		Assert.assertNotNull(products);
	}

	@Test(description="verify that add user method returns when successfull")
	public void testAddProduct() {
		// Arrange
		//ProductClient client = new ProductClient();
		// Act
		Response added = client.addProduct(new Product(3, 20000f, "Hello moto", "Motrola"));
		// Assert
		Assert.assertNotNull(added);
	}

	@Test(description="verify that add user method returns when successfull")
	public void testUpdateProduct() {
		// Arrange
		//ProductClient client = new ProductClient();
		// Act
		Response updated = client.updateProduct(1, new Product(1, 15000f, "Innovation Never Stands Still", "Lenovo"));
		// Assert
		Assert.assertNotNull(updated);
	}

	@Test(description="verify that add user method returns when successfull",enabled = false)
	public void testDeleteProduct() {
		// Arrange
		//ProductClient client = new ProductClient();
		// Act
		Response deleted = client.deleteProduct(2);
		// Assert
		Assert.assertNotNull(deleted);
	}

}
