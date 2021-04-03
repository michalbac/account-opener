package com.michal.accountopener.service;

import com.michal.accountopener.domain.Owner;
import com.michal.accountopener.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;

    public Owner save (Owner owner){
        return ownerRepository.save(owner);
    }

    public void delete (Long id){
        ownerRepository.deleteById(id);
    }

    public Owner findByNameAndSurname(String name, String surname){
        return ownerRepository.findByNameAndSurname(name, surname);
    }

    public boolean existByNameAndSurname(String name, String surname){
        return ownerRepository.existsByNameAndSurname(name,surname);
    }

    public Owner saveIfNotExists(String name, String surname){
        Owner owner;
        if(ownerRepository.existsByNameAndSurname(name,surname)){
            owner = ownerRepository.findByNameAndSurname(name, surname);

        } else {
            owner = new Owner();
            owner.setName(name);
            owner.setSurname(surname);
            ownerRepository.save(owner);

        }
        return  owner;
    }


}
