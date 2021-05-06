package com.michal.accountopener.adapters.repository;

import com.michal.accountopener.account.domain.model.Account;
import com.michal.accountopener.account.domain.ports.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
class InMemoryAccountRepository implements AccountRepository {
    private Map<Long, Account> map = new ConcurrentHashMap<>();

    @Override
    public Optional<Account> findById(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteById(long id) {
        map.remove(id);
    }

    @Override
    public Account save(Account account) {
        return map.put(account.getId(), account);
    }

}
