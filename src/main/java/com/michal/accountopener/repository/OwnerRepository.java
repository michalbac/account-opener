package com.michal.accountopener.repository;

import com.michal.accountopener.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

   Owner findByNameAndSurname(String name, String surname);

   boolean existsByNameAndSurname (String name, String surname);
}
