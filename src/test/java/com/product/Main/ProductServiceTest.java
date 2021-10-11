package com.product.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;
import com.product.repository.ProductRepository;
import com.product.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	ProductRepository repository;
	
	@Test
	void addProductServiceTest() {
		Product p = new Product("productName1", 12.0, "description1", "category1", 2);
		when(repository.save(p)).thenReturn(p);
		Product actual = productService.addProduct(p);
		assertNotNull(actual);
		assertEquals(actual.getProdName(), p.getProdName());
		verify(repository).save(p);
		
	}
	@Test
	void deleteServiceProduct() throws ProductNotFoundException {
		int productId=101;
		Product p = new Product("productName1", 12.0, "description1", "category1", 2);
		p.setProdId(productId);
		when(repository.findById(productId)).thenReturn(Optional.of(p));
		doNothing().when(repository).delete(p);
		Product actual = productService.deleteProduct(101);
		assertNotNull(actual);
		assertEquals(actual.getProdName(), p.getProdName());
		verify(repository).delete(p);
	}
	@Test
	void findProductServiceTest() throws ProductNotFoundException{
		int productId=101;
		Product p = new Product("productName1", 12.0, "description1", "category1", 2);
		p.setProdId(productId);
		when(repository.findById(productId)).thenReturn(Optional.of(p));
		Product actual = productService.findProduct(productId);
		assertNotNull(actual);
		assertEquals(actual.getProdName(), p.getProdName());
		verify(repository).findById(productId);
	}
	@Test
	void showAllProductsServiceTest() {
		List<Product> products =new ArrayList<>();
		Product p1 = new Product("productName1", 12.0, "description1", "category1", 2);
		Product p2 = new Product("productName1", 12.0, "description1", "category1", 2);
        products.add(p1);
        products.add(p2);
        when(repository.findAll()).thenReturn(products);
        List<Product> actualProducts = productService.showAllProducts();
        assertEquals(actualProducts.size(), products.size());
        verify(repository).findAll();
	}
	@Test
	void updateProductServiceTest() throws ProductNotFoundException{
		int pid = 10;
		Product p1 = new Product("productName1", 12.0, "description1", "category1", 2);
		Product p2 = new Product("productName2", 14.0, "description2", "category2", 1);
		p1.setProdId(pid);
		p2.setProdId(pid);
		Optional<Product> product = Optional.of(p1);
		when(repository.findById(pid)).thenReturn(product);
		Product updatedProduct = productService.updateProduct(pid, p2);
		assertEquals(updatedProduct.getProdId(), p2.getProdId());
		assertEquals(updatedProduct.getProdName(), p2.getProdName());
		assertEquals(updatedProduct.getPrice(), p2.getPrice());
		assertEquals(updatedProduct.getDescription(), p2.getDescription());
		assertEquals(updatedProduct.getCategory(), p2.getCategory());
		assertEquals(updatedProduct.getQoh(), p2.getQoh());
		verify(repository).save(updatedProduct);
	}
	@Test
	void deleteServiceProductException() throws ProductNotFoundException {
		int productId=101;
		Product p = new Product("productName1", 12.0, "description1", "category1", 2);
		p.setProdId(productId);
		ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
			productService.deleteProduct(productId);
	    });
		assertEquals(exception.getMessage(), "Product with id "+productId +" does not exists");
	}
	@Test
	void findProductServiceTestException() throws ProductNotFoundException{
		int productId=101;
		Product p = new Product("productName1", 12.0, "description1", "category1", 2);
		p.setProdId(productId);
		ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
			productService.findProduct(productId);
	    });
		assertEquals(exception.getMessage(), "Product with id "+productId +" does not exists");
	}
	
}
