package test.java.OMSDemo.integration;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.OMSDemo.OmsDemoApplication;
import main.java.OMSDemo.domain.Client;
import main.java.OMSDemo.domain.Product;
import main.java.OMSDemo.repository.ClientRepository;
import main.java.OMSDemo.repository.ProductRepository;
import main.java.OMSDemo.services.OmsService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Testing the interaction between the OmsService and database.
 * @author Janar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OmsDemoApplication.class})
public class OmsServiceIntegrationTest {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired 
	private OmsService omsService;
	
	Client client1;
	Client client2;
	Client client3;
	
	Product product1;
	Product product2;
	Product product3;
	
	@Before
	public void setUp(){
		client1 = new Client("Chloe", "O'Brian", "44444 4444", "United Kingdom", "9th Street");
		client2 = new Client("Jack", "Bauer", "555-555-555", "United States", "Colonial Drive");
		client3 = new Client("Jack", "Bauer", "555-555-555", "United States", "Colonial Drive");
		
		product1 = new Product("Soap", new BigDecimal(9), "A regular old bar of soap", new Date());
		product2 = new Product("Bean", new BigDecimal(15), "A regular old bean", new Date());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse("1982-02-12");
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date();
		}
		product3 = new Product("Bean", new BigDecimal(15), "A regular old bean",date);
	}
	
	@Test
	public void testSaveClient(){
		omsService.saveClient(client1);
		omsService.saveClient(client2);
		omsService.saveClient(client3);
		
		Client receivedClient1 = clientRepo.findBySecurityCode(client1.getSecurityCode());
		Client receivedClient2 = clientRepo.findBySecurityCode(client2.getSecurityCode());
		Client receivedClient3 = clientRepo.findBySecurityCode(client3.getSecurityCode());
		
		Assert.assertNotNull(receivedClient1);
		Assert.assertNotNull(receivedClient2);
		Assert.assertNotNull(receivedClient3);
		
		Assert.assertTrue("Two clients have the same id",
				receivedClient2.getSecurityCode() != receivedClient3.getSecurityCode());
		Assert.assertTrue("Inconsistent data", receivedClient1.getPhoneNumber().equals("44444 4444"));
		Assert.assertTrue("Inconsistent data", receivedClient2.getLastName().equals("Bauer"));
		Assert.assertTrue("Inconsistent data", receivedClient3.getFirstName().equals("Jack"));
	}
	
	@Test
	public void testSaveProduct(){
		omsService.saveProduct(product1);
		omsService.saveProduct(product2);
		omsService.saveProduct(product3);
		
		Product receivedProduct1 = productRepo.findByBarCode(product1.getBarCode());
		Product receivedProduct2 = productRepo.findByBarCode(product2.getBarCode());
		Product receivedProduct3 = productRepo.findByBarCode(product3.getBarCode());
		
		Assert.assertNotNull(receivedProduct1);
		Assert.assertNotNull(receivedProduct2);
		
		Assert.assertTrue("Two products have the same bar code",
				receivedProduct2.getBarCode() != receivedProduct3.getBarCode());
		Assert.assertTrue("Inconsistent data", receivedProduct1.getName().equals("Soap"));
		Assert.assertTrue("Inconsistent data", receivedProduct2.getPrice().toPlainString().equals("15.00"));
		Assert.assertTrue("Inconsistent data", receivedProduct3.getReleaseDate().toString().equals("1982-02-12"));
	}
	

	
}