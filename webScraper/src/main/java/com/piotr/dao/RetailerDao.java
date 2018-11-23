package com.piotr.dao;

import java.util.List;

import com.piotr.model.Product;
import com.piotr.model.Retailer;

public interface RetailerDao {
	
	public void add(Retailer retailer);
	public void edit(Retailer retailer);
	public void delete(int retailerId);
	public Retailer getRetailer(int retailerId);
	public List getAllRetailers();

}
