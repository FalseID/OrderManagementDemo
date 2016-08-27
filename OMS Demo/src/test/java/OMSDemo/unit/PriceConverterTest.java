package test.java.OMSDemo.unit;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

import main.java.OMSDemo.services.FixerRatesService;
import main.java.OMSDemo.util.PriceConverter;
import main.java.OMSDemo.util.PriceFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceConverter.class)
public class PriceConverterTest {
	
	@MockBean
	private FixerRatesService fixer;
	
	@Autowired 
	private PriceConverter converter;
	
	Map<String, Object> mockFixerData;
	
	@Before
	public void setUp(){
		mockFixerData = new HashMap<String, Object>();
		Map<String, Double> mockFixerRates = new HashMap<String, Double>();
		
		mockFixerRates.put("USD", 1.00);
		mockFixerRates.put("GBP", 2.00);
		
		mockFixerData.put("base", "EUR");
		mockFixerData.put("date", "2000-01-03");
		mockFixerData.put("rates", mockFixerRates);
	}
	
	@Test
	public void testConversions() throws IOException{
		given(this.fixer.getFixerData()).willReturn(mockFixerData);
		
		/**
		 * Supported countries should contain countries that have currency rates present in fixerData and
		 * countries that use the base currency.
		 */
		Set<String> countries = converter.getSupportedCountries();
		Assert.assertNotNull(countries);
		Assert.assertFalse(countries.isEmpty());
		Assert.assertTrue(countries.contains("France"));
		Assert.assertTrue(countries.contains("United States"));
		Assert.assertTrue(countries.contains("United Kingdom"));
	}
	
	@Test
	public void testSupportedCountries() throws IOException{
		given(this.fixer.getFixerData()).willReturn(mockFixerData);
		
		BigDecimal convertedPrice1 = converter.convert(new BigDecimal(5), "United States");
		BigDecimal convertedPrice2 = converter.convert(new BigDecimal(4), "United Kingdom");
		
		Assert.assertNotNull(convertedPrice1);
		Assert.assertNotNull(convertedPrice2);
		
		Assert.assertTrue(convertedPrice1.toPlainString().equals("5"));
		Assert.assertTrue(convertedPrice2.toPlainString().equals("8"));

	}
	
}
