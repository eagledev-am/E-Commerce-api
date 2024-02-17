package com.example.bankapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponse {

    @JsonProperty("Card Number")
    private Long cardNumber;
    @JsonProperty("Username")
    private String username;
    @JsonProperty("Balance")
    private BigDecimal balance;
}
