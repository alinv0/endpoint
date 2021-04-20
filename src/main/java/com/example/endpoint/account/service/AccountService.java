package com.example.endpoint.account.service;

import com.example.endpoint.account.model.Account;
import com.example.endpoint.account.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Cacheable("accounts")
    public List<Account> getAccounts() {
        log.debug("Retrieving account list");
        return accountRepository.findAll();
    }

    @CachePut("accounts")
    @Scheduled(cron = "0 0 2 * * *")
    public List<Account> updateCacheAsync() {
        log.debug("Refreshing accounts cache");
        return accountRepository.findAll();
    }
}
