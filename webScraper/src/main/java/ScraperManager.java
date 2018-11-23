import java.util.List;
import java.util.Scanner;

import com.piotr.scraper.ScrapesWebsites;

public class ScraperManager {
	
	String[] productsArray = {"water", "yogurth", "cereal"};
	
	List<ScrapesWebsites> scraperList;
	
	//Starts up the scrapers
	public void startScraping() throws Exception {
		
		for(String product : productsArray) {
			
			for(ScrapesWebsites webScraper : scraperList) {
				webScraper.scrape(product);
			}
			
		}
		
		
		for(ScrapesWebsites webScraper: scraperList) {
			try {
//				webScraper.stopScraping();
//				webScraper.join();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	
	

}
