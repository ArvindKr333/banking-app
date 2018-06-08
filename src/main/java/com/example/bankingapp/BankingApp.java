package com.example.bankingapp;

import com.example.bankingapp.dao.*;
import com.example.bankingapp.model.Account;
import com.example.bankingapp.model.User;
import com.example.bankingapp.service.AccountService;
import com.example.bankingapp.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class BankingApp {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        User user;

        UserDao userDao = new MySQLUserDaoImpl();
        UserService userService = new UserService(userDao);

        AccountDao accountDao = new InMemoryAccountDaoImpl();
        AccountService accountService = new AccountService(accountDao);

        String username, password;

        Integer resp = 0;
        do {
            System.out.println("\n1. Login");
            System.out.println("0. Exit");

            resp = Integer.parseInt(reader.readLine().trim());

            switch (resp) {
                case 0:
                    System.out.println("Operation selected: Exit");
                    break;
                case 1:
                    System.out.print("Username: \t");
                    username = reader.readLine();
                    System.out.print("Password: \t");
                    password = reader.readLine();
                    user = userService.authenticate(username, password);

                    Integer response = 0;
                    if (user != null) {
                        System.out.println("Login Successful..");


                        do {
                            if (UserType.parse(user.getType()) == UserType.MANAGER) {
                                System.out.println("\n1. Open Account");
                                System.out.println("2. View All");

                                System.out.println("0. Logout");

                                response =  Integer.parseInt(reader.readLine().trim());

                                switch (response) {
                                    case 0:
                                        System.out.println("Operation selected: Exit");
                                        break;
                                    case 1:
                                        System.out.println("Operation selected: Open Account");
                                        String uname, name, passwd, balance;
                                        System.out.print("Username: \t");
                                        uname = reader.readLine();
                                        System.out.print("Name: \t");
                                        name = reader.readLine();
                                        System.out.print("Password: \t");
                                        passwd = reader.readLine();
                                        System.out.print("Initial Balance: \t");
                                        balance = reader.readLine();


                                        User newUser = new User(name, uname, passwd, UserType.CUSTOMER);

                                        Account account = new Account(Double.parseDouble(balance.trim()));
                                        account = accountService.save(account);
                                        newUser.setAccount(account);

                                        newUser = userService.save(newUser);

                                        System.out.println(newUser);

                                        break;
                                    case 2:
                                        System.out.println("Operation selected: View All");

                                        Collection<User> users = userService.findAll();
                                        users.forEach(System.out::println);

                                        break;
                                    default:
                                        System.out.println("Incorrect Operation selected.");

                                }
                            } else if (UserType.parse(user.getType()) == UserType.CUSTOMER) {
                                System.out.println("\n1. Deposit Money");
                                System.out.println("2. Withdraw Money");
                                System.out.println("3. Check Balance");
                                System.out.println("0. Logout");

                                response =  Integer.parseInt(reader.readLine().trim());

                                String amount;
                                double balance;
                                switch (response) {
                                    case 0:
                                        System.out.println("Operation selected: Exit");
                                        break;
                                    case 1:
                                        System.out.println("Operation Selected: Deposit Money");
                                        System.out.print("Amount: \t");
                                        amount = reader.readLine();
                                        balance = accountService.depositMoney(user.getAccount().getAccountNumber(), Double.parseDouble(amount.trim()));
                                        System.out.println("Current Balance: " + balance);
                                        break;
                                    case 2:
                                        System.out.println("Operation Selected: Widthdraw Money");
                                        System.out.print("Amount: \t");
                                        amount = reader.readLine();
                                        balance = accountService.withdrawMoney(user.getAccount().getAccountNumber(), Double.parseDouble(amount.trim()));
                                        System.out.println("Current Balance: " + balance);
                                        break;
                                    case 3:
                                        System.out.println("Operation Selected: Check Balance");
                                        balance = accountService.checkBalance(user.getAccount().getAccountNumber());
                                        System.out.println("Current Balance: " + balance);
                                        break;
                                    default:
                                        System.out.println("Incorrect Operation selected.");
                                }
                            }



                        }while (response != 0);
                    } else {
                        System.out.println("Login failed. Try again!");
                    }
                    break;
                default: System.out.println("Invalid Operation selected");
            }

        }while (resp != 0);
    }
}
