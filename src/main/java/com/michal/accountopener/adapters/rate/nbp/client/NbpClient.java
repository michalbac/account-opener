package com.michal.accountopener.adapters.rate.nbp.client;

import com.michal.accountopener.adapters.rate.nbp.NbpRate;
import com.michal.accountopener.account.domain.ports.ExchangeRateClient;
import com.michal.accountopener.adapters.rate.nbp.config.NbpConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@Component
public class NbpClient implements ExchangeRateClient {
    private RestTemplate restTemplate;
    private NbpConfig nbpConfig;

    public NbpClient(RestTemplate restTemplate, NbpConfig nbpConfig) {
        this.restTemplate = restTemplate;
        this.nbpConfig = nbpConfig;
    }

    public BigDecimal getCurrentRate(){
        URI url = buildURL();
        try {
            NbpRate response = restTemplate.getForObject(url, NbpRate.class);
                    return response.getRatesList().get(0).getMid();
        } catch (RestClientException e){
            return BigDecimal.ONE;
        }
    }

    private URI buildURL(){
        URI url = UriComponentsBuilder.fromHttpUrl(nbpConfig.getCurrentUsdRateApiEndpoint()).build().encode().toUri();
        return url;
    }
}
