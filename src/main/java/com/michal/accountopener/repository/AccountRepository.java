package com.michal.accountopener.repository;

import com.michal.accountopener.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
