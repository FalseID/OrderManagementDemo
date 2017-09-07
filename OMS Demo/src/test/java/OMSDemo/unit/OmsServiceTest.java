package OMSDemo.unit;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import OMSDemo.repository.ClientRepository;
import OMSDemo.repository.OrderRepository;
import OMSDemo.repository.ProductRepository;
import OMSDemo.services.FixerRatesService;
import OMSDemo.services.OmsService;
import OMSDemo.util.PriceConverter;
import OMSDemo.util.PriceFormatter;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OmsService.class)
public class OmsServiceTest {

    @MockBean
    private ClientRepository clientRepo;

    @MockBean
    private ProductRepository productRepo;

    @MockBean
    private OrderRepository orderRepo;

    @MockBean
    private FixerRatesService fixer;

    @MockBean
    private PriceConverter priceConverter;

    @MockBean
    private PriceFormatter priceFormatter;

    @Autowired
    private OmsService omsService;

    @Test
    public void testGetCountries() throws IOException {
        given(this.priceConverter.getSupportedCountries()).willReturn(
                new HashSet<String>(Arrays.asList("United States", "United Kingdom")));

        List<String> countries = omsService.getCountries();
        Assert.assertNotNull(countries);
        Assert.assertFalse(countries.isEmpty());
        Assert.assertTrue(countries.contains("United States"));
    }

}
