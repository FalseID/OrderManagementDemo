package main.java.OMSDemo.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import main.java.OMSDemo.services.FixerRatesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class PriceConverter {
	private Map<String, Double> currencyToRate;
	private Map<String, String> countryToSupportedCurrency;
	
	@Autowired 
	private FixerRatesService fixer;
	
	public PriceConverter(){
		super();
	}
	
	public BigDecimal convert(BigDecimal old_price, String country) throws IOException{
		updateRates();
		
		String currency = this.countryToSupportedCurrency.get(country);
		if(currency.equals("EUR")){
			return old_price;
		}
		
		BigDecimal new_price = old_price.multiply(new BigDecimal(currencyToRate.get(currency)));
		return new_price;
	}
	
	/**
	 * Initializes our fields(updates conversion rates and supported countries).
	 * @throws IOException
	 */
	public void updateRates() throws IOException{
		/*
		 *Initalize currencyToRate 
		 */
		Map<String, Object> map = fixer.getFixerData();
		this.currencyToRate = (HashMap<String, Double>) map.get("rates");
		String base = (String) map.get("base");
		/*
		 *Initalize countryToSupportedCurrency
		 */
		Map<String, String> countriesToSupportedCurrency = new HashMap<>();
	    for (String iso : Locale.getISOCountries()) {
	        Locale l = new Locale("", iso);
	        Currency currency = Currency.getInstance(l);
	        if (currency == null){
	        	continue;
	        }
	        String currency_code = currency.getCurrencyCode();
	        //We only include countries that use the base currency or have an available conversion rate from our API.
	        if (this.currencyToRate.keySet().contains(currency_code) || currency_code.equals(base)){
	        	countriesToSupportedCurrency.put(l.getDisplayCountry(), currency_code);
	        }
	    }
	    this.countryToSupportedCurrency = countriesToSupportedCurrency;
	}

	public Set<String> getSupportedCountries() throws IOException {
		updateRates();
		return this.countryToSupportedCurrency.keySet();
	}
	
}
