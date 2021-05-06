package com.michal.accountopener.config;

import com.michal.accountopener.account.domain.AccountFacade;
import com.michal.accountopener.account.domain.ports.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfiguration {

    @Bean
    AccountService accountService(AccountRepository accountRepository, ExchangeRateClient exchangeRateClient){
        return new AccountService(accountRepository, exchangeRateClient);
    }

    @Bean
    OwnerService ownerService(OwnerRepository ownerRepository){
        return new OwnerService(ownerRepository);
    }

    @Bean
    AccountFacade accountFacade(AccountService accountService,  OwnerService ownerService){
        return new AccountFacade(accountService, ownerService );
    }

}
