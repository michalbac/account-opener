package com.michal.accountopener.account.domain;

import com.michal.accountopener.account.domain.model.Account;
import com.michal.accountopener.account.domain.model.Owner;
import com.michal.accountopener.account.domain.ports.AccountService;
import com.michal.accountopener.account.domain.ports.OwnerService;
import com.michal.accountopener.account.dto.AccountDto;
import com.michal.accountopener.account.dto.OpenAccountDto;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
public class AccountFacade {
    private AccountService accountService;
    private OwnerService ownerService;

    public AccountFacade(AccountService accountService, OwnerService ownerService) {
        this.accountService = accountService;
        this.ownerService = ownerService;
    }

    public AccountDto create(OpenAccountDto accountDto) {
        Owner owner = ownerService.saveIfNotExists(accountDto.getName(), accountDto.getSurname());
        Account account = new Account(accountDto.getStartingPlnBalance(), owner);
        return account.dto();
    }

    public AccountDto changeAmountToUsd(long id, BigDecimal amount) {
        return accountService.changeAmountToUsd(id, amount);
    }

    public AccountDto changeAmountToPln(long id, BigDecimal amount) {
        return accountService.changeAmountToPln(id, amount);
    }

    public AccountDto save(Account account){
        return accountService.save(account).dto();
    }

    public AccountDto getAccount(long id) {
        return accountService.getAccount(id).dto();
    }

    public void deleteAccount(long id) {
        accountService.deleteAccount(id);
    }
}
