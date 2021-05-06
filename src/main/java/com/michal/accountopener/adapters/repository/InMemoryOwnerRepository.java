package com.michal.accountopener.adapters.repository;

import com.michal.accountopener.account.domain.model.Owner;
import com.michal.accountopener.account.domain.ports.OwnerRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryOwnerRepository implements OwnerRepository {
    private Map<Long, Owner> map = new ConcurrentHashMap<>();

    @Override
    public Owner findByNameAndSurname(String name, String surname) {
        return map.get(new Owner(name,surname));
    }

    @Override
    public boolean existsByNameAndSurname(String name, String surname) {
        return map.containsKey(new Owner(name, surname));
    }

    @Override
    public Owner save(Owner owner) {
        return map.put(owner.getId(), owner);
    }

    @Override
    public void deleteById(Long id) {
        map.remove(id);

    }

    @Override
    public Optional<Owner> findById(long id) {
        return Optional.ofNullable(map.get(id));
    }
}
