package com.chaminda.jee.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.chaminda.jee.entity.Product;

@Stateless
public class ProductDao {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean saveProduct(Product product) {
		entityManager.persist(product);
		return true;
	}

	public Product findProductByID(long productID) {
		return entityManager.find(Product.class, productID);
	}

	public Product updateProduct(Product productUpdate) {
		entityManager.merge(productUpdate);
		return productUpdate;
	}

	public boolean deleteProduct(long productId) {
		
		boolean status = false;
		Product product = entityManager.find(Product.class, productId);
		
		if (product != null) {
			entityManager.remove(product);
			status = true;
		}
		return status;
	}



}
