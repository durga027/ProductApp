package com.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "prod_id")
	 private int prodId;
	 
	 @Column(name = "prod_name")
	 private String prodName;
	 
	 @Column(name = "price")
	 private double price;
	 
	 @Column(name="description")
	 private String description;
	 
	 @Column(name = "category")
	 private String category;
	 
	 @Column(name = "qoh")
	 private int qoh;

	 
	public Product() {
	}
	public Product(String prodName, double price, String description, String category, int qoh) {
		this.prodName = prodName;
		this.price = price;
		this.description = description;
		this.category = category;
		this.qoh = qoh;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQoh() {
		return qoh;
	}
	public void setQoh(int qoh) {
		this.qoh = qoh;
	}
		 
}
