package com.example.bankingapp.dao;

import com.example.bankingapp.UserType;
import com.example.bankingapp.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class MySQLUserDaoImplTest {

    UserDao userDao;

    @BeforeClass
    public void setUp() {
        userDao = new MySQLUserDaoImpl();
    }

    public void authenticate() {
        User user = userDao.authenticate("zahid7292", "zahid");
        Assert.assertNotNull(user);

        user = userDao.authenticate("zahid", "zahid");
        Assert.assertNull(user);
    }

    public void save() {
        User user = new User("Arvind Kumar", "arvind123", "arvind", UserType.CUSTOMER);
        user = userDao.save(user);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
    }

    public void findOne() {
        User user = userDao.findOne("srk123");
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getAccount());
    }
}
