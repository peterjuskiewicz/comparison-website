package com.piotr.dao.impl;

import java.util.List; 


import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.piotr.dao.ProductDao;
import com.piotr.model.Product;
import com.piotr.model.Retailer;

// Single session factory manager

@Repository
public class ProductDaoImpl implements ProductDao {
	
	

	private SessionFactory session;
	
	
	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}	
	
	@Override
	public void add(Product product) {
		
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		try {
			currentSession.save(product);
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}
	
		System.out.println("Retailer added to database with ID: " + product.getId());
	}

	@Override
	public void edit(Product product) {
		
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		try {
			currentSession.update(product);
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}

		System.out.println("Retailer updated to database with ID: " + product.getId());
	}

	@Override
	public void delete(int productId) {
		
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		try {
			currentSession.delete(getProduct(productId));
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}
		
		System.out.println("Retailer updated to database with ID: " + productId);		
	}

	@Override
	public Product getProduct(int productId) {
		
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		Product product = null;
				
		try {
			product = (Product) currentSession.get(Product.class, productId);
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}
		
		System.out.println(product);
		
		return product;
	}

	@Override
	public List getAllProducts() {
		
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		List productList = null;
		
		try {
			productList = currentSession.createQuery("from product").list();
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}
		
		return productList;		
	}

}
