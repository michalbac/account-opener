package com.michal.accountopener.controller;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import com.michal.accountopener.domain.OpenAccountDto;
import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.mapper.AccountMapper;
import com.michal.accountopener.mapper.OpenAccountMapper;
import com.michal.accountopener.nbp.client.NbpClient;
import com.michal.accountopener.service.AccountService;
import com.michal.accountopener.service.OwnerService;
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
    OwnerService ownerService;

    @Autowired
    NbpClient nbpClient;

    @Autowired
    CurrencyConvertingUtil convertingUtil;

    @PostMapping("/new")
    public AccountDto createAccount(@RequestBody OpenAccountDto accountDto){
        Owner owner = ownerService.saveIfNotExists(accountDto.getName(), accountDto.getSurname());
        Account account = openAccountMapper.mapToAccount(accountDto);
        account.setOwner(owner);
        accountService.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    @GetMapping("/{id}/pln")
    public BigDecimal getAccountBalancePln (@PathVariable long id){
        AccountDto accountDto = accountMapper.mapToAccountDto(accountService.getAccount(id));
        if (accountDto.getCurrency().toString().equals("PLN")){
            return accountDto.getStartingBalance();
        } else {
            return convertingUtil.getBalanceInPln(accountDto, nbpClient.getCurrentRate());
        }
    }

    @GetMapping("/{id}/usd")
    public BigDecimal getAccountBalanceUsd (@PathVariable long id){
        AccountDto accountDto = accountMapper.mapToAccountDto(accountService.getAccount(id));
        if (accountDto.getCurrency().toString().equals("USD")){
            return accountDto.getStartingBalance();
        } else {
            return convertingUtil.getBalanceInUsd(accountDto, nbpClient.getCurrentRate());
        }
    }

    @GetMapping("convert/usd/{id}")
    public AccountDto convertAccountToUsd (@PathVariable long id){
        Account account = convertingUtil.convertToUsd(accountService.getAccount(id), nbpClient.getCurrentRate());
        AccountDto accountDto = accountMapper.mapToAccountDto(account);
        return accountDto;
    }

    @GetMapping("convert/pln/{id}")
    public AccountDto convertAccountToPln (@PathVariable long id){
        Account account = convertingUtil.convertToPln(accountService.getAccount(id), nbpClient.getCurrentRate());
        AccountDto accountDto = accountMapper.mapToAccountDto(account);
        return accountDto;
    }

    @GetMapping("/{id}")
    public AccountDto getAccountInfo(@PathVariable long id){
        return accountMapper.mapToAccountDto(accountService.getAccount(id));
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable long id){
        accountService.deleteAccount(id);
    }
}
