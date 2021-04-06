package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.domain.OwnerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTestSuite {
    @Autowired
    AccountMapper accountMapper;

    @Test
    public void mapToAccountTest(){
        //Given
        OwnerDto ownerDto = new OwnerDto(1, "John", "Smith");
        AccountDto accountDto = new AccountDto(1, Currency.getInstance("PLN"), new BigDecimal("1000"), ownerDto);

        //When
        Account account = accountMapper.mapToAccount(accountDto);

        //Then
        assertEquals(accountDto.getId(), account.getId());
        assertEquals(accountDto.getCurrency(), account.getCurrency());
        assertEquals(accountDto.getStartingBalance(), account.getStartingBalance());
        assertEquals(accountDto.getOwner().getId(), account.getOwner().getId());
        assertEquals(accountDto.getOwner().getName(), account.getOwner().getName());
        assertEquals(accountDto.getOwner().getSurname(), account.getOwner().getSurname());
    }

    @Test
    public void mapToAccountDtoTest(){
        //Given
        Owner owner = new Owner(1, "John", "Smith", new ArrayList<>());
        Account account = new Account(1, Currency.getInstance("PLN"), new BigDecimal("1000"), owner);

        //When
        AccountDto accountDto = accountMapper.mapToAccountDto(account);

        //Then
        assertEquals(account.getId(), accountDto.getId());
        assertEquals(account.getCurrency(), accountDto.getCurrency());
        assertEquals(account.getStartingBalance(), accountDto.getStartingBalance());
        assertEquals(account.getOwner().getId(), accountDto.getOwner().getId());
        assertEquals(account.getOwner().getName(), accountDto.getOwner().getName());
        assertEquals(account.getOwner().getSurname(), accountDto.getOwner().getSurname());
    }

    @Test
    public void mapToAccountList(){
        //Given
        OwnerDto ownerDto1 = new OwnerDto(1, "John", "Smith");
        OwnerDto ownerDto2 = new OwnerDto(2, "Mike", "Jones");
        AccountDto accountDto1 = new AccountDto(1, Currency.getInstance("PLN"), new BigDecimal("1000"), ownerDto1);
        AccountDto accountDto2 = new AccountDto(2, Currency.getInstance("USD"), new BigDecimal("500"), ownerDto1);
        AccountDto accountDto3 = new AccountDto(3, Currency.getInstance("PLN"), new BigDecimal("5000"), ownerDto2);
        List<AccountDto> accountDtoList = new ArrayList<>();
        accountDtoList.add(accountDto1);
        accountDtoList.add(accountDto2);
        accountDtoList.add(accountDto3);

        //When
        List<Account> accountList = accountMapper.mapToAccountList(accountDtoList);

        //Then
        assertEquals(3, accountList.size());
        assertEquals(accountDtoList.get(0).getId(), accountList.get(0).getId());
        assertEquals(accountDtoList.get(0).getCurrency(), accountList.get(0).getCurrency());
        assertEquals(accountDtoList.get(0).getStartingBalance(), accountList.get(0).getStartingBalance());
        assertEquals(accountDtoList.get(0).getOwner().getId(), accountList.get(0).getOwner().getId());
        assertEquals(accountDtoList.get(0).getOwner().getName(), accountList.get(0).getOwner().getName());
        assertEquals(accountDtoList.get(0).getOwner().getSurname(), accountList.get(0).getOwner().getSurname());
    }

    @Test
    public void mapToAccountDtoList(){
        //Given
        Owner owner1 = new Owner(1, "John", "Smith", new ArrayList<>());
        Owner owner2 = new Owner(2, "Mike", "Jones", new ArrayList<>());
        Account account1 = new Account(1, Currency.getInstance("PLN"), new BigDecimal("1000"), owner1);
        Account account2 = new Account(2, Currency.getInstance("USD"), new BigDecimal("500"), owner2);
        Account account3 = new Account(3, Currency.getInstance("PLN"), new BigDecimal("5000"), owner1);
        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);

        //When
        List<AccountDto> accountDtoList = accountMapper.mapToAccountDtoList(accountList);

        //Then
        assertEquals(3, accountDtoList.size());
        assertEquals(accountDtoList.get(0).getId(), accountList.get(0).getId());
        assertEquals(accountDtoList.get(0).getCurrency(), accountList.get(0).getCurrency());
        assertEquals(accountDtoList.get(0).getStartingBalance(), accountList.get(0).getStartingBalance());
        assertEquals(accountDtoList.get(0).getOwner().getId(), accountList.get(0).getOwner().getId());
        assertEquals(accountDtoList.get(0).getOwner().getName(), accountList.get(0).getOwner().getName());
        assertEquals(accountDtoList.get(0).getOwner().getSurname(), accountList.get(0).getOwner().getSurname());
    }
}
