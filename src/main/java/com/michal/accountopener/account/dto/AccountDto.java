package com.michal.accountopener.account.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountDto {
    private long id;
    private BigDecimal balanceInUsd;
    private BigDecimal balanceInPln;
    private OwnerDto owner;
}
