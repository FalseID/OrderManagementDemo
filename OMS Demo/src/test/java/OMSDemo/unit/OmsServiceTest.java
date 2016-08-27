package test.java.OMSDemo.unit;

import static org.mockito.BDDMockito.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.java.OMSDemo.domain.Client;
import main.java.OMSDemo.domain.OrderTransfer;
import main.java.OMSDemo.domain.Product;
import main.java.OMSDemo.repository.ClientRepository;
import main.java.OMSDemo.repository.OrderRepository;
import main.java.OMSDemo.repository.ProductRepository;
import main.java.OMSDemo.services.FixerRatesService;
import main.java.OMSDemo.services.OmsService;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OmsService.class)
public class OmsServiceTest { 
	
	 @MockBean
	 private  ClientRepository clientRepo;
	 
	 @MockBean
	 private  ProductRepository productRepo;
	 
	 @MockBean
	 private  OrderRepository orderRepo;
	 
	 @MockBean
	 private FixerRatesService fixer;
	 
	 @MockBean
	 private  PriceConverter priceConverter;
	 
	 @MockBean
	 private  PriceFormatter priceFormatter;
	 
	 @Autowired
	 private  OmsService omsService;

	 @Test
     public void testGetCountries() throws IOException{
		 given(this.priceConverter.getSupportedCountries()).willReturn(
				 new HashSet<String>(Arrays.asList("United States","United Kingdom")));
		 
		 List<String> countries = omsService.getCountries();
		 Assert.assertNotNull(countries);
		 Assert.assertFalse(countries.isEmpty());
		 Assert.assertTrue(countries.contains("United States"));
     }

}
