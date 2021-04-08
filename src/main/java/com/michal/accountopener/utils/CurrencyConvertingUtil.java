package com.michal.accountopener.utils;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

@Component
public class CurrencyConvertingUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConvertingUtil.class);


    public BigDecimal convertToPln(BigDecimal amountInUsd, BigDecimal exchangeRate){
        return amountInUsd.multiply(exchangeRate);
    }

    public BigDecimal convertToUsd(BigDecimal amountInPln, BigDecimal exchangeRate){
        return amountInPln.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBalanceInUsd(AccountDto account, BigDecimal rate){
        if (account.getCurrency().toString().equals("USD")){
            return account.getStartingBalance();
        } else if (account.getCurrency().toString().equals("PLN")){
            return convertToUsd(account.getStartingBalance(), rate);
        }
        LOGGER.error("Currency " +account.getCurrency() + " in account with id: "
                + account.getId() + " is not PLN or USD");
        return account.getStartingBalance();
    }

    public BigDecimal getBalanceInPln(AccountDto account, BigDecimal rate){
        if(account.getCurrency().toString().equals("PLN")){
            return account.getStartingBalance();
        } else if (account.getCurrency().toString().equals("USD")){
            return convertToPln(account.getStartingBalance(), rate);
        }
        LOGGER.error("Currency " +account.getCurrency() + " in account with id: "
                + account.getId() + " is not PLN or USD");
        return account.getStartingBalance();
    }

    public Account convertToPln(Account account, BigDecimal exchangeRate){
        if (account.getCurrency().toString().equals("USD")){
            BigDecimal balanceInUsd = account.getStartingBalance();
            BigDecimal balanceInPln = this.convertToPln(balanceInUsd, exchangeRate);
            account.setCurrency(Currency.getInstance("PLN"));
            account.setStartingBalance(balanceInPln);
        }
        return account;
    }

    public Account convertToUsd(Account account, BigDecimal exchangeRate){
        if (account.getCurrency().toString().equals("PLN")){
            BigDecimal balanceInPln = account.getStartingBalance();
            BigDecimal balanceInUsd = this.convertToUsd(balanceInPln, exchangeRate);
            account.setCurrency(Currency.getInstance("USD"));
            account.setStartingBalance(balanceInUsd);
        }
        return account;
    }
}
