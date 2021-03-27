package com.michal.accountopener.controller;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import com.michal.accountopener.domain.OpenAccountDto;
import com.michal.accountopener.mapper.AccountMapper;
import com.michal.accountopener.mapper.OpenAccountMapper;
import com.michal.accountopener.nbp.client.NbpClient;
import com.michal.accountopener.service.AccountService;
import com.michal.accountopener.utils.CurrencyConvertingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Currency;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    OpenAccountMapper openAccountMapper;

    @Autowired
    NbpClient nbpClient;



    @PostMapping("/new")
    public AccountDto createAccount(@RequestBody OpenAccountDto accountDto){
        return openAccountMapper.mapToAccountDto(accountDto);
    }

    @GetMapping("/{id}/pln")
    public BigDecimal getAccountBalancePln (@PathVariable long id){
        AccountDto accountDto = accountMapper.mapToAccountDto(accountService.getAccount(id));
        if (accountDto.getCurrency().toString().equals("PLN")){
            return accountDto.getStartingBalance();
        } else {
            return CurrencyConvertingUtil.getBalanceInPln(accountDto, nbpClient.getCurrentRate());

        }
    }

    @GetMapping("/{id}/usd")
    public BigDecimal getAccountBalanceUsd (@PathVariable long id){
        AccountDto accountDto = accountMapper.mapToAccountDto(accountService.getAccount(id));
        if (accountDto.getCurrency().toString().equals("USD")){
            return accountDto.getStartingBalance();
        } else {
            return CurrencyConvertingUtil.getBalanceInUsd(accountDto, nbpClient.getCurrentRate());
        }
    }

    @GetMapping("convert/usd/{id}")
    public AccountDto convertAccountToUsd (@PathVariable long id){
        Account account = accountService.getAccount(id);

        if (account.getCurrency().toString().equals("PLN")){
            BigDecimal balanceInPln = account.getStartingBalance();
            BigDecimal balanceInUsd = CurrencyConvertingUtil.convertToUsd(balanceInPln, nbpClient.getCurrentRate());
            account.setCurrency(Currency.getInstance("USD"));
            account.setStartingBalance(balanceInUsd);
        }
        AccountDto accountDto = accountMapper.mapToAccountDto(account);
        return accountDto;
    }

    @GetMapping("convert/pln/{id}")
    public AccountDto convertAccountToPln (@PathVariable long id){
        Account account = accountService.getAccount(id);

        if (account.getCurrency().toString().equals("USD")){
            BigDecimal balanceInUsd = account.getStartingBalance();
            BigDecimal balanceInPln = CurrencyConvertingUtil.convertToPln(balanceInUsd, nbpClient.getCurrentRate());
            account.setCurrency(Currency.getInstance("PLN"));
            account.setStartingBalance(balanceInPln);
        }
        AccountDto accountDto = accountMapper.mapToAccountDto(account);
        return accountDto;
    }



    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable long id){
        accountService.deleteAccount(id);
    }
}
