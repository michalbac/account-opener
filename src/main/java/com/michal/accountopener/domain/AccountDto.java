package com.michal.accountopener.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private long id;
    private Currency currency;
    private BigDecimal startingBalance;
    private User user;
}
