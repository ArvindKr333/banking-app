package com.example.bankingapp.dao;


import com.example.bankingapp.model.User;

import java.util.Collection;
import java.util.List;

public interface UserDao {

    /**
     * Authenticate User and return if found.
     * @param username Username
     * @param password Password
     * @return User object if authenticated. null if not found or not authenticated.
     */
    User authenticate(String username, String password);

    User findOne(Long id);

    User findOne(String username);

    Collection<User> findAll();

    User save(User user);

    User update(User user);

    User delete(Long id);

    User delete(String username);

    long count();
}
