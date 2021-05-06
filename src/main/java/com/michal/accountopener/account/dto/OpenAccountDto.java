package com.michal.accountopener.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenAccountDto {
    private BigDecimal startingPlnBalance;
    private String name;
    private String surname;

}
