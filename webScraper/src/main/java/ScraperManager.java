import java.util.List;
import java.util.Scanner;

import com.piotr.scraper.*;

public class ScraperManager {
	
	String[] productsArray = {"water", "yogurth", "cereal"};
	
	TescoScraper scraper;
	MorrisonsScraper morrisonsScraper;
	TescoScraper tescoScraper;
	OcadoScraper ocadoScraper;
	WaitroseScraper waitroseScraper;
	
	public void startScraping() throws Exception {
		
		try {
			scraper.start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scraper.getProductDao().getSession().close();
			scraper.join();
		}	
	}

	public TescoScraper getScraper() {
		return scraper;
	}

	public void setScraper(TescoScraper scraper) {
		this.scraper = scraper;
	}
	
	

	
	
	
	
	
	
	
//	//Starts up the scrapers
//	public void startScraping() throws Exception {
//		
//		for(String product : productsArray) {
//			
//			for(ScrapesWebsites webScraper : scraperList) {
//				webScraper.scrape(product);
//			}
//			
//		}
//		
//		
//		for(ScrapesWebsites webScraper: scraperList) {
//			try {
////				webScraper.stopScraping();
////				webScraper.join();
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
//
//	
	

}
