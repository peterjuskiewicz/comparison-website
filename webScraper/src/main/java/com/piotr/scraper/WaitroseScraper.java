package com.piotr.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.piotr.dao.impl.ProductDaoImpl;
import com.piotr.dao.impl.RetailerDaoImpl;
import com.piotr.dao.impl.RetailerProductDaoImpl;

public class WaitroseScraper extends Thread implements ScrapesWebsites{
	
	private String productName;
	private String url;
	private String imgUrl;
	private String price;
	private String weight;
	private String brandName;
	
	private ProductDaoImpl productDao;
	private RetailerDaoImpl retailerDao;
	private RetailerProductDaoImpl retailerProductDao;


	/* (non-Javadoc)
	 * @see com.piotr.scraper.TestInterface#scrape(java.lang.String)
	 */
	@Override
	public void scrape(String query) throws Exception {
		 Document doc = Jsoup.connect("https://www.waitrose.com/ecom/shop/search?&searchTerm=" + query).get();

		// List<Product> __ = new ArrayList<>();
		Elements products = doc.select(".flexGrid___1DH6K").get(0).select("article.productPod___2BPU9");

		System.out.println(products.size());
			

		for (Element product : products) {

//			if(!product.hasAttr("data-sku")) {
//				continue;
//			}
//
			// extracts image url        
			
//			System.out.println(product);

//			imgUrl = product.select("picture.podImage___1ajLe").select("img").attr("src");
//
//			System.out.println(imgUrl);
//			// extracts url
//
			url = product.select("div.gtm___3QuoL").select("a").attr("abs:href");
			
//
//			// extracts price
//
			price = product.select("div.prices___1JkR4").select("span > span").text();
//			
//
			// extracts product name 

			productName = product.select("span.name___2sgmL").text();
//			
//
//			// extracts brand name 
			String[] strArr = productName.split(" ");
			brandName = strArr[0];
//			
//
//			//extracts product weight
//
			weight = product.select("span.size___2HSwr").get(0).text();
//
////
			System.out.println("Waitrose Weight: " + weight + " Brand: " + brandName + " Product: " + productName +
					" Price: " + price + " url " + url);

			// Get data here.
			// Validate - Hibernate entities have validation using annotations.
			// Create entity
			// __.add(p)

		}

		// You can either return the list.
		// return __

		// If async, you'd need a persistor dependency or you can save it directly in here

	}
      
}
