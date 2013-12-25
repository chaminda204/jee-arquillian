package com.chaminda.jee.service;

import com.chaminda.jee.entity.Product;

public interface ProductService {

	boolean saveProduct(Product product);

	Product findProductByID(long productID);

	Product updateProduct(Product productUpdate);

	boolean deleteProduct(long productId);

}