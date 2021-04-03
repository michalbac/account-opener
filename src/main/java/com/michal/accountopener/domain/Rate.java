package com.michal.accountopener.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    @JsonProperty("no")
    private String no;
    @JsonProperty("effectiveDate")
    private Date effectiveDate;
    @JsonProperty("mid")
    private BigDecimal mid;

}
