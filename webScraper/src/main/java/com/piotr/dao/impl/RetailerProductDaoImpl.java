package com.piotr.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.piotr.dao.RetailerProductDao;
import com.piotr.model.Retailer;
import com.piotr.model.RetailerProduct;

@Repository
public class RetailerProductDaoImpl implements RetailerProductDao {

	
	
	private SessionFactory session;
	

	public SessionFactory getSession() {
		return session;
	}

	public void setSession(SessionFactory session) {
		this.session = session;
	}

	
	
	@Override
	public void add(RetailerProduct retailer) {
		
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		try {		
			
			currentSession.save(retailer);
			currentSession.getTransaction().commit();
			
			System.out.println("Retailer added to database with ID: " + retailer.getId());
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}
		
	}

	@Override
	public void edit(RetailerProduct retailer) {
			
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		try {
			
			currentSession.update(retailer);
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}

		System.out.println("Retailer updated to database with ID: " + retailer.getId());

	}

	@Override
	public void delete(int retailerId) {
		
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		try {
			
			currentSession.delete(getRetailer(retailerId));
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}
		
		System.out.println("Retailer updated to database with ID: " + retailerId);
	}

	@Override
	public RetailerProduct getRetailer(int retailerId) {
			
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		RetailerProduct retailerProduct = null;
		
		try {
			retailerProduct = (RetailerProduct) currentSession.get(RetailerProduct.class, retailerId);
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}

		System.out.println(retailerProduct);
		
		return retailerProduct;
	}

	@Override
	public List getAllRetailers() {
		
		Session currentSession = this.session.getCurrentSession();
		currentSession.beginTransaction();
		
		List retailerProductList = null;
		
		try {
			retailerProductList = currentSession.createQuery("from retailer_product").list();
			currentSession.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			currentSession.close();
		}

		return retailerProductList;
	}

}
