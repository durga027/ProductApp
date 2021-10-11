package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import com.product.service.ProductServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@PutMapping
	Product addProduct(@RequestBody Product product) {
		productService.addProduct(product);
		return product;
	}

	@DeleteMapping("/{prodId}")
	Product deleteProduct(@PathVariable(value = "prodId") int prodId) throws ProductNotFoundException {
		return productService.deleteProduct(prodId);
	}

	@GetMapping("/{prodId}")
	Product findProduct(@PathVariable(value = "prodId") int prodId) throws ProductNotFoundException {
		return productService.findProduct(prodId);
	}

	@GetMapping
	List<Product> showAllProducts() {
		return productService.showAllProducts();
	}
	@PostMapping("/{prodId}")
	Product updateProduct(@PathVariable(value = "prodId") int prodId, @RequestBody Product product)throws ProductNotFoundException{
		return productService.updateProduct(prodId,product);
	}
}
