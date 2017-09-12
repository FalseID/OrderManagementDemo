package OMSDemo.unit;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import OMSDemo.service.FixerRatesService;
import OMSDemo.service.OmsConverter;

import static java.util.Arrays.asList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmsConverterTest {

    @MockBean
    FixerRatesService fixer;

    @Autowired
    OmsConverter converter;

    Map<String, Object> mockFixerData;

    @Before
    public void setUp() {
        mockFixerData = new HashMap<>();
        Map<String, Double> mockFixerRates = new HashMap<>();

        mockFixerRates.put("USD", 1.00);
        mockFixerRates.put("GBP", 2.00);

        mockFixerData.put("base", "EUR");
        mockFixerData.put("date", "2000-01-03");
        mockFixerData.put("rates", mockFixerRates);
    }

    @Test
    public void testConversions() throws IOException {
        given(fixer.getFixerRatesData()).willReturn(mockFixerData);

        Set<String> countries = converter.getSupportedCountries();

        assertThat(countries).isNotEmpty();
        assertThat(countries).containsAll(asList("France", "United States", "United Kingdom"));
    }

    @Test
    public void testConversion() throws IOException {
        given(fixer.getFixerRatesData()).willReturn(mockFixerData);

        BigDecimal convertedPrice1 = converter.convert(new BigDecimal(5), "United States");
        BigDecimal convertedPrice2 = converter.convert(new BigDecimal(4), "United Kingdom");

        assertThat(convertedPrice1).isNotNull();
        assertThat(convertedPrice2).isNotNull();

        assertThat(convertedPrice1).isEqualTo("5");
        assertThat(convertedPrice2).isEqualTo("8");
    }
}
