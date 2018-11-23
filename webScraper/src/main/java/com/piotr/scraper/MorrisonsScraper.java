package com.piotr.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MorrisonsScraper extends Thread implements ScrapesWebsites {
	
	private Element prod;
	private String productName;
	private String url;
	private String imgUrl;
	private String price;
	private String weight;
	private String brandName;
    
    /** Scrapes data from the Morrisons website */
    public void scrape(String query) throws Exception {
    	
        //Download HTML document from website
        Document doc = Jsoup.connect("https://groceries.morrisons.com/search?entry=" + query).get();
        
        
        //Get all of the products on the page
        Elements prods = doc.select("ul.fops");

        
        Elements product = prods.get(0).select("li.fops-item .fop-item");
        
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
        		
        		
        		
        		System.out.println(i + " " + brandName);
        	}
      	
        }

    }

}
