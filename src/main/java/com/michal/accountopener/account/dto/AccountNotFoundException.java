package com.michal.accountopener.account.dto;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(Long id){
        super("No account with id: " + id + "found.");
    }
}
