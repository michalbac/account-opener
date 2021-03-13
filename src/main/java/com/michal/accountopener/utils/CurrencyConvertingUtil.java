package com.michal.accountopener.utils;

import com.michal.accountopener.domain.Account;

import java.math.BigDecimal;

public class CurrencyConvertingUtil {


    public static BigDecimal convertToPln(BigDecimal amountInUsd, BigDecimal exchangeRate){
        return amountInUsd.divide(exchangeRate);
    }

    public static BigDecimal convertToUsd(BigDecimal amountInPln, BigDecimal exchangeRate){
        return amountInPln.multiply(exchangeRate);
    }

    public static BigDecimal getBalanceInUsd(Account account, BigDecimal rate){
        if (account.getCurrency().equals("USD")){
            return account.getStartingBalance();
        } else if (account.getCurrency().equals("PLN")){
            return convertToUsd(account.getStartingBalance(), rate);
        }
        return BigDecimal.ONE;
    }

    public static BigDecimal getBalanceInPln(Account account, BigDecimal rate){
        if(account.getCurrency().equals("PLN")){
            return account.getStartingBalance();
        } else if (account.getCurrency().equals("USD")){
            return convertToPln(account.getStartingBalance(), rate);
        }
        return BigDecimal.ONE;
    }
}
