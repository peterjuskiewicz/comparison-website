package com.piotr.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;

import com.piotr.dao.RetailerDao;
import com.piotr.model.Product;
import com.piotr.model.Retailer;

@Repository
public class RetailerDaoImpl implements RetailerDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void add(Retailer retailer) {
		session.getCurrentSession().save(retailer);
	}

	@Override
	public void edit(Retailer retailer) {
		session.getCurrentSession().update(retailer);

	}

	@Override
	public void delete(int retailerId) {
		session.getCurrentSession().delete(getRetailer(retailerId));

	}

	@Override
	public Retailer getRetailer(int retailerId) {
		return (Retailer)session.getCurrentSession().get(Retailer.class, retailerId);
	}

	@Override
	public List getAllRetailers() {
		return session.getCurrentSession().createQuery("from retailer").list();
	}

}
