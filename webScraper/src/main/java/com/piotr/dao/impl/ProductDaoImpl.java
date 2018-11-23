package com.piotr.dao.impl;

import java.util.List; 


import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.SessionFactory;
import com.piotr.dao.ProductDao;
import com.piotr.model.Product;

// Single session factory manager

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private SessionFactory session;

	@Override
	public void add(Product product) {
		session.getCurrentSession().save(product);

	}

	@Override
	public void edit(Product product) {
		session.getCurrentSession().update(product);

	}

	@Override
	public void delete(int productId) {
		session.getCurrentSession().delete(getProduct(productId));

	}

	@Override
	public Product getProduct(int productId) {
		return (Product)session.getCurrentSession().get(Product.class, productId);
	}

	@Override
	public List getAllProducts() {

		return session.getCurrentSession().createQuery("from product").list();
	}

}
