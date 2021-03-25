package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.domain.OwnerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    ModelMapper mapper;

    public Owner mapToOwner(OwnerDto ownerDto){
        Owner owner = mapper.map(ownerDto, Owner.class);
        return owner;

    }

    public OwnerDto mapToOwnerDto(Owner owner){
        OwnerDto ownerDto = mapper.map(owner, OwnerDto.class);
        return ownerDto;
    }
}
