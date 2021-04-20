package com.example.endpoint.transaction.service;

import com.example.endpoint.transaction.model.Transaction;
import com.example.endpoint.transaction.repository.TransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Cacheable("transactions")
    public List<Transaction> getTransactions() {
        log.debug("Retrieving transaction list");
        return transactionRepository.findAll();
    }

    @CachePut("transactions")
    @Scheduled(cron = "0 0 2 * * *")
    public List<Transaction> updateCacheAsync() {
        log.debug("Refreshing transactions cache");
        return transactionRepository.findAll();
    }
}
