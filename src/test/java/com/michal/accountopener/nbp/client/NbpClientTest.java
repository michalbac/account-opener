package com.michal.accountopener.nbp.client;

import com.michal.accountopener.domain.NbpRate;
import com.michal.accountopener.domain.Rate;
import com.michal.accountopener.nbp.config.NbpConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NbpClientTest {
    @InjectMocks
    private NbpClient nbpClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private NbpConfig nbpConfig;

    @Before
    public void init(){
        when(nbpConfig.getNbpApiEndpoint()).thenReturn("http://test.com");
        when(nbpConfig.getCurrentUsdRateApiEndpoint()).thenReturn("http://test.com/api/exchangerates/rates/a/usd");
    }

    @Test
    public void shouldFetchUsdRate() throws URISyntaxException {
        BigDecimal fx = new BigDecimal("3.89");
        List<Rate> rateList = new ArrayList<>();
        Rate rate = new Rate("1", new Date(), fx);
        rateList.add(rate);
        NbpRate nbpRate = new NbpRate('A', "USD", "test", rateList);

        URI uri = new URI("http://test.com/api/exchangerates/rates/a/usd");

        when(restTemplate.getForObject(uri, NbpRate.class)).thenReturn(nbpRate);

        //When
        BigDecimal fetchedRate = nbpClient.getCurrentRate();

        //Then
        assertEquals(new BigDecimal("3.89"),fetchedRate);


    }
}
