package com.product.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;
import com.product.repository.ProductRepository;

public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;

	@Override
	public Product addProduct(Product product) {
		repository.save(product);
		return product;
	}

	@Override
	public Product deleteProduct(int prodId) throws ProductNotFoundException {
		Product p = null;
		try {
			p = repository.findById(prodId).get();
			repository.delete(p);
			System.out.println("Product with id "+p.getProdId()+ " is deleted");
		} catch (NoSuchElementException e) {
			throw new ProductNotFoundException("Product with id "+prodId +" does not exists");
		}
		catch (Exception e) {
			throw e;
		}
		return p;
	}

	@Override
	public Product findProduct(int prodId) throws ProductNotFoundException{
		Product p = null;
		try {
			p = repository.findById(prodId).get();
		} catch (NoSuchElementException e) {
			throw new ProductNotFoundException("Product with id "+prodId +" does not exists");
		}
		catch (Exception e) {
			throw e;
		}
		return p;
	}

	@Override
	public List<Product> showAllProducts() {
		List<Product> products = repository.findAll();
		return products;
	}

}
