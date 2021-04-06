package com.michal.accountopener.service;

import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.repository.OwnerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTestSuite {
    @InjectMocks
    OwnerService ownerService;

    @Mock
    OwnerRepository ownerRepositoryMock;

    @Test
    public void getOwnerByIdTest(){
        //Given
        Owner createdOwner = new Owner(1, "John", "Smith", new ArrayList<>());

        //When
        Mockito.when(ownerRepositoryMock.findById(1L)).thenReturn(Optional.of(createdOwner));
        Owner owner = ownerService.findById(1);

        //Then
        assertNotNull(owner);
        assertEquals( 1L, owner.getId());
        assertEquals( "John", owner.getName());
        assertEquals( "Smith", owner.getSurname());
        Mockito.verify(ownerRepositoryMock, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void shouldReturnFoundOwner(){
        //Given
        Owner createdOwner = new Owner(1, "John", "Smith", new ArrayList<>());

        //When
        Mockito.when(ownerRepositoryMock.existsByNameAndSurname("John", "Smith")).thenReturn(true);
        Mockito.when(ownerRepositoryMock.findByNameAndSurname("John", "Smith")).thenReturn(createdOwner);
        Owner owner = ownerService.saveIfNotExists("John", "Smith");

        //Then
        assertNotNull(owner);
        assertEquals( 1L, owner.getId());
        assertEquals( "John", owner.getName());
        assertEquals( "Smith", owner.getSurname());
        Mockito.verify(ownerRepositoryMock, Mockito.times(1)).existsByNameAndSurname(anyString(), anyString());
        Mockito.verify(ownerRepositoryMock, Mockito.times(0)).save(any());
    }

    @Test
    public void shouldSaveNewUser(){
        //Given
        Owner createdOwner = new Owner(1, "John", "Smith", new ArrayList<>());

        //When
        Mockito.when(ownerRepositoryMock.existsByNameAndSurname("John", "Smith")).thenReturn(false);
        Mockito.when(ownerRepositoryMock.save(any())).thenReturn(createdOwner);
        Owner owner = ownerService.saveIfNotExists("John", "Smith");

        //Then
        assertNotNull(owner);
        assertEquals( "John", owner.getName());
        assertEquals( "Smith", owner.getSurname());
        Mockito.verify(ownerRepositoryMock, Mockito.times(1)).existsByNameAndSurname(anyString(), anyString());
        Mockito.verify(ownerRepositoryMock, Mockito.times(0)).findByNameAndSurname(anyString(), anyString());
        Mockito.verify(ownerRepositoryMock, Mockito.times(1)).save(any());
    }

}
