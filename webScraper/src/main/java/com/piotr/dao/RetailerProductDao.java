package com.piotr.dao;

import java.util.List;

import com.piotr.model.RetailerProduct;

public interface RetailerProductDao {
	
	public void add(RetailerProduct retailer);
	public void edit(RetailerProduct retailer);
	public void delete(int retailerId);
	public RetailerProduct getRetailer(int retailerId);
	public List getAllRetailers();

}
