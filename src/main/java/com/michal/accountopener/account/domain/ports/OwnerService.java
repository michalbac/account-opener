package com.michal.accountopener.account.domain.ports;

import com.michal.accountopener.account.domain.model.Owner;

import java.util.Optional;

public class OwnerService {
    private OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

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
        if(existByNameAndSurname(name,surname)){
            owner = findByNameAndSurname(name, surname);
        } else {
            owner = new Owner(name, surname);
            save(owner);
        }
        return  owner;
    }

    public Optional <Owner> findById(long id){
        return ownerRepository.findById(id);
    }


}
