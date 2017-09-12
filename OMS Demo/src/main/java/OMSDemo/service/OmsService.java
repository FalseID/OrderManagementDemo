package OMSDemo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OMSDemo.domain.Client;
import OMSDemo.domain.OrderTransfer;
import OMSDemo.domain.Product;
import OMSDemo.domain.StoreOrder;
import OMSDemo.repository.ClientRepository;
import OMSDemo.repository.OrderRepository;
import OMSDemo.repository.ProductRepository;

import static java.util.stream.Collectors.toList;

import static OMSDemo.util.FormatUtil.format;

@Service
public class OmsService {

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OmsConverter converter;

    public List<String> getCountries() throws IOException {
        List<String> countries = new ArrayList<>(converter.getSupportedCountries());
        return countries.stream().sorted().collect(toList());
    }

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public List<Client> getClients() {
        return clientRepo.findAll();
    }

    public List<StoreOrder> getOrders() {
        return orderRepo.findAll();
    }

    @Transactional
    public void saveClient(Client client) {
        clientRepo.save(client);
    }

    @Transactional
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    /**
     * Makes a transaction specified in the OrderTransfer object.
     *
     * @param orderTransfer
     * @return The StoreOrder id number.
     * @throws IOException
     */
    @Transactional
    public long makeTransaction(OrderTransfer orderTransfer) throws IOException {
        long securityCode = orderTransfer.getSecurityCode();
        long barCode = orderTransfer.getBarCode();

        Client client = clientRepo.findBySecurityCode(securityCode);
        Product product = productRepo.findByBarCode(barCode);

        BigDecimal convertedPrice = converter.convert(product.getPrice(), client.getCountry());
        String formattedPrice = format(convertedPrice, client.getCountry());

        StoreOrder storeOrder = new StoreOrder(client, product, formattedPrice, new Date());
        orderRepo.save(storeOrder);

        return storeOrder.getNumber();
    }
}
