package com.piotr.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class OcadoScraper implements ScrapesWebsites {
	

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
        Document doc = Jsoup.connect("https://www.ocado.com/search?entry=" + query).get();
        Elements products = doc.select("ul.fops.fops-regular").get(0).select("li.fops-item .fop-item");
        // List<Product> __ = new ArrayList<>();
        
        
        for (Element product : products) {

        	if(!product.hasAttr("data-sku")) {
        		continue;
        	}

//       	System.out.println(product);
        	
        	// extracts image url        	
        	
        	imgUrl = product.select("img.fop-img").attr("abs:src");
        	
        	// extracts url
        	
        	url = product.select("a").get(0).attr("abs:href");
        	
        	System.out.println(url);
        	
        	// extracts price
        	
        	price = product.select(".fop-price").text();
        	
        	System.out.println(price);
        	
        	//extracts product weight
        	
        	weight = product.select(".fop-catch-weight").text();
        	
        	System.out.println(weight);
        	
        	// extracts product name 
        	
        	productName = product.select("h4.fop-title").attr("title");
        	
        	System.out.println(productName);
        	
        	// extracts brand name 
    		String[] strArr = productName.split(" ");
    		brandName = strArr[0];
        	
        	
        	
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
