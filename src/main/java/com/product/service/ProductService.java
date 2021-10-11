package com.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;

@Service
public interface ProductService {
	  Product addProduct(Product product);
	  Product deleteProduct(int prodId) throws ProductNotFoundException;
	  Product findProduct(int productId) throws ProductNotFoundException;
	  List<Product> showAllProducts();
	  Product updateProduct(int prodId, Product product)throws ProductNotFoundException;
}
