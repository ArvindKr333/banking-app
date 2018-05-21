package com.example.bankingapp;

import com.example.bankingapp.dao.InMemoryUserDaoImpl;
import com.example.bankingapp.dao.UserDao;
import com.example.bankingapp.model.User;
import com.example.bankingapp.service.UserService;

import java.util.Scanner;

public class BankingApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user;

        UserDao userDao = new InMemoryUserDaoImpl();
        UserService userService = new UserService(userDao);

        String username, password;

        System.out.print("Username: \t");
        username = scanner.next();
        System.out.print("Password: \t");
        password = scanner.next();

        user = userService.authenticate(username, password);

        Integer response;
        if (user != null) {
            System.out.println("Login Successful..");

            do {
                System.out.println("1. Open Account");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Check Balance");
                System.out.println("5. Exit");

                response = scanner.nextInt();

                switch (response) {
                    case 1:
                        System.out.println("Operation selected: Open Account");
                        break;
                    case 2:
                        System.out.println("Operation selected: Deposit Money");
                        break;
                    case 3:
                        System.out.println("Operation selected: Withdraw Money");
                        break;
                    case 4:
                        System.out.println("Operation selected: Check Balance");
                        break;
                    case 5:
                        System.out.println("Operation selected: Exit");
                        break;
                    default:
                        System.out.println("Incorrect Operation selected.");

                }
            }while (response != 5);
        } else {
            System.out.println("Login failed...");
        }

    }
}
