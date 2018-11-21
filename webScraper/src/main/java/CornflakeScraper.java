
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/** Example code to illustrate web scraping with JSoup */
public class CornflakeScraper {
    
    
    /** Constructor */
    CornflakeScraper(){
        try{
            scrapeCornflakes();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    /** Scrapes cornflakes data from the Ocado website */
    void scrapeCornflakes() throws Exception{
        //Name of item that we want to scrape
        String itemName = "cornflakes";
        
        //Download HTML document from website
        Document doc = Jsoup.connect("https://www.ocado.com/search?entry=" + itemName).get();
        
        //Get all of the products on the page
        Elements prods = doc.select(".fops-item");
        
        //Work through the products
        for(int i=0; i<prods.size(); ++i){
            
            //Get the product description
            Elements description = prods.get(i).select(".fop-description");
            
            //Get the product price
            Elements price1 = prods.get(i).select(".fop-price");
            Elements finalPrice = price1.select(".fop-lpp");
            
            //Get the weight
            Elements weight = prods.get(i).select(".fop-catch-weight");
            
            //Output the data that we have downloaded
            System.out.println("DESCRIPTION: " + description.text() + "; WEIGHT: " + 
                    weight.text() + "; PRICE: " + finalPrice.text());
        }
    }
}
