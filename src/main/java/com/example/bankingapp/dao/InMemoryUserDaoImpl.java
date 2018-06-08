package com.example.bankingapp.dao;

import com.example.bankingapp.UserType;
import com.example.bankingapp.model.User;

import java.util.*;

public class InMemoryUserDaoImpl implements UserDao {

    private Map<Long, User> userCache = new HashMap<>();
    private Long counter;

    public InMemoryUserDaoImpl() {
        counter = 1L;
        User user = new User(counter++, "Md Zahid Raza", "zahid7292", "zahid", UserType.MANAGER);
        userCache.put(user.getId(), user);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = findOne(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User findOne(Long id) {
        return userCache.get(id);
    }

    @Override
    public User findOne(String username) {
        return userCache.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny().orElse(null);
    }

    @Override
    public Collection<User> findAll() {
        return userCache.values();
    }

    @Override
    public User save(User user) {
        user.setId(counter++);
        userCache.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("Id Required for updating User.");
        }
        userCache.put(user.getId(), user);
        return user;
    }

    @Override
    public User delete(Long id) {
        User user = userCache.get(id);
        userCache.remove(id);
        return user;
    }

    @Override
    public User delete(String username) {
        User user = findOne(username);
        if (user != null) {
            userCache.remove(user.getId());
        }
        return user;
    }

    @Override
    public long count() {
        return userCache.keySet().size();
    }
}
