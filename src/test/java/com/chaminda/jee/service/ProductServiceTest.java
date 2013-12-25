package com.chaminda.jee.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.chaminda.jee.dao.ProductDao;
import com.chaminda.jee.entity.Product;

@RunWith(Arquillian.class)
public class ProductServiceTest{

	@Inject
	ProductService productService;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(ProductDao.class)
				.addPackage(ProductService.class.getPackage()).addPackage(Product.class.getPackage())
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Test
	public void shouldInitialiseProductService(){
		assertNotNull(productService);
	}
	
	@Test
	public void shouldSucessfullySaveGivenValidProduct(){
		// Given
		Product product = new Product();
		product.setProductName("Apple");
		
		//When
		boolean status = productService.saveProduct(product);
		
		//Then
		assertTrue(status);
		assertNotNull(product.getProductId());
	}
	
	@Test 
	public void shouldSucessfullyRetriveProductById(){
		//Given
		Product savedProduct = persistProductForTest();
		
		//When
		Product retrivedProduct = productService.findProductByID(savedProduct.getProductId());

		//Then
		assertNotNull(retrivedProduct);
	}
	
	private Product persistProductForTest(){
		Product product = new Product();
		product.setProductName("Apple");
		productService.saveProduct(product);
		
		return product;
	}
	@Test
	public void shouldSucessfullyUpdateAValidProduct(){
		// Given
		Product product = persistProductForTest();
		product.setProductName("Mango");
		
		// When
		Product updated = productService.updateProduct(product);
		
		// Then
		assertNotNull(updated);
		assertEquals(product.getProductId(), updated.getProductId());
		assertEquals(product.getProductName(), updated.getProductName());
	}
	
	@Test
	public void shouldSucessfullyDeleteAGivenProduct(){
		// Given
		Product product = persistProductForTest();
		long productID = product.getProductId();
		
		//Then
		boolean status = productService.deleteProduct(productID);
		Product retrivedAfterDelete = productService.findProductByID(productID);
		
		//When
		assertTrue(status);
		assertNull(retrivedAfterDelete);
	}
}


