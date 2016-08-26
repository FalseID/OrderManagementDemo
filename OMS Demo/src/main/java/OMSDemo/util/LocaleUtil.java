package main.java.OMSDemo.util;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Some generic locale utility methods.
 * @author Janar
 *
 */
public final class LocaleUtil {
	
	private LocaleUtil(){
	
	}
	
	/**
	 * Returns a map of country names to their respective currency code.
	 * It disregards countries with no currency code.
	 * @return Map between a country name and its respective currency.
	 */
	public static Map<String, String> getCountryToCurrencyMap(){
		Map<String, String> currencies = new HashMap<>();
	    for (String iso : Locale.getISOCountries()) {
	        Locale l = new Locale("", iso);
	        Currency currency = Currency.getInstance(l);
	        if (currency == null){
	        	continue;
	        }
	        currencies.put(l.getDisplayCountry(), currency.getCurrencyCode());
	    }
	    return currencies;
	}
	
	/**
	 * Returns a map of country names to their respective ISO codes.
	 * @return Map between a country name and its respective ISO code.
	 */
	public static Map<String, String> getCountryToISOMap(){
		Map<String, String> countries = new HashMap<>();
	    for (String iso : Locale.getISOCountries()) {
	        Locale l = new Locale("", iso);
	        countries.put(l.getDisplayCountry(), iso);
	    }
	    return countries;
	}
	
	
	
	
}
