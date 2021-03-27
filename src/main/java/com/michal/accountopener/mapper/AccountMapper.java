package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {
    @Autowired
    OwnerMapper ownerMapper;

    @Autowired
    ModelMapper mapper;

    public Account mapToAccount(AccountDto accountDto){
        Account account = mapper.map(accountDto, Account.class);
        return account;
    }

    public AccountDto mapToAccountDto(Account account){
        AccountDto accountDto =  mapper.map(account, AccountDto.class);
//        accountDto.setOwnerId(account.getOwner().getId());
        return accountDto;
    }

    public List<Account> mapToAccountList(List<AccountDto> accountDtoList){
        if(accountDtoList==null || accountDtoList.isEmpty()){
            return new ArrayList<>();
        }
        return accountDtoList.stream()
                .map(accountDto -> mapper.map(accountDto, Account.class))
                .collect(Collectors.toList());
    }

    public List<AccountDto> mapToAccountDtoList(List<Account> accountList){
        if( accountList==null || accountList.isEmpty()){
            return new ArrayList<>();
        }
        return accountList.stream()
                .map(account -> mapper.map(account, AccountDto.class))
                .collect(Collectors.toList());
    }
}
