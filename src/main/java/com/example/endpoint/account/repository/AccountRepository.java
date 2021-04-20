package com.example.endpoint.account.repository;

import com.example.endpoint.account.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}
