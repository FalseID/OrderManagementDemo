package OMSDemo.unit;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import OMSDemo.util.FormatUtil;

import static org.assertj.core.api.Assertions.assertThat;

import static OMSDemo.util.FormatUtil.format;
import static OMSDemo.util.FormatUtil.getCountryToISOMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormatUtil.class)
public class FormatUtilTest {

    @Test
    public void IsoCodesTest() {
        Map<String, String> countryToISOMap = getCountryToISOMap();

        assertThat(countryToISOMap).isNotEmpty();
        assertThat(countryToISOMap).containsEntry("United States", "US");
    }

    @Test
    public void FormatTest() {
        String formattedPrice1 = format(new BigDecimal(0), "United States");
        String formattedPrice2 = format(new BigDecimal(100), "United Kingdom");

        assertThat(formattedPrice1).isNotEmpty();
        assertThat(formattedPrice2).isNotEmpty();

        assertThat(formattedPrice1).isEqualTo("USD 0.00");
        assertThat(formattedPrice2).isEqualTo("GBP 100.00");
    }
}
