package com.michal.accountopener.service;

import com.michal.accountopener.domain.Account;
import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.repository.AccountRepository;
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
    AccountService accountService;

    @Mock
    AccountRepository accountRepositoryMock;

    @Test
    public void shouldReturnAccount(){
        //Given
        Owner owner  = new Owner(1, "John", "Smith", new ArrayList<>());
        Account createdAccount = new Account(1, Currency.getInstance("PLN"), new BigDecimal("1000"), owner);

        //When
        Mockito.when(accountRepositoryMock.findById(1L)).thenReturn(Optional.of(createdAccount));
        Account account = accountService.getAccount(1L);

        //Then
        assertNotNull(account);
        assertEquals(1, account.getId());
        assertEquals("PLN", account.getCurrency().toString());
        assertEquals(new BigDecimal("1000"), account.getStartingBalance());
        Mockito.verify(accountRepositoryMock, Mockito.times(1)).findById(1L);
    }
}
