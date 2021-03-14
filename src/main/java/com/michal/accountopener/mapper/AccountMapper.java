package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    @Autowired
    OwnerMapper ownerMapper;

    public Account mapToAccount(AccountDto accountDto){
        return new Account(accountDto.getId(),
                accountDto.getCurrency(),
                accountDto.getStartingBalance(),
                ownerMapper.mapToOwner(accountDto.getOwner()));
    }

    public AccountDto mapToAccountDto(Account account){
        return new AccountDto(account.getId(),
                account.getCurrency(),
                account.getStartingBalance(),
                ownerMapper.mapToOwnerDto(account.getOwner()));
    }

    public List<Account> mapToAccountList(List<AccountDto> accountDtoList){
        if(accountDtoList==null || accountDtoList.isEmpty()){
            return new ArrayList<>();
        }
        return accountDtoList.stream()
                .map(accountDto ->
                        new Account(accountDto.getId(),
                                accountDto.getCurrency(),accountDto.getStartingBalance(),
                                ownerMapper.mapToOwner(accountDto.getOwner())))
                .collect(Collectors.toList());
    }

    public List<AccountDto> mapToAccountDtoList(List<Account> accountList){
        if( accountList==null || accountList.isEmpty()){
            return new ArrayList<>();
        }
        return accountList.stream()
                .map(account ->
                        new AccountDto(account.getId(),
                                account.getCurrency(),account.getStartingBalance(),
                                ownerMapper.mapToOwnerDto(account.getOwner())))
                .collect(Collectors.toList());
    }
}
