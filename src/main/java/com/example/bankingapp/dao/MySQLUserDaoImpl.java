package com.example.bankingapp.dao;

import com.example.bankingapp.ConnectionUtils;
import com.example.bankingapp.UserType;
import com.example.bankingapp.model.Account;
import com.example.bankingapp.model.User;

import java.sql.*;
import java.util.Collection;

public class MySQLUserDaoImpl implements UserDao {

    @Override
    public User authenticate(String username, String password) {
        User user = findOne(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public User findOne(String username) {
        User user = null;
        Connection conn = ConnectionUtils.getConnection();
        String sql = "SELECT * FROM user WHERE username = '" + username + "'";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String uName = rs.getString(3);
                String password = rs.getString(4);
                String type = rs.getString(5);
                user = new User(id, name, uName, password, UserType.parse(type));

                sql = "SELECT * FROM account WHERE user_id = " + id;

                rs = statement.executeQuery(sql);
                if (rs.next()) {
                    id = rs.getLong(1);
                    String accountNumber = rs.getString(2);
                    Double balance = rs.getDouble(3);
                    Account account = new Account(id, accountNumber, balance);
                    user.setAccount(account);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO user VALUES( NULL, ?, ?, ?, ?)";
        Connection conn = ConnectionUtils.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getType());

            int result = ps.executeUpdate();
            if (result == 1) {
                User u = findOne(user.getUsername());
                user.setId(u.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(Long id) {
        return null;
    }

    @Override
    public User delete(String username) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
