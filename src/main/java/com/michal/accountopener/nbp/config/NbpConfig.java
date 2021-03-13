package com.michal.accountopener.nbp.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NbpConfig {

    @Value("http://api.nbp.pl/api")
    private String nbpApiEndpoint;

    @Value("http://api.nbp.pl/api/exchangerates/rates/a/usd")
    private String currentUsdRateApiEndpoint;
}
