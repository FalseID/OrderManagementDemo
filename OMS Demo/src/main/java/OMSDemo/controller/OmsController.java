package main.java.OMSDemo.controller;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import main.java.OMSDemo.repository.ClientRepository;
import main.java.OMSDemo.repository.OrderRepository;
import main.java.OMSDemo.repository.ProductRepository;
import main.java.OMSDemo.services.OmsService;
import main.java.OMSDemo.util.PriceConverter;
import main.java.OMSDemo.util.PriceFormatter;
import main.java.OMSDemo.domain.Client;
import main.java.OMSDemo.domain.OrderTransfer;
import main.java.OMSDemo.domain.Product;
import main.java.OMSDemo.domain.StoreOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OmsController {
	
	@Autowired
	OmsService omsService;
	
	@ModelAttribute("countries")
	public List<String> getCountries(){
		try {
			return omsService.getCountries();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
	
	@ModelAttribute("client")
	public Client client(){
		return new Client("", "", "", "", "");
	}
	
	@ModelAttribute("product")
	public Product product(){
		return new Product("", new BigDecimal(0), "", new Date());
	}
	
	@ModelAttribute("orderTransfer")
	public OrderTransfer order(){
		return new OrderTransfer(0, 0);
	}
	
	@ModelAttribute("products")
	public List<Product> products(){
		return omsService.getProducts();
	}
	
	@ModelAttribute("clients")
	public List<Client> clients(){
		return omsService.getClients();
	}
	
	@ModelAttribute("orders")
	public List<StoreOrder> orders(){
		return omsService.getOrders();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getPage(){
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/saveClient", method = RequestMethod.POST)
	public String saveClient(@Valid @ModelAttribute(value="client") Client client, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "redirect:/";
		}
		omsService.saveClient(client);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute(value="product") Product product, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "redirect:/";
		}
		omsService.saveProduct(product);
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/conductOrder", method = RequestMethod.POST)
	public String conductOrdersaveClient(@ModelAttribute(value="orderTransfer") OrderTransfer orderTransfer){
		try {
			omsService.makeTransaction(orderTransfer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
