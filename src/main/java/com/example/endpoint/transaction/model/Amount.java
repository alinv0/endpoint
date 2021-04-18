package com.example.endpoint.transaction.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Currency;

@Data
@Builder
public class Amount implements Serializable {
    static final long serialVersionUID = 7759133917397061489L;

    Double amount;
    Currency currency;
}
