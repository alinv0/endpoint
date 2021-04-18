package com.example.endpoint.account.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Document
@Builder
public class Account implements Serializable {

    static final long serialVersionUID = -4715691204848611817L;

    @Id
    String id;

    LocalDateTime update;
    String name;
    String product;
    AccountStatus status;
    AccountType type;
    Double balance;
}
