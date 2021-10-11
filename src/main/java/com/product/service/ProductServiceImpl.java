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

	@Override
	public Product updateProduct(int prodId, Product product) throws ProductNotFoundException{
		Product p = findProduct(prodId);
		p.setProdName(product.getProdName());
		p.setPrice(product.getPrice());
		p.setCategory(product.getCategory());
		p.setDescription(product.getDescription());
		p.setQoh(product.getQoh());
		repository.save(p);
		return p;
	}

}
