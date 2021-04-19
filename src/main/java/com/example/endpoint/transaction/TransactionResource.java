package com.example.endpoint.transaction;

import com.example.endpoint.transaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }
}
