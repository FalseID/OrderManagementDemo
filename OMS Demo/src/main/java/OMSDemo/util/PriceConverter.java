package main.java.OMSDemo.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class PriceConverter {
	private URL url;
	private Map<String, Object> map;
	
	public PriceConverter(){
		super();
	}
	
	public BigDecimal convert(BigDecimal old_price, String country) throws IOException{
		obtainRates();
		
		String currency = LocaleUtil.getCountryToCurrencyMap().get(country);
		if(currency.equals("EUR")){
			return old_price;
		}
		
		Map<String, Double> currencies = (HashMap<String, Double>) map.get("rates");
		
		BigDecimal new_price = old_price.multiply(new BigDecimal(currencies.get(currency)));
		return new_price;
	}
	
	@SuppressWarnings("unchecked")
	public void obtainRates() throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		this.url = new URL("http://api.fixer.io/latest");
		this.map = mapper.readValue(url, Map.class);
	}
	
}
