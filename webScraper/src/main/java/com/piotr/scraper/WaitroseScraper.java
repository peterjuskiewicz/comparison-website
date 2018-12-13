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

public class WaitroseScraper extends Thread implements ScrapesWebsites{

	private List<String> productList;

	private ProductDaoImpl productDao;
	private RetailerDaoImpl retailerDao;
	private RetailerProductDaoImpl retailerProductDao;

	public WaitroseScraper(List<String> productList) {

		this.productList = productList;
	}


	public WaitroseScraper() {

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


	/* (non-Javadoc)
	 * @see com.piotr.scraper.TestInterface#scrape(java.lang.String)
	 */
	public void scrape(String text) throws Exception {

		for(String query : productList) {

			Document doc = Jsoup.connect("https://www.waitrose.com/ecom/shop/search?&searchTerm=" + query).get();

			Elements products = doc.select(".flexGrid___1DH6K").get(0).select("article.productPod___2BPU9");
			
			getResults(products);

		}



		// You can either return the list.
		// return __

		// If async, you'd need a persistor dependency or you can save it directly in here

	}


	public void getResults(Elements products) {

		String productName;
		String url;
		String imgUrl;
		String price;
		String weight;
		String brandName;

		for (Element product : products) {

			// imgUrl = product.select("picture.podImage___1ajLe").select("img").attr("src");
			// System.out.println(imgUrl);
			// extracts url

			url = product.select("div.gtm___3QuoL").select("a").attr("abs:href");


			// extracts price
			price = product.select("div.prices___1JkR4").select("span > span").text();


			// extracts product name 

			productName = product.select("span.name___2sgmL").text();


			// extracts brand name 
			String[] strArr = productName.split(" ");
			brandName = strArr[0];


			// extracts product weight

			weight = product.select("span.size___2HSwr").get(0).text();

			modifyData(brandName, productName, weight, price, url);

			System.out.println("Waitrose Weight: " + weight + " Brand: " + brandName + " Product: " + productName +
					" Price: " + price + " url " + url);


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
