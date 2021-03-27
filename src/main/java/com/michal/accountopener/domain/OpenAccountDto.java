package com.michal.accountopener.domain;

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
    private BigDecimal startingBalance;
    private String name;
    private String surname;

}
