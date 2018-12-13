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

public class TescoScraper extends Thread implements ScrapesWebsites {

	private List<String> productList;

	private ProductDaoImpl productDao;
	private RetailerDaoImpl retailerDao;
	private RetailerProductDaoImpl retailerProductDao;


	public TescoScraper(List<String> productList) {
		this.productList = productList;

	}

	public TescoScraper() {
		this.productList = new ArrayList<>();
		this.productList.add("water");
		productList.add("yogurth");
		productList.add("cereal");
	}


	@Override
	public void run() {

		try {
			scrape("test");
		} catch(Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * {@inheritDoc}
	 */
	public void scrape(String text) throws Exception {

		for(String query : productList) {


			Document doc = Jsoup.connect("https://www.tesco.com/groceries/en-GB/search?query=" + query).get();

			Elements products = doc.select("ul.product-list").get(0).select("li.product-list--list-item");

			getResults(products);

		}

	}


	public void getResults(Elements products) {


		String productName;
		String url;
		String imgUrl;
		String price;
		String weight;
		String brandName;

		for (Element product : products) {

			// extracts image url        	

			imgUrl = product.select("img").get(0).attr("src");

			// extracts url

			url = product.select("a").get(0).attr("abs:href");

			// extracts price

			price = product.select("span.value").text();

			// extracts product name 

			productName = product.select("a").get(1).text();

			// extracts brand name 
			String[] strArr = productName.split(" ");
			brandName = strArr[0];

			//extracts product weight

			weight = strArr[strArr.length - 1];

			modifyData(brandName, productName, weight, price, url);


			System.out.println("Tesco Weight: " + weight + " Brand: " + brandName + " Product: " + productName +
					" Price: " + price + " url " + url);

		}


	}

	private void modifyData(String brandName, String productName, String weight, String price, String url) {


		Product product = new Product();
		Retailer retailer = new Retailer();
		RetailerProduct retailerProduct = new RetailerProduct();

		retailer.setName("Tesco");

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
