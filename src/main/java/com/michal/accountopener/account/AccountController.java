package com.michal.accountopener.account;

import com.michal.accountopener.account.domain.AccountFacade;
import com.michal.accountopener.account.dto.AccountDto;
import com.michal.accountopener.account.dto.OpenAccountDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
class AccountController {
    private AccountFacade accountFacade;

    public AccountController(AccountFacade accountFacade){
        this.accountFacade = accountFacade;
    }

    @PostMapping()
    public AccountDto createAccount(@RequestBody OpenAccountDto accountDto){
        return accountFacade.create(accountDto);
    }

    @GetMapping("convert/usd/{id}/{amount}")
    public AccountDto convertAccountToUsd (@PathVariable long id, @PathVariable BigDecimal amount){
        return accountFacade.changeAmountToUsd(id, amount);
    }

    @GetMapping("convert/pln/{id}/{amount}")
    public AccountDto convertAccountToPln (@PathVariable long id, @PathVariable BigDecimal amount){
        return accountFacade.changeAmountToPln(id, amount);
    }

    @GetMapping("/{id}")
    public AccountDto getAccountInfo(@PathVariable long id){
        return accountFacade.getAccount(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable long id){
        accountFacade.deleteAccount(id);
    }
}
