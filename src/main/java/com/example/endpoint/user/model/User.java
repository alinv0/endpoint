package com.example.endpoint.user.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document
public class User implements Serializable {
    static final long serialVersionUID = 7792869584241359335L;

    String username;
    String password;
}