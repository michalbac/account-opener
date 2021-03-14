package com.michal.accountopener.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
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

}
