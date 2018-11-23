import com.piotr.scraper.OcadoScraper;
import com.piotr.scraper.ScrapesWebsites;
import com.piotr.scraper.WaitroseScraper;

/** Simple Hibernate example */

public class Main {

    public static void main(String [] args) throws Exception {    	
    	
    	ScrapesWebsites scraper = new WaitroseScraper();
    	ScrapesWebsites scraper1 = new OcadoScraper();
//     	
    	scraper.scrape("milk");
    	scraper1.scrape("milk");
    	
    	
//    	
//    	for (String product : new String[] {
//    			"water", "cereal", "yogurth"
//    	}) {
//    		
//    		scraper.scrape(product);
//        	
//    	}
        //Create a new instance of the HibernateExample class
    	
    	
    	
//        HibernateSession hibernateSession = new HibernateSession();
        
        //Set up the SessionFactory
//        hibernateSession.init();
        
        //Example operations
        
//        hibernateSession.addCereal();

       // hibernateXmlExample.updateCereal();//Updates data
        //hibernateXmlExample.searchCereals();//Search for data
        //hibernateXmlExample.deleteCerealSafe();//Delete data
        
        //Shut down Hibernate
//        hibernateSession.shutDown();
    }

}
