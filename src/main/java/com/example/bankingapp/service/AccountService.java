package com.example.bankingapp.service;

import com.example.bankingapp.dao.AccountDao;
import com.example.bankingapp.model.Account;
import com.jazasoft.util.Utils;

public class AccountService {

    private AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Account save(Account account) {
        String accountNumber = Utils.getRandomNumber(10);
        account.setAccountNumber(accountNumber);
        return accountDao.save(account);
    }

    public double depositMoney(String accountNumber, double amount) {
        Account account = accountDao.findOne(accountNumber);
        account.setBalance(account.getBalance() + amount);
        return account.getBalance();
    }

    public double withdrawMoney(String accountNumber, double amount) {
        Account account = accountDao.findOne(accountNumber);
        account.setBalance(account.getBalance() - amount);
        return account.getBalance();
    }

    public double checkBalance(String accountNumber) {
        Account account = accountDao.findOne(accountNumber);
        return account.getBalance();
    }

}
