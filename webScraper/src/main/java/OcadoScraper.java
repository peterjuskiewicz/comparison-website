import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class OcadoScraper implements ScrapesWebsites {
	
	private Element prod;
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
        	
        	// extracts price
        	
        	price = product.select(".fop-price").text();
        	
        	//extracts product weight
        	
        	weight = product.select(".fop-catch-weight").text();
        	
        	// extracts product name 
        	
        	productName = product.select("h4.fop-title").attr("title");
        	
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
        
        
        
        
        
        
        
        
        
        
        
        
//        //Work through the products
//        for(int i=0; i<prods.size(); ++i){
//            
//            //Get the product description
//            Elements description = prods.get(i).select(".fop-description");
//            
//            //Get the product price
//            Elements price1 = prods.get(i).select(".fop-price");
//            Elements finalPrice = price1.select(".fop-lpp");
//            
//            //Get the weight
//            Elements weight = prods.get(i).select(".fop-catch-weight");
//            
//            //Output the data that we have downloaded
////            System.out.println("DESCRIPTION: " + description.text() + "; WEIGHT: " + 
////                    weight.text() + "; PRICE: " + finalPrice.text());
//        }
    }
}
