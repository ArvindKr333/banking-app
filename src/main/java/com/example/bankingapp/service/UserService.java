package com.example.bankingapp.service;

import com.example.bankingapp.dao.UserDao;
import com.example.bankingapp.model.User;

import java.util.Collection;
import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User authenticate(String username, String password) {
        return userDao.authenticate(username, password);
    }

    public Collection<User> findAll() {
        return userDao.findAll();
    }

    public User save(User user) {
        return userDao.save(user);
    }
}
