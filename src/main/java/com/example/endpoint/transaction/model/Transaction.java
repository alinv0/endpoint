package com.example.endpoint.transaction.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Document
@Builder
public class Transaction implements Serializable {
    static final long serialVersionUID = -3259758581087183859L;

    @Id
    private String id;
    private String accountId;
    private ExchangeRate rate;
    private Amount originalAmount;
    private CardHolder creditor;
    private CardHolder debtor;
    private TransactionStatus status;
    private Currency currency;
    private double amount;
    private LocalDateTime update;
    private String description;
}
