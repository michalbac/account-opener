package com.michal.accountopener.account.domain.ports;

import com.michal.accountopener.account.domain.model.Account;
import com.michal.accountopener.account.dto.AccountNotFoundException;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Repository<Account, Long> {
    Optional<Account> findById(long id);

    List<Account> findAll();

    void deleteById(long id);

    Account save(Account account);

    default Account findOneOrThrow(Long id){
        Optional<Account> account = findById(id);
        if(account.isPresent()){
            return account.get();
        }
        throw new AccountNotFoundException(id);
    }
}
