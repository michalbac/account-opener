package com.michal.accountopener.account.domain.ports;

import java.math.BigDecimal;

public interface ExchangeRateClient {
    public BigDecimal getCurrentRate();
}
