package com.michal.accountopener.account.dto;

import lombok.*;


@Getter
@Setter
@Builder
public class OwnerDto {
    private long id;
    private String name;
    private String surname;
}
