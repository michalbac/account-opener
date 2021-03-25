package com.michal.accountopener.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private long id;
    private Currency currency;
    private BigDecimal startingBalance;
    private OwnerDto owner;
}
