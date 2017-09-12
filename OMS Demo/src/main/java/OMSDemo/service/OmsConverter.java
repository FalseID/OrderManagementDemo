package OMSDemo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Locale.getISOCountries;
import static java.util.stream.Collectors.toMap;

@Component
public class OmsConverter {

    @Autowired
    FixerRatesService fixer;

    public BigDecimal convert(BigDecimal price, String country) throws IOException {
        Map<String, Object> fixerRates = fixer.getFixerRatesData();
        String baseCurrencyCode = (String) fixerRates.get("base");
        Map<String, Double> currencyCodeToRateMap = (HashMap<String, Double>) fixerRates.get("rates");
        Map<String, String> countryToSupportedCurrencyMap = getCountryToSupportedCurrencyMap(currencyCodeToRateMap, baseCurrencyCode);

        String currencyCode = countryToSupportedCurrencyMap.get(country);
        if (currencyCode.equals("EUR"))
            return price;
        else
            return price.multiply(new BigDecimal(currencyCodeToRateMap.get(currencyCode)));
    }

    public Set<String> getSupportedCountries() throws IOException {
        Map<String, Object> fixerRates = fixer.getFixerRatesData();
        String baseCurrencyCode = (String) fixerRates.get("base");
        Map<String, Double> currencyCodeToRateMap = (HashMap<String, Double>) fixerRates.get("rates");
        return getCountryToSupportedCurrencyMap(currencyCodeToRateMap, baseCurrencyCode).keySet();
    }

    private Map<String, String> getCountryToSupportedCurrencyMap(Map<String, Double> currencyToRateMap, String baseCurrencyCode) {
        return Arrays.stream(getISOCountries())
                .map(iso -> new Locale("", iso))
                .filter(locale -> hasCompatibleCurrency(locale, currencyToRateMap, baseCurrencyCode))
                .collect(toMap(locale -> locale.getDisplayCountry(), locale -> getCurrencyCode(locale)));
    }

    private boolean hasCompatibleCurrency(Locale locale, Map<String, Double> currencyToRateMap, String baseCurrencyCode) {
        String currencyCode = getCurrencyCode(locale);
        return !currencyCode.isEmpty() && (currencyToRateMap.keySet().contains(currencyCode) || currencyCode.equals(baseCurrencyCode));
    }

    private String getCurrencyCode(Locale locale) {
        Currency currency = Currency.getInstance(locale);
        return currency != null ? currency.getCurrencyCode() : "";
    }
}
