package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.domain.OwnerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwnerMapperTestSuite {
    @Autowired
    OwnerMapper ownerMapper;

    @Test
    public void mapToOwnerTest(){
        //Given
        OwnerDto ownerDto = new OwnerDto(1, "John", "Smith");

        //When
        Owner owner = ownerMapper.mapToOwner(ownerDto);

        //Then
        assertEquals(owner.getId(), ownerDto.getId());
        assertEquals(owner.getName(), ownerDto.getName());
        assertEquals(owner.getSurname(), ownerDto.getSurname());
    }

    @Test
    public void mapToOwnerDtoTest(){
        //Given
        Owner owner = new Owner(1, "John", "Smith", new ArrayList<>());

        //When
        OwnerDto ownerDto = ownerMapper.mapToOwnerDto(owner);

        //Then
        assertEquals(owner.getId(), ownerDto.getId());
        assertEquals(owner.getName(), ownerDto.getName());
        assertEquals(owner.getSurname(), ownerDto.getSurname());
    }
}
