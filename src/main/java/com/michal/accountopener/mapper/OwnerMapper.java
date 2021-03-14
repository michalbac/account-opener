package com.michal.accountopener.mapper;

import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.domain.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    @Autowired
    AccountMapper accountMapper;

    public Owner mapToOwner(OwnerDto ownerDto){
        return new Owner(ownerDto.getId(),
                ownerDto.getName(),
                ownerDto.getSurname(),
                accountMapper.mapToAccountList(ownerDto.getAccounts()));

    }

    public OwnerDto mapToOwnerDto(Owner owner){
        return new OwnerDto(owner.getId(),
                owner.getName(),
                owner.getSurname(),
                accountMapper.mapToAccountDtoList(owner.getAccounts()));

    }
}
