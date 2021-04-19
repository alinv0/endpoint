package com.example.endpoint.account;

import com.example.endpoint.account.model.Account;
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
public class AccountResourceTest {

    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountResource accountResource;

    @Test
    public void shouldGetAccounts() {
        List<Account> testAccounts = new ArrayList<>();
        when(accountService.getAccounts()).thenReturn(testAccounts);
        assertEquals(testAccounts, accountResource.getAccounts());
    }

}
