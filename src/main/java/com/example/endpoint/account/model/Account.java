package com.example.endpoint.account.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Document
public class Account implements Serializable {
    static final long serialVersionUID = -4715691204848611817L;

    @Id
    private String id;
    private LocalDateTime update;
    private String name;
    private String product;
    private AccountStatus status;
    private AccountType type;
    private double balance;
}
