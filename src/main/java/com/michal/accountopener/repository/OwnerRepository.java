package com.michal.accountopener.repository;

import com.michal.accountopener.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

   Owner findAllByNameAndSurname(String name, String surname);

   boolean existsAllByNameAndSurname (String name, String surname);
}
