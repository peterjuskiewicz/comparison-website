package com.piotr.scraper;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
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

public class MorrisonsScraper extends Thread implements ScrapesWebsites {


	private List<String> productList;

	private ProductDaoImpl productDao;
	private RetailerDaoImpl retailerDao;
	private RetailerProductDaoImpl retailerProductDao;

	public MorrisonsScraper(List<String> productList) {

		this.productList = productList;

	}

	public MorrisonsScraper() {
		this.productList = new ArrayList<>();
		productList.add("water");
		productList.add("yogurth");
		productList.add("cereal");
	}


	@Override
	public void run() {

		try {
			scrape("test");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** Scrapes data from the Morrisons website */
	public void scrape(String text) throws Exception {

		for(String query : productList) {


			//Download HTML document from website
			Document doc = Jsoup.connect("https://groceries.morrisons.com/search?entry=" + query).get();


			//Get all of the products on the page
			Elements prods = doc.select("ul.fops");


			Elements product = prods.get(0).select("li.fops-item .fop-item");

			getResults(product);

		}



	}

	private void getResults(Elements product) {

		Element prod;
		String productName;
		String url;
		String imgUrl;
		String price;
		String weight;
		String brandName;


		for (int i = 0; i < product.size(); i++) {



			Element currentProduct = product.get(i);

			if(currentProduct.hasAttr("data-sku")) {
				prod = currentProduct;

				// extracts url
				url = prod.select("a").get(0).attr("abs:href");

				//extracts image url
				imgUrl = prod.select("img").attr("abs:src");

				//extracts product price
				//clean the price and convert to float
				price = prod.select(".fop-price").text();

				//extracts product weight
				weight = prod.select(".fop-catch-weight").text();

				//extracts product name
				productName = prod.select(".fop-title").attr("title");

				// extracts brand name 
				String[] strArr = productName.split(" ");
				brandName = strArr[0];

				modifyData(brandName, productName, weight, price, url);



				System.out.println("Morrisons Weight: " + weight + " Brand: " + brandName + " Product: " + productName +
						" Price: " + price + " url " + url);
			}

		}



	}

	private void modifyData(String brandName, String productName, String weight, String price, String url) {


		Product product = new Product();
		Retailer retailer = new Retailer();
		RetailerProduct retailerProduct = new RetailerProduct();

		retailer.setName("Morrisons");

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


	public void setProductDao(ProductDaoImpl productDao) {
		this.productDao = productDao;
	}

	public ProductDaoImpl getProductDao() {
		return this.productDao;
	}

	public void setRetailerDao(RetailerDaoImpl retailerDao) {
		this.retailerDao = retailerDao;
	}

	public void setRetailerProductDao(RetailerProductDaoImpl retailerProductDao) {
		this.retailerProductDao = retailerProductDao;
	}
	
	

	public RetailerDaoImpl getRetailerDao() {
		return retailerDao;
	}

	public RetailerProductDaoImpl getRetailerProductDao() {
		return retailerProductDao;
	}

	public List<String> getProductList() {
		return productList;
	}
	
	

}
