package com.example.bankingapp.model;

public class Account {
    private Long id;
    private String acoountNumber;
    private Double balance;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcoountNumber() {
        return acoountNumber;
    }

    public void setAcoountNumber(String acoountNumber) {
        this.acoountNumber = acoountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
