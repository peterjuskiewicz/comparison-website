import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WaitroseScraper {
	
	/** Constructor */
    WaitroseScraper(){
        try{
            scrapeCornflakes("cornflakes");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    /** Scrapes data from the Ocado website */
    void scrapeCornflakes(String name) throws Exception{
        //Name of item that we want to scrape
        String itemName = name;
        
        //Download HTML document from website
        Document doc = Jsoup.connect("https://www.waitrose.com/ecom/shop/search?&searchTerm=" + itemName).get();
        
        
        
        //Get all of the products on the page
        Elements prods = doc.select(".flexGrid___1DH6K");
        
        System.out.println(prods);
//        Work through the products
        for(int i=0; i<prods.size(); ++i){
            
            //Get the product description
            Elements description = prods.get(i).select(".name___2sgmL");
            
            
            //Get the product price
            Elements price1 = prods.get(i).select(".prices___1JkR4");
            Elements finalPrice = price1.select("span");

            
//            Get the weight
            Elements weight = prods.get(i).select(".sizeMessage___33nJ_");

//            
//            Output the data that we have downloaded
            System.out.println("DESCRIPTION: " + description.text() + "; WEIGHT: " + 
                    weight.text() + "; PRICE: " + price1.text());
        }
    }

}
