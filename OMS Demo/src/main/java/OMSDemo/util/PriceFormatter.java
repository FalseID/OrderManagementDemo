package main.java.OMSDemo.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PriceFormatter {
	
	
	public PriceFormatter(){
		super();
	}
	
	public String format(BigDecimal price, String country){
		
		String countryISO = getCountryToISOMap().get(country);
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("", countryISO));
		
		return nf.format(price.doubleValue());
	}
	
	public Map<String, String> getCountryToISOMap(){
		Map<String, String> countries = new HashMap<>();
	    for (String iso : Locale.getISOCountries()) {
	        Locale l = new Locale("", iso);
	        countries.put(l.getDisplayCountry(), iso);
	    }
	    return countries;
	}
}
