package com.michal.accountopener.account.domain.ports;

import com.michal.accountopener.account.domain.model.Owner;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface OwnerRepository extends Repository<Owner, Long> {

   Owner findByNameAndSurname(String name, String surname);

   boolean existsByNameAndSurname (String name, String surname);

   Owner save(Owner owner);

   void deleteById(Long id);

   Optional<Owner> findById(long id);
}
