package com.example.endpoint.transaction.resource;

import com.example.endpoint.transaction.model.Transaction;
import com.example.endpoint.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionResourceTest {

    @Mock
    private TransactionService transactionService;
    @InjectMocks
    private TransactionResource transactionResource;

    @Test
    public void shouldGetTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        when(transactionService.getTransactions()).thenReturn(transactions);
        assertEquals(transactions, transactionResource.getTransactions());
    }
}
