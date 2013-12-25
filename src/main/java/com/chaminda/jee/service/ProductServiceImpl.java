package com.chaminda.jee.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.chaminda.jee.dao.ProductDao;
import com.chaminda.jee.entity.Product;

@Stateless
public class ProductServiceImpl implements ProductService {

	@Inject
	ProductDao productDao;

	@Override
	public boolean saveProduct(Product product) {
		boolean status = productDao.saveProduct(product);
		return status;
	}

	@Override
	public Product findProductByID(long productID) {
		return productDao.findProductByID(productID);
	}

	@Override
	public Product updateProduct(Product productUpdate) {
		return productDao.updateProduct(productUpdate);
	}

	@Override
	public boolean deleteProduct(long productId) {
		return productDao.deleteProduct(productId);
	}

}
