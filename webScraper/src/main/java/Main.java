/** Simple Hibernate example */

public class Main {

    public static void main(String [] args) throws Exception {    	
    	
    	ScrapesWebsites scraper = new OcadoScraper();
    	
     	
    	scraper.scrape("water");
    	
    	
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
