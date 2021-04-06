package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.AccountDto;
import com.michal.accountopener.domain.OpenAccountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenAccountMapperTestSuite {
    @Autowired
    OpenAccountMapper openAccountMapper;

    @Test
    public void mapToAccountTest(){
        //Given
        OpenAccountDto openAccountDto = new OpenAccountDto(new BigDecimal("100"), "jon", "smith");

        //When
        Account account = openAccountMapper.mapToAccount(openAccountDto);

        //Then
        assertEquals(openAccountDto.getStartingBalance(), account.getStartingBalance());
        assertEquals(Currency.getInstance("PLN"), account.getCurrency());
    }

    @Test
    public void mapToAccountDto(){
        //Given
        OpenAccountDto openAccountDto = new OpenAccountDto(new BigDecimal("100"), "jon", "smith");

        //When
        AccountDto accountDto = openAccountMapper.mapToAccountDto(openAccountDto);

        //Then
        assertEquals(openAccountDto.getStartingBalance(), accountDto.getStartingBalance());
        assertEquals(Currency.getInstance("PLN"), accountDto.getCurrency());
    }
}
