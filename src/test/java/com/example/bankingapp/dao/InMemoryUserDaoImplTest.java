package com.example.bankingapp.dao;

import com.example.bankingapp.UserType;
import com.example.bankingapp.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class InMemoryUserDaoImplTest {

    UserDao userDao;

    @BeforeClass
    public void setUp() {
        userDao = new InMemoryUserDaoImpl();
    }

    public void authenticate() {
        User user = userDao.authenticate("zahid7292", "zahid");
        Assert.assertNotNull(user);

        user = userDao.authenticate("zahid", "zahid");
        Assert.assertNull(user);
    }

    public void save() {
        Assert.assertEquals(userDao.count(), 1L);

        User user = new User("Arvind Kumar", "arvind123", "arvind", UserType.CUSTOMER);
        user = userDao.save(user);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(userDao.count(), 2L);
    }

}
