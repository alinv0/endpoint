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
    String id;
    String accountId;
    ExchangeRate rate;
    Amount originalAmount;
    CardHolder creditor;
    CardHolder debtor;
    TransactionStatus status;
    Currency currency;
    Double amount;
    LocalDateTime update;
    String description;
}
