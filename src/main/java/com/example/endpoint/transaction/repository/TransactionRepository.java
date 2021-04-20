package com.example.endpoint.transaction.repository;

import com.example.endpoint.transaction.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
