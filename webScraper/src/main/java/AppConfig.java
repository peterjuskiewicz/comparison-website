import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.piotr.dao.impl.*;
import com.piotr.scraper.*;

@Configuration
public class AppConfig {
    SessionFactory sessionFactory;
    
    @Bean
    public ScraperManager scraperManager(){
        ScraperManager manager = new ScraperManager();
        manager.setMorrisonsScraper(scraper1());
        manager.setOcadoScraper(scraper2());
        manager.setTescoScraper(scraper3());
        manager.setWaitroseScraper(scraper4());
        return manager;
    }

    
    @Bean
    public MorrisonsScraper scraper1(){
        MorrisonsScraper scraper = new MorrisonsScraper();
        scraper.setProductDao(productDao());
        scraper.setRetailerDao(retailerDao());
        scraper.setRetailerProductDao(retailerProductDao());
        return (MorrisonsScraper) scraper;
    }
    
    @Bean
    public OcadoScraper scraper2(){
    	OcadoScraper scraper = new OcadoScraper();
        scraper.setProductDao(productDao());
        scraper.setRetailerDao(retailerDao());
        scraper.setRetailerProductDao(retailerProductDao());
        return (OcadoScraper) scraper;
    }
    
    @Bean
    public TescoScraper scraper3(){
    	TescoScraper scraper = new TescoScraper();
        scraper.setProductDao(productDao());
        scraper.setRetailerDao(retailerDao());
        scraper.setRetailerProductDao(retailerProductDao());
        return (TescoScraper) scraper;
    }
    
    @Bean
    public WaitroseScraper scraper4(){
    	WaitroseScraper scraper = new WaitroseScraper();
        scraper.setProductDao(productDao());
        scraper.setRetailerDao(retailerDao());
        scraper.setRetailerProductDao(retailerProductDao());
        return (WaitroseScraper) scraper;
    }
    
    
    
    
    
    @Bean
    public ProductDaoImpl productDao(){
        ProductDaoImpl hatDao = new ProductDaoImpl();
        hatDao.setSession(sessionFactory());
        return hatDao;
    }
    
    @Bean
    public RetailerDaoImpl retailerDao(){
        RetailerDaoImpl hatDao = new RetailerDaoImpl();
        hatDao.setSession(sessionFactory());
        return hatDao;
    }
    
    @Bean
    public RetailerProductDaoImpl retailerProductDao(){
        RetailerProductDaoImpl hatDao = new RetailerProductDaoImpl();
        hatDao.setSession(sessionFactory());
        return hatDao;
    }
    
    @Bean
    public SessionFactory sessionFactory(){
        if(sessionFactory == null){//Build sessionFatory once only
            try {
                //Create a builder for the standard service registry
                StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

                //Load configuration from hibernate configuration file.
                //Here we are using a configuration file that specifies Java annotations.
                standardServiceRegistryBuilder.configure("hibernate.cfg.xml"); 

                //Create the registry that will be used to build the session factory
                StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
                try {
                    //Create the session factory - this is the goal of the init method.
                    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
                }
                catch (Exception e) {
                        /* The registry would be destroyed by the SessionFactory, 
                            but we had trouble building the SessionFactory, so destroy it manually */
                        System.err.println("Session Factory build failed.");
                        e.printStackTrace();
                        StandardServiceRegistryBuilder.destroy( registry );
                }
                //Ouput result
                System.out.println("Session factory built.");
            }
            catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("SessionFactory creation failed." + ex);
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }
}