package com.michal.accountopener.account.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.michal.accountopener.account.dto.OwnerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @JsonManagedReference
    @OneToMany(targetEntity = Account.class, mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accounts;

    public Owner(@NotNull String name, @NotNull String surname) {
        this.name = name;
        this.surname = surname;
        this.accounts = new ArrayList<>();
    }

    public OwnerDto dto(){
        return OwnerDto.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return name.equals(owner.name) && surname.equals(owner.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
