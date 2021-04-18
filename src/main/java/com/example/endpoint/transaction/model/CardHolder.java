package com.example.endpoint.transaction.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CardHolder implements Serializable {
    static final long serialVersionUID = -3335741659908512901L;

    String maskedPan;
    String name;
}
