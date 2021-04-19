package com.example.endpoint.util;

import com.example.endpoint.account.AccountRepository;
import com.example.endpoint.account.model.Account;
import com.example.endpoint.account.model.AccountStatus;
import com.example.endpoint.account.model.AccountType;
import com.example.endpoint.auth.user.User;
import com.example.endpoint.auth.user.UserRepository;
import com.example.endpoint.transaction.TransactionRepository;
import com.example.endpoint.transaction.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;


/**
 * Utility class used only for testing purposes.
 * Inserts sample data in a mongo database when the application starts.
 */

@Component
public class SampleDataCreator implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) {
        insertUsers();
        insertAccounts();
        insertTransactions();
    }

    private void insertUsers() {
        User user = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .build();

        userRepository.deleteAll();
        userRepository.insert(user);
    }

    private void insertTransactions() {
        Transaction transaction1 = Transaction.builder()
                .accountId("a7065dce-6a18-428a-901d-cca14740af16")
                .rate(ExchangeRate.builder()
                        .currencyFrom(Currency.getInstance("EUR"))
                        .currencyTo(Currency.getInstance("USD"))
                        .rate(1.1)
                        .build())
                .originalAmount(Amount.builder()
                        .amount(106.17285630918292)
                        .currency(Currency.getInstance("USD"))
                        .build())
                .creditor(CardHolder.builder()
                        .maskedPan("XXXXXXXXXX1230")
                        .name("Creditor 1230")
                        .build())
                .debtor(CardHolder.builder()
                        .maskedPan("XXXXXXXXXX9870")
                        .name("DebtorName 9870")
                        .build())
                .status(TransactionStatus.BOOKED)
                .currency(Currency.getInstance("EUR"))
                .amount(96.52077846289356)
                .update(LocalDateTime.now())
                .description("Mc Donalds Amsterdam transaction - 0")
                .build();

        transactionRepository.deleteAll();
        transactionRepository.insert(Arrays.asList(transaction1));
    }

    private void insertAccounts() {
        Account account1 = Account.builder()
                .name("Account-ionescu")
                .update(LocalDateTime.now())
                .product("Gold account")
                .status(AccountStatus.ENABLED)
                .type(AccountType.CREDIT_CARD)
                .balance(9107.935799901432)
                .build();

        Account account2 = Account.builder()
                .name("Account-JohnnyC")
                .update(LocalDateTime.now())
                .product("USD Account")
                .status(AccountStatus.DISABLED)
                .type(AccountType.BANK_ACCOUNT)
                .balance(1545.122342542)
                .build();

        accountRepository.deleteAll();
        accountRepository.insert(Arrays.asList(account1, account2));
    }
}
