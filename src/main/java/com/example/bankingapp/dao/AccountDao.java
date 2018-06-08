package com.example.bankingapp.dao;

import com.example.bankingapp.model.Account;

import java.util.List;

public interface AccountDao {

    Account findOne(Long id);

    Account findOne(String acoountNumber);

    List<Account> findAll();

    Account save(Account account);

    Account update(Account account);

    Account delete(Long id);

    Account delete(String accountNumber);
}
