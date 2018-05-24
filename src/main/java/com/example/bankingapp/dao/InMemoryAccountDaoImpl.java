package com.example.bankingapp.dao;

import com.example.bankingapp.model.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAccountDaoImpl implements AccountDao {
    private Map<Long, Account> accountCache = new HashMap<>();
    private Long counter;

    public InMemoryAccountDaoImpl() {
        counter = 1L;
    }

    public Account findOne(Long id) {
        return accountCache.get(id);
    }

    public Account findOne(String acountNumber) {
        return accountCache.values().stream().filter(account -> account.getAccountNumber().equals(acountNumber)).findAny().orElse(null);
    }

    public List<Account> findAll() {
        return null;
    }

    public Account save(Account account) {
        account.setId(counter++);
        accountCache.put(account.getId(), account);
        return account;
    }

    public Account update(Account account) {
        return null;
    }

    public Account delete(Long id) {
        return null;
    }

    public Account delete(String accountNumber) {
        return null;
    }
}
