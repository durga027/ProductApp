package com.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.product.service.ProductService;
import com.product.service.ProductServiceImpl;

@Configuration
public class ProductConfig {

	
	@Bean
	public ProductService productService() {
		return new ProductServiceImpl();
	}
}
