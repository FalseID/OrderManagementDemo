package main.java.OMSDemo.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FixerRatesService {
	private String urlString = "http://api.fixer.io/latest";
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getFixerData() throws JsonParseException, JsonMappingException, IOException{
		URL url = new URL(urlString);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(url, Map.class);
		return map;
	}
	
	
}