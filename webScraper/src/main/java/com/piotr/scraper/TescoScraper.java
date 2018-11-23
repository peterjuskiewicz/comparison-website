package com.piotr.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TescoScraper implements ScrapesWebsites {

	private String productName;
	private String url;
	private String imgUrl;
	private String price;
	private String weight;
	private String brandName;


	/**
	 * {@inheritDoc}
	 */
	public void scrape(String query) throws Exception {
		Document doc = Jsoup.connect("https://www.tesco.com/groceries/en-GB/search?query=" + query).get();

		// List<Product> __ = new ArrayList<>();
		Elements products = doc.select("ul.product-list").get(0).select("li.product-list--list-item");

		System.out.println(products.size());
			

		for (Element product : products) {

//			if(!product.hasAttr("data-sku")) {
//				continue;
//			}
//
			// extracts image url        	

			imgUrl = product.select("img").get(0).attr("src");

			System.out.println(imgUrl);
			// extracts url

			url = product.select("a").get(0).attr("abs:href");
			
			System.out.println(url);

			// extracts price

			price = product.select("span.value").get(0).text();
			
			System.out.println(price);

			// extracts product name 

			productName = product.select("a").get(1).text();
			
			System.out.println(productName);

			// extracts brand name 
			String[] strArr = productName.split(" ");
			brandName = strArr[0];
			
			System.out.println(brandName);

			//extracts product weight

			weight = strArr[strArr.length - 1];


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
