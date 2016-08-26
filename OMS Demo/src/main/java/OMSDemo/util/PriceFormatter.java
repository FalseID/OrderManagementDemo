package main.java.OMSDemo.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class PriceFormatter {
	
	
	public PriceFormatter(){
		super();
	}
	
	public String format(BigDecimal price, String country){
		
		String countryISO = LocaleUtil.getCountryToISOMap().get(country);
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("", countryISO));
		
		return nf.format(price.doubleValue());
	}
}
