package com.michal.accountopener.utils;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.domain.OwnerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyConverterTestSuite {
    @Autowired
    CurrencyConvertingUtil convertingUtil;

    @Test
    public void convertToUsdTest(){
        //Given
        Owner owner = new Owner(1, "John", "Smith", new ArrayList<>());
        Account account = new Account(1, Currency.getInstance("PLN"), new BigDecimal("1000"), owner);

        //When
        Account accountAfterConversion = convertingUtil.convertToUsd(account, new BigDecimal("4"));

        //Then
        assertTrue(new BigDecimal("250").compareTo(accountAfterConversion.getStartingBalance())==0);
        assertEquals(Currency.getInstance("USD"), accountAfterConversion.getCurrency());
        assertEquals(owner.getId(), accountAfterConversion.getOwner().getId());
    }

    @Test
    public void convertToPlnTest(){
        //Given
        Owner owner = new Owner(1, "John", "Smith", new ArrayList<>());
        Account account = new Account(1, Currency.getInstance("USD"), new BigDecimal("100"), owner);

        //When
        Account accountAfterConversion = convertingUtil.convertToPln(account, new BigDecimal("3.5"));

        //Then
        assertTrue(new BigDecimal("350").compareTo(accountAfterConversion.getStartingBalance())==0);
        assertEquals(Currency.getInstance("PLN"), accountAfterConversion.getCurrency());
        assertEquals(owner.getId(), accountAfterConversion.getOwner().getId());
    }

    @Test
    public void shouldReturnBalanceInUsd(){
        //Given
        OwnerDto owner = new OwnerDto(1, "John", "Smith");
        AccountDto account = new AccountDto(1, Currency.getInstance("PLN"), new BigDecimal("1000"), owner);

        //When
        BigDecimal amountInUsd = convertingUtil.getBalanceInUsd(account, new BigDecimal("4"));

        //Then
        assertTrue(new BigDecimal("250").compareTo(amountInUsd)==0);
    }

    @Test
    public void shouldReturnBalanceInPln(){
        //Given
        OwnerDto owner = new OwnerDto(1, "John", "Smith");
        AccountDto account = new AccountDto(1, Currency.getInstance("USD"), new BigDecimal("200"), owner);

        //When
        BigDecimal amountInPln = convertingUtil.getBalanceInPln(account, new BigDecimal("3.75"));

        //Then
        assertTrue(new BigDecimal("750").compareTo(amountInPln)==0);
    }
}
