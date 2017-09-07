package OMSDemo.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import OMSDemo.domain.Client;
import OMSDemo.domain.OrderTransfer;
import OMSDemo.domain.Product;
import OMSDemo.domain.StoreOrder;
import OMSDemo.services.OmsService;

@Controller
public class OmsController {

    @Autowired
    OmsService omsService;

    @ModelAttribute("countries")
    public List<String> getCountries() {
        try {
            return omsService.getCountries();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to fetch supported countries", ex);
        }
    }

    @ModelAttribute("client")
    public Client client() {
        return new Client("", "", "", "", "");
    }

    @ModelAttribute("product")
    public Product product() {
        return new Product("", new BigDecimal(0), "", new Date());
    }

    @ModelAttribute("orderTransfer")
    public OrderTransfer order() {
        return new OrderTransfer(0, 0);
    }

    @ModelAttribute("products")
    public List<Product> products() {
        return omsService.getProducts();
    }

    @ModelAttribute("clients")
    public List<Client> clients() {
        return omsService.getClients();
    }

    @ModelAttribute("orders")
    public List<StoreOrder> orders() {
        return omsService.getOrders();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getPage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/saveClient", method = RequestMethod.POST)
    public String saveClient(@Valid @ModelAttribute(value = "client") Client client, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) omsService.saveClient(client);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute(value = "product") Product product, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) omsService.saveProduct(product);
        return "redirect:/";
    }

    @RequestMapping(value = "/conductOrder", method = RequestMethod.POST)
    public String conductOrdersaveClient(@ModelAttribute(value = "orderTransfer") OrderTransfer orderTransfer) {
        try {
            omsService.makeTransaction(orderTransfer);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to make transaction", ex);
        }
        return "redirect:/";
    }
}
