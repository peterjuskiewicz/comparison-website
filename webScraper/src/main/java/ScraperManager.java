import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.piotr.scraper.*;

public class ScraperManager {

	String[] productsArray = {"water", "yogurth", "cereal"};

	MorrisonsScraper morrisonsScraper;
	TescoScraper tescoScraper;
	OcadoScraper ocadoScraper;
	WaitroseScraper waitroseScraper;
	
	/**
	 * Method starts scraping 4 websites:
	 * morrisons.com, tesco.com, ocado.com, waitrose.com 
	 */

	public void startScraping() throws Exception {


		try {
			morrisonsScraper.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ocadoScraper.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			waitroseScraper.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			tescoScraper.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			morrisonsScraper.join();
			
			try {
				ocadoScraper.join();
				
				try {
					waitroseScraper.join();
					
					try {
						tescoScraper.join();
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			morrisonsScraper.getProductDao().getSession().close();
		}

		

		

		
	}

	public MorrisonsScraper getMorrisonsScraper() {
		return morrisonsScraper;
	}

	public void setMorrisonsScraper(MorrisonsScraper morrisonsScraper) {
		this.morrisonsScraper = morrisonsScraper;
	}

	public TescoScraper getTescoScraper() {
		return tescoScraper;
	}

	public void setTescoScraper(TescoScraper tescoScraper) {
		this.tescoScraper = tescoScraper;
	}

	public OcadoScraper getOcadoScraper() {
		return ocadoScraper;
	}

	public void setOcadoScraper(OcadoScraper ocadoScraper) {
		this.ocadoScraper = ocadoScraper;
	}

	public WaitroseScraper getWaitroseScraper() {
		return waitroseScraper;
	}

	public void setWaitroseScraper(WaitroseScraper waitroseScraper) {
		this.waitroseScraper = waitroseScraper;
	}

}
