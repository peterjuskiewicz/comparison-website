import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.piotr.scraper.MorrisonsScraper;
import com.piotr.scraper.OcadoScraper;
import com.piotr.scraper.ScrapesWebsites;
import com.piotr.scraper.TescoScraper;
import com.piotr.scraper.WaitroseScraper;

/** Simple Hibernate example */

public class Main {

    public static void main(String [] args) throws Exception {     	
  	
    	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	ScraperManager scraper = (ScraperManager) context.getBean("scraperManager");
    	
    	scraper.startScraping();

    }

}
