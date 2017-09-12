package OMSDemo.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import static java.text.NumberFormat.getCurrencyInstance;
import static java.util.Arrays.stream;
import static java.util.Locale.getISOCountries;
import static java.util.stream.Collectors.toMap;

public class FormatUtil {

    public static String format(BigDecimal price, String country) {
        String countryIso = getCountryToISOMap().get(country);
        NumberFormat nf = getCurrencyInstance(new Locale("", countryIso));
        return nf.format(price.doubleValue());
    }

    public static Map<String, String> getCountryToISOMap() {
        return stream(getISOCountries())
                .collect(toMap(iso -> new Locale("", iso).getDisplayCountry(), iso -> iso));
    }
}
