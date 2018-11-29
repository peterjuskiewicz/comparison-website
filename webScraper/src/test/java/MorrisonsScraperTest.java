import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.piotr.dao.ProductDao;
import com.piotr.dao.impl.ProductDaoImpl;
import com.piotr.dao.impl.RetailerDaoImpl;
import com.piotr.dao.impl.RetailerProductDaoImpl;
import com.piotr.scraper.MorrisonsScraper;

public class MorrisonsScraperTest {

	@Test
	public void shouldCreateObjectWithEmptyConstructor() {
		
		MorrisonsScraper morrisons = new MorrisonsScraper();
		
		String item = morrisons.getProductList().get(0);
		
		assertTrue(item.equals("water"));
	}
	
	@Test
	public void shouldCreateObjectWithListAsConstructorArgument() {
		
		List<String> productList = new ArrayList<>();
		productList.add("water");
		productList.add("yogurth");
		
		
		MorrisonsScraper morrisons = new MorrisonsScraper(productList);
		
		List<String> items = morrisons.getProductList();
		
		assertTrue(items.equals(productList));
	}
	
	@Test
	public void shouldSetProductDao() {
		
		ProductDaoImpl product = new ProductDaoImpl();
		
		MorrisonsScraper morrisons = new MorrisonsScraper();
		
		morrisons.setProductDao(product);

		assertEquals(product, morrisons.getProductDao());
	}
	
	@Test
	public void shouldSetRetailerDao() {
		
		RetailerDaoImpl retailer = new RetailerDaoImpl();
		
		MorrisonsScraper morrisons = new MorrisonsScraper();
		
		morrisons.setRetailerDao(retailer);

		assertEquals(retailer, morrisons.getRetailerDao());
	}
	
	
	
	@Test
	public void shouldSetRetailerProductDao() {
		
		RetailerProductDaoImpl retailerProduct = new RetailerProductDaoImpl();
		
		MorrisonsScraper morrisons = new MorrisonsScraper();
		
		morrisons.setRetailerProductDao(retailerProduct);

		assertEquals(retailerProduct, morrisons.getRetailerProductDao());
	}
	
	
	

}
