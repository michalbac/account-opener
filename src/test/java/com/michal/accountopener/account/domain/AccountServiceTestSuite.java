package com.michal.accountopener.account.domain;

import com.michal.accountopener.account.domain.model.Account;
import com.michal.accountopener.account.domain.model.Owner;
import com.michal.accountopener.account.domain.ports.AccountRepository;
import com.michal.accountopener.account.dto.AccountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTestSuite {
    @InjectMocks
    AccountFacade accountFacade;

    @Mock
    AccountRepository accountRepositoryMock;

    @Test
    public void shouldReturnAccount(){
        //Given
        Owner owner  = new Owner("John", "Smith");
        Account createdAccount = new Account(new BigDecimal("1000"), owner);

        //When
        Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(Optional.of(createdAccount));
        AccountDto accountDto = accountFacade.getAccount(1L);

        //Then
        assertNotNull(accountDto);
        assertEquals(1, accountDto.getId());
         assertEquals(new BigDecimal("1000"), accountDto.getBalanceInPln());
        Mockito.verify(accountRepositoryMock, Mockito.times(1)).findById(1L);
    }

    @Test
    public void shouldConvertToUsd(){
        //Given
        Owner owner  = new Owner("John", "Smith");
        Account createdAccount = new Account(new BigDecimal("1000"), owner);

        //When
        Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(Optional.of(createdAccount));
        AccountDto accountDto = accountFacade.getAccount(1L);

        //Then
        assertNotNull(accountDto);
        assertEquals(1, accountDto.getId());
        assertEquals(new BigDecimal("1000"), accountDto.getBalanceInPln());
        Mockito.verify(accountRepositoryMock, Mockito.times(1)).findById(1L);

    }
}
