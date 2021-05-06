package com.michal.accountopener.account.domain.ports;

import com.michal.accountopener.account.domain.model.Account;
import com.michal.accountopener.account.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;


public class AccountService {
    private AccountRepository accountRepository;
    private ExchangeRateClient exchangeRateClient;

    public AccountService(AccountRepository accountRepository, ExchangeRateClient exchangeRateClient) {
        this.accountRepository = accountRepository;
        this.exchangeRateClient = exchangeRateClient;
    }

    public Account getAccount(long id) {
        return accountRepository.findOneOrThrow(id);

    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccount(long id) {
        accountRepository.deleteById(id);
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public AccountDto changeAmountToUsd(long id, BigDecimal amount) {
        //To Do: check if changed amount persist in repository
        Account account = getAccount(id);
        account.changeBalanceInUsd(amount, exchangeRateClient.getCurrentRate());
        return getAccount(id).dto();
    }

    public AccountDto changeAmountToPln(long id, BigDecimal amount) {
        //To Do: check if changed amount persist in repository
        Account account = getAccount(id);
        account.changeBalanceInPln(amount, exchangeRateClient.getCurrentRate());
        return getAccount(id).dto();
    }

}
