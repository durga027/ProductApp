package com.product.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.product.controller.ProductController;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;
import com.product.service.ProductService;
import com.product.vo.ProductErrorVO;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

	@MockBean
	ProductService productService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void addProductTest() throws Exception {
		Product p = new Product("productName1", 12.0, "description1", "category1", 2);
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter writer = mapper.writer();
		String requestString = writer.writeValueAsString(p);
		when(productService.addProduct(p)).thenReturn(p);
		mockMvc.perform(put("/products").contentType(MediaType.APPLICATION_JSON).content(requestString))
				.andExpect(status().isOk()).andExpect(new ResultMatcher() {

					@Override
					public void match(MvcResult result) throws Exception {
						Product actual = mapper.readValue(result.getResponse().getContentAsString(),
								new TypeReference<Product>() {
								});
						assertNotNull(p);
						assertTrue(actual.getProdName().equals(p.getProdName()));

					}
				}).andReturn();
	}

	@Test
	public void deleteProductTest() throws Exception {
		Product p = new Product("productName1", 12.0, "description1", "category1", 2);
		p.setProdId(101);
		when(productService.deleteProduct(101)).thenReturn(p);
		mockMvc.perform(delete("/products/101")).andExpect(status().isOk()).andExpect(new ResultMatcher() {
			@Override
			public void match(MvcResult result) throws Exception {
				Product actual = mapper.readValue(result.getResponse().getContentAsString(),
						new TypeReference<Product>() {
						});
				assertNotNull(p);
				assertTrue(actual.getProdName().equals(p.getProdName()));

			}
		}).andReturn();
	}

	@Test
	public void findProductTest() throws Exception {
		Product p = new Product("productName1", 12.0, "description1", "category1", 2);
		p.setProdId(101);
		when(productService.findProduct(101)).thenReturn(p);
		mockMvc.perform(get("/products/101")).andExpect(status().isOk()).andExpect(new ResultMatcher() {
			@Override
			public void match(MvcResult result) throws Exception {
				Product actual = mapper.readValue(result.getResponse().getContentAsString(),
						new TypeReference<Product>() {
						});
				assertNotNull(p);
				assertTrue(actual.getProdName().equals(p.getProdName()));

			}
		}).andReturn();
	}

	@Test
	public void showAllProductsTest() throws Exception {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("productName1", 12.0, "description1", "category1", 2));
		products.add(new Product("productName2", 12.0, "description2", "category2", 3));

		when(productService.showAllProducts()).thenReturn(products);
		mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(new ResultMatcher() {

			@Override
			public void match(MvcResult result) throws Exception {
				List<Product> actual = mapper.readValue(result.getResponse().getContentAsString(),
						new TypeReference<List<Product>>() {
						});
				assertEquals(actual.size(), products.size());
				assertTrue(actual.get(0).getProdName().equals(products.get(0).getProdName()));
				assertTrue(actual.get(1).getProdName().equals(products.get(1).getProdName()));
			}
		}).andReturn();
	}

	@Test
	public void findProductTestException() throws Exception {
		int prodId = 101;
		ProductNotFoundException exception = new ProductNotFoundException();
		exception.setMessage("Product with id " + prodId + " does not exists");
		when(productService.findProduct(prodId))
				.thenThrow(exception);
		mockMvc.perform(get("/products/" + prodId)).andExpect(status().isBadRequest()).andExpect(new ResultMatcher() {
			@Override
			public void match(MvcResult result) throws Exception {
				ProductErrorVO actual = mapper.readValue(result.getResponse().getContentAsString(),
						new TypeReference<ProductErrorVO>() {
						});
				assertEquals(actual.getMessage(), "Product with id " + prodId + " does not exists");

			}
		}).andReturn();
	}

}
