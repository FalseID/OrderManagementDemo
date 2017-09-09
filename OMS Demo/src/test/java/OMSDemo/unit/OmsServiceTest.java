package OMSDemo.unit;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import OMSDemo.repository.ClientRepository;
import OMSDemo.repository.OrderRepository;
import OMSDemo.repository.ProductRepository;
import OMSDemo.service.FixerRatesService;
import OMSDemo.service.OmsService;
import OMSDemo.util.PriceConverter;
import OMSDemo.util.PriceFormatter;

import static java.util.Arrays.asList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OmsService.class)
public class OmsServiceTest {

    @MockBean
    ClientRepository clientRepo;

    @MockBean
    ProductRepository productRepo;

    @MockBean
    OrderRepository orderRepo;

    @MockBean
    FixerRatesService fixer;

    @MockBean
    PriceConverter priceConverter;

    @MockBean
    PriceFormatter priceFormatter;

    @Autowired
    private OmsService omsService;

    @Test
    public void testGetCountries() throws IOException {
        given(priceConverter.getSupportedCountries()).willReturn(
                new HashSet<>(asList("United States", "United Kingdom")));

        List<String> countries = omsService.getCountries();

        assertThat(countries).isNotEmpty();
        assertThat(countries).contains("United States");
    }
}
