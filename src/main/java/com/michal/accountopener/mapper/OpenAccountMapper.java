package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.AccountDto;
import com.michal.accountopener.domain.OpenAccountDto;
import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class OpenAccountMapper {
    @Autowired
    OwnerRepository repository;

    @Autowired
    OwnerMapper ownerMapper;


    public AccountDto mapToAccountDto(OpenAccountDto openAccountDto){
        AccountDto accountDto = new AccountDto();
        if(repository.existsAllByNameAndSurname(openAccountDto.getName(),openAccountDto.getSurname())){
            Owner owner = repository.findAllByNameAndSurname(openAccountDto.getName(), openAccountDto.getSurname());
            accountDto.setOwner(ownerMapper.mapToOwnerDto(owner));
            repository.save(owner);

        } else {
            Owner owner = new Owner();
            owner.setName(openAccountDto.getName());
            owner.setSurname(openAccountDto.getSurname());
            accountDto.setOwner(ownerMapper.mapToOwnerDto(owner));
            repository.save(owner);

        }
        accountDto.setStartingBalance(openAccountDto.getStartingBalance());
        accountDto.setCurrency(Currency.getInstance("PLN"));

        return accountDto;
    }
}
