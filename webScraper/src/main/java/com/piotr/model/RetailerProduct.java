package com.piotr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="retailer_product")
public class RetailerProduct {
	
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName="id")
	private Product productId;
	
	
	@ManyToOne
	@JoinColumn(name="retailer_id", referencedColumnName="id")
	private Retailer retailerId;
	
	
	@Column
	private float price;
	
	@Column
	private String url;

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public Retailer getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Retailer retailerId) {
		this.retailerId = retailerId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getId() {
		return id;
	}
	
	
	

}
