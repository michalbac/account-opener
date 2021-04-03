package com.michal.accountopener.utils;

import com.michal.accountopener.domain.AccountDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CurrencyConvertingUtil {


    public static BigDecimal convertToPln(BigDecimal amountInUsd, BigDecimal exchangeRate){
        return amountInUsd.multiply(exchangeRate);
    }

    public static BigDecimal convertToUsd(BigDecimal amountInPln, BigDecimal exchangeRate){
        return amountInPln.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getBalanceInUsd(AccountDto account, BigDecimal rate){
        if (account.getCurrency().toString().equals("USD")){
            return account.getStartingBalance();
        } else if (account.getCurrency().toString().equals("PLN")){
            return convertToUsd(account.getStartingBalance(), rate);
        }
        return BigDecimal.ONE;
    }

    public static BigDecimal getBalanceInPln(AccountDto account, BigDecimal rate){
        if(account.getCurrency().toString().equals("PLN")){
            return account.getStartingBalance();
        } else if (account.getCurrency().toString().equals("USD")){
            return convertToPln(account.getStartingBalance(), rate);
        }
        return BigDecimal.ONE;
    }
}
