package OMSDemo.integration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import OMSDemo.OmsDemoApplication;
import OMSDemo.domain.Client;
import OMSDemo.domain.OrderTransfer;
import OMSDemo.domain.Product;
import OMSDemo.domain.StoreOrder;
import OMSDemo.repository.ClientRepository;
import OMSDemo.repository.OrderRepository;
import OMSDemo.repository.ProductRepository;
import OMSDemo.service.OmsService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OmsDemoApplication.class})
public class OmsServiceIntegrationTest {

    @Autowired
    ClientRepository clientRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OmsService omsService;

    Client client1;

    Product product1;

    StoreOrder order1;

    @Before
    public void setUp() {
        client1 = new Client("Chloe", "O'Brian", "44444 4444", "United Kingdom", "9th Street");
        product1 = new Product("Soap", new BigDecimal(9), "A regular old bar of soap", new Date());
        order1 = new StoreOrder(client1, product1, product1.getPrice().toString(), new Date());
    }

    @Test
    @Transactional
    public void testSaveClient() {
        omsService.saveClient(client1);

        Client receivedClient1 = clientRepo.findBySecurityCode(client1.getSecurityCode());

        assertThat(receivedClient1).isNotNull();
        assertThat(receivedClient1).isEqualToComparingFieldByFieldRecursively(client1);
    }

    @Test
    @Transactional
    public void testSaveProduct() {
        omsService.saveProduct(product1);

        Product receivedProduct1 = productRepo.findByBarCode(product1.getBarCode());

        assertThat(receivedProduct1).isNotNull();
        assertThat(receivedProduct1).isEqualToComparingFieldByFieldRecursively(product1);
    }

    @Test
    @Transactional
    public void testMakeOrder() throws IOException {
        omsService.saveClient(client1);
        omsService.saveProduct(product1);

        OrderTransfer orderTransfer1 = new OrderTransfer(client1.getSecurityCode(), product1.getBarCode());

        long orderNr1 = omsService.makeTransaction(orderTransfer1);
        List<StoreOrder> orders = orderRepo.findAll();
        StoreOrder receivedOrder1 = orderRepo.findByNumber(orderNr1);

        assertThat(orders).isNotEmpty();
        assertThat(orders).doesNotContainNull();
        assertThat(orders).hasSize(1);

        assertThat(receivedOrder1).isNotNull();
        assertThat(receivedOrder1).hasNoNullFieldsOrProperties();
        assertThat(receivedOrder1).isEqualToComparingOnlyGivenFields(order1, "client", "product");
    }
}
