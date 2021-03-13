package com.michal.accountopener.nbp.client;

import com.michal.accountopener.domain.NbpRate;
import com.michal.accountopener.nbp.config.NbpConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@Component
public class NbpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NbpClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NbpConfig nbpConfig;

    public BigDecimal getCurrentRate(){
        URI url = buildURL();
        try {
            NbpRate response = restTemplate.getForObject(url, NbpRate.class);
                    return response.getRatesList().get(0).getMid();
        } catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return BigDecimal.ONE;
        }
    }

    private URI buildURL(){
        URI url = UriComponentsBuilder.fromHttpUrl(nbpConfig.getCurrentUsdRateApiEndpoint()).build().encode().toUri();
        return url;
    }
}
