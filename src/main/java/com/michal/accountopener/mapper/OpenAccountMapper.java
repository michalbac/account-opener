package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import com.michal.accountopener.domain.OpenAccountDto;
import com.michal.accountopener.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class OpenAccountMapper {
    @Autowired
    OwnerService ownerService;

    @Autowired
    OwnerMapper ownerMapper;


    public AccountDto mapToAccountDto(OpenAccountDto openAccountDto){
        AccountDto accountDto = new AccountDto();
        accountDto.setStartingBalance(openAccountDto.getStartingBalance());
        accountDto.setCurrency(Currency.getInstance("PLN"));
        return accountDto;
    }

    public Account mapToAccount(OpenAccountDto openAccountDto){
        Account account = new Account();
        account.setCurrency(Currency.getInstance("PLN"));
        account.setStartingBalance(openAccountDto.getStartingBalance());
        return account;
    }
}
