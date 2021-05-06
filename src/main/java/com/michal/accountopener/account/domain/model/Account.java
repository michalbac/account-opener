package com.michal.accountopener.account.domain.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.michal.accountopener.account.dto.AccountDto;
import com.michal.accountopener.account.dto.OwnerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private BigDecimal balanceInUsd;

    @NotNull
    private BigDecimal balanceInPln;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Owner owner;

    public Account(BigDecimal balanceInPln, Owner owner){
        this.balanceInPln = balanceInPln;
        this.owner = owner;
    }

    public AccountDto dto(){
        return AccountDto.builder()
                .id(id)
                .balanceInPln(balanceInPln)
                .balanceInUsd(balanceInUsd)
                .owner(owner.dto())
                .build();
    }

    public BigDecimal convertAmountToUsd (BigDecimal amountInPln, BigDecimal exchangeRate){
        return amountInPln.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal convertAmountToPln(BigDecimal amountInUsd, BigDecimal exchangeRate){
        return amountInUsd.multiply(exchangeRate);
    }

    public void changeBalanceInPln(BigDecimal amountToConvertToPln, BigDecimal exchangeRate) {
            BigDecimal newBalanceInUsd = getBalanceInUsd().subtract(amountToConvertToPln);
            setBalanceInUsd(newBalanceInUsd);
            BigDecimal newBalanceInPln = getBalanceInPln().add(convertAmountToPln(amountToConvertToPln, exchangeRate));
            setBalanceInPln(newBalanceInPln);
        }

    public void changeBalanceInUsd(BigDecimal amountToConvertToUsd, BigDecimal exchangeRate){
            BigDecimal newBalanceInPln = getBalanceInPln().subtract(amountToConvertToUsd);
            setBalanceInPln(newBalanceInPln);
            BigDecimal newBalanceInUsd = getBalanceInUsd().add(convertAmountToUsd(amountToConvertToUsd, exchangeRate));
            setBalanceInUsd(newBalanceInUsd);
    }

}
