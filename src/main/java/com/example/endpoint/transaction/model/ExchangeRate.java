package com.example.endpoint.transaction.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Currency;

@Data
@Builder
public class ExchangeRate implements Serializable {
    static final long serialVersionUID = 9129321525863665630L;

    Currency currencyFrom;
    Currency currencyTo;
    Double rate;
}