package main.java.OMSDemo.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import main.java.OMSDemo.domain.Client;
import main.java.OMSDemo.domain.OrderTransfer;
import main.java.OMSDemo.domain.Product;
import main.java.OMSDemo.domain.StoreOrder;
import main.java.OMSDemo.repository.ClientRepository;
import main.java.OMSDemo.repository.OrderRepository;
import main.java.OMSDemo.repository.ProductRepository;
import main.java.OMSDemo.util.PriceConverter;
import main.java.OMSDemo.util.PriceFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class OmsService {
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private PriceConverter converter;
	
	@Autowired
	private PriceFormatter formatter;
	
	public List<String> getCountries() throws IOException{
		List<String> countries = new ArrayList<String>(converter.getSupportedCountries());
		Collections.sort(countries);
		return countries;
	}
	
	public List<Product> getProducts(){
		return productRepo.findAll();
	}
	
	public List<Client> getClients(){
		return clientRepo.findAll();
	}
	
	public List<StoreOrder> getOrders(){
		return orderRepo.findAll();
	}
	
	@Transactional
	public void saveClient(Client client){
		clientRepo.save(client);
	}
	
	@Transactional
	public void saveProduct(Product product){
		productRepo.save(product);
	}
	
	@Transactional
	public void makeTransaction(OrderTransfer orderTransfer) throws IOException{
		long securityCode = orderTransfer.getSecurityCode();
		long barCode = orderTransfer.getBarCode();
		
		Client client = clientRepo.findBySecurityCode(securityCode);
		Product product = productRepo.findByBarCode(barCode);
		
		BigDecimal convertedPrice = converter.convert(product.getPrice(), client.getCountry());
		String formattedPrice = formatter.format(convertedPrice, client.getCountry());
		orderRepo.save(new StoreOrder(client, product, formattedPrice, new Date()));
	}
	
}
