package main.java.OMSDemo;

import java.math.BigDecimal;
import java.util.Date;

import main.java.OMSDemo.domain.Client;
import main.java.OMSDemo.domain.Product;
import main.java.OMSDemo.repository.ClientRepository;
import main.java.OMSDemo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExampleData implements CommandLineRunner{
	
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(String... arg0) throws Exception {
		clientRepository.save(new Client("Jack", "Bauer", "555-555-555", "United States", "Colonial Drive"));
		clientRepository.save(new Client("Chloe", "O'Brian", "44444 4444", "United Kingdom", "9th Street"));
		clientRepository.save(new Client("Kim", "Bauer", "222-222-222", "United States", "Riverside Drive"));
		clientRepository.save(new Client("David", "Palmer", "111-111-111", "United States", "6th Street"));
		clientRepository.save(new Client("Peeter", "Peeterson", "151 1515", "Eesti", "Järve tänav"));
		
		productRepository.save(new Product("Soap", new BigDecimal(13), "A regular old bar of soap", new Date()));
		productRepository.save(new Product("Bread", new BigDecimal(5), "A regular old bread", new Date()));
		productRepository.save(new Product("Vanilla Extract", new BigDecimal(5), "A regular old vanilla", new Date()));
		productRepository.save(new Product("Bean", new BigDecimal(5), "A regular old bean", new Date()));
	}
	
	
}
