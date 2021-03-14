package com.michal.accountopener.controller;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import com.michal.accountopener.mapper.AccountMapper;
import com.michal.accountopener.service.AccountService;
import com.michal.accountopener.utils.CurrencyConvertingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    CurrencyConvertingUtil converter;


    @PostMapping("/new")
    public Account createAccount(@RequestBody AccountDto accountDto){
        return accountService.save(accountMapper.mapToAccount(accountDto));
    }

    @GetMapping("/{id}/pln")
    public AccountDto getAccountInfoPln (@PathVariable long id){
        AccountDto accountDto = accountMapper.mapToAccountDto(accountService.getAccount(id));
        if (accountDto.getCurrency().toString().equals("PLN")){
            return accountDto;
        } else {
            BigDecimal balanceInUsd = accountDto.getStartingBalance();
            //todo
            //method to convert balance in accountDto;
            return accountDto;

        }
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable long id){
        accountService.deleteAccount(id);
    }
}
