package com.piotr.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;

import com.piotr.dao.RetailerProductDao;
import com.piotr.model.Retailer;
import com.piotr.model.RetailerProduct;

@Repository
public class RetailerProductDaoImpl implements RetailerProductDao {

	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(RetailerProduct retailer) {
		session.getCurrentSession().save(retailer);

	}

	@Override
	public void edit(RetailerProduct retailer) {
		session.getCurrentSession().update(retailer);

	}

	@Override
	public void delete(int retailerId) {
		session.getCurrentSession().delete(getRetailer(retailerId));

	}

	@Override
	public RetailerProduct getRetailer(int retailerId) {
		return (RetailerProduct)session.getCurrentSession().get(RetailerProduct.class, retailerId);
	}

	@Override
	public List getAllRetailers() {
		return session.getCurrentSession().createQuery("from retailer_product").list();
	}

}
