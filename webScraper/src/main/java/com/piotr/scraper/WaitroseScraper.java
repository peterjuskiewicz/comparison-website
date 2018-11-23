package com.piotr.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WaitroseScraper extends Thread implements ScrapesWebsites{
	
	private String productName;
	private String url;
	private String imgUrl;
	private String price;
	private String weight;
	private String brandName;


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
			
			System.out.println(url);
//
//			// extracts price
//
			price = product.select("div.prices___1JkR4").select("span > span").text();
//			
			System.out.println(price);
//
			// extracts product name 

			productName = product.select("span.name___2sgmL").text();
//			
			System.out.println(productName);
//
//			// extracts brand name 
			String[] strArr = productName.split(" ");
			brandName = strArr[0];
//			
			System.out.println(brandName);
//
//			//extracts product weight
//
			weight = product.select("span.size___2HSwr").get(0).text();
//
////
			System.out.println(weight);

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
