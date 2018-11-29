package com.piotr.scraper;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.piotr.dao.impl.ProductDaoImpl;
import com.piotr.dao.impl.RetailerDaoImpl;
import com.piotr.dao.impl.RetailerProductDaoImpl;
import com.piotr.model.Product;
import com.piotr.model.Retailer;
import com.piotr.model.RetailerProduct;


public class OcadoScraper extends Thread implements ScrapesWebsites {

	private List<String> productList;	

	private ProductDaoImpl productDao;
	private RetailerDaoImpl retailerDao;
	private RetailerProductDaoImpl retailerProductDao;

	public OcadoScraper(List<String> productList) {

		this.productList = productList;
	}

	public OcadoScraper() {
		this.productList = new ArrayList<>();
		productList.add("water");
	}

	@Override
	public void run() {

		try {
			scrape("test");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * {@inheritDoc}
	 */
	public void scrape(String text) throws Exception {
		
		for(String query : productList) {
			
			Document doc = Jsoup.connect("https://www.ocado.com/search?entry=" + query).get();

			Elements products = doc.select("ul.fops.fops-regular").get(0).select("li.fops-item .fop-item");

			getResults(products);
			
		}
	}

	private void getResults(Elements products) {

		String productName;
		String url;
		String imgUrl;
		String price;
		String weight;
		String brandName;

		for (Element product : products) {

			if(!product.hasAttr("data-sku")) {
				continue;
			}

			//       	System.out.println(product);

			// extracts image url        	

			imgUrl = product.select("img.fop-img").attr("abs:src");

			// extracts url

			url = product.select("a").get(0).attr("abs:href");

			// extracts price

			price = product.select(".fop-price").text();

			//extracts product weight

			weight = product.select(".fop-catch-weight").text();

			// extracts product name 

			productName = product.select("h4.fop-title").attr("title");



			// extracts brand name 
			String[] strArr = productName.split(" ");
			brandName = strArr[0];



			System.out.println("Ocado Weight: " + weight + " Brand: " + brandName + " Product: " + productName +
					" Price: " + price + " url " + url);

			modifyData(brandName, productName, weight, price, url);

		}

	}

	private void modifyData(String brandName, String productName, String weight, String price, String url) {


		Product product = new Product();
		Retailer retailer = new Retailer();
		RetailerProduct retailerProduct = new RetailerProduct();

		retailer.setName("Ocado");

		product.setBrand(brandName);
		product.setName(productName);
		product.setSize(weight);

		//		retailerProduct.setProductId(product);
		//		retailerProduct.setRetailerId(retailer);

		retailerProduct.setPrice(price);
		retailerProduct.setUrl(url);
		retailerProduct.setProduct(product);
		retailerProduct.setRetailer(retailer);

		productDao.add(product);
		retailerDao.add(retailer);
		retailerProductDao.add(retailerProduct);	

	}

	public ProductDaoImpl getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

	public RetailerDaoImpl getRetailerDao() {
		return retailerDao;
	}

	public void setRetailerDao(RetailerDaoImpl retailerDao) {
		this.retailerDao = retailerDao;
	}

	public RetailerProductDaoImpl getRetailerProductDao() {
		return retailerProductDao;
	}

	public void setRetailerProductDao(RetailerProductDaoImpl retailerProductDao) {
		this.retailerProductDao = retailerProductDao;
	}


}

