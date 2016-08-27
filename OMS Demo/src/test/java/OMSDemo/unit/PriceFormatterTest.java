package test.java.OMSDemo.unit;

import java.math.BigDecimal;
import java.util.Map;

import main.java.OMSDemo.util.PriceFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceFormatter.class)
public class PriceFormatterTest {
	
	@Autowired
	private PriceFormatter formatter;
	
	@Test
	public void IsoCodesTest(){
		Map<String, String> iso = formatter.getCountryToISOMap();
		Assert.assertNotNull(iso);
		Assert.assertFalse(iso.isEmpty());
		Assert.assertTrue(iso.containsKey("United States"));
		Assert.assertTrue(iso.containsValue("US"));
	}
	
	@Test
	public void FormatTest(){
		String formattedPrice1 = formatter.format(new BigDecimal(0), "United States");
		String formattedPrice2 = formatter.format(new BigDecimal(100), "United Kingdom");
		Assert.assertFalse(formattedPrice1.isEmpty());
		Assert.assertFalse(formattedPrice2.isEmpty());
		Assert.assertTrue(formattedPrice1.equals("USD 0.00"));
		Assert.assertTrue(formattedPrice2.equals("GBP 100.00"));
	}
	
	
}
